package rs.ac.bg.etf.pp1;

import java.util.*;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {	
	public static final Struct boolType = new Struct(Struct.Bool);
	
	Struct currentType = Tab.noType;
	String currentTypeName = "";
	
	Obj currentMethod = Tab.noObj;
	Struct currentMethodType = Tab.noType;
	int currentMethodFormPars = 0;
	Map<String, ArrayList<Obj>> methodPars;
	Deque<Obj> callMethods;
	Deque<Integer> callMethodsParamsNumber;
	
	boolean voidMethod = false;
	boolean returnFound = false;
	
	Obj currentEnum = Tab.noObj;
	int currentEnumValue = 0;
	
	boolean errorDetected = false;
	
	int nVars;
	
	int globalVariableCount = 0;
	int localVariableCount = 0;
	int globalArrayVariableCount = 0;
	int localArrayVariableCount = 0;
	int constantCount = 0;
	
	private Map<String, Obj> globalEnums;
	private Map<String, String> localEnums;
	
	
	Logger log = Logger.getLogger(getClass());
	ArrayList<String> greske;
	
	public SemanticPass() {	
		Tab.insert(Obj.Type, "bool", boolType);
		
		ArrayList<Obj> chr = new ArrayList<Obj>();
		chr.add(new Obj(Obj.Var, "i", Tab.intType));
		
		ArrayList<Obj> ord = new ArrayList<Obj>();
		ord.add(new Obj(Obj.Var, "ch", Tab.charType));
		
		ArrayList<Obj> len = new ArrayList<Obj>();
		len.add(new Obj(Obj.Var, "len", new Struct(Struct.Array, Tab.noType)));
				
		methodPars = new HashMap<>();
		callMethods = new ArrayDeque<Obj>();
		callMethodsParamsNumber = new ArrayDeque<Integer>();
		
		globalEnums = new HashMap<>();
		localEnums = new HashMap<>();
		
		methodPars.put("chr",  chr);
		methodPars.put("ord",  ord);
		methodPars.put("len",  len);
		
		greske = new ArrayList<>();		
	}
	
	public ArrayList<String> getGreske() {
		return greske;
	}
	
	public void report_error(String message, SyntaxNode info) {	
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
		greske.add(msg.toString());	
		//MJParserTest.greske.add(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean isEnumEquals(Obj firstEnum, Obj secondEnum) {
		// First enum is left enum, get type of it
		// Left enum must be variable, so we look in localEnums
		String firstEnumType = this.localEnums.get(firstEnum.getName());
		
		// Second enum is right enum, get type of it
		// Right enum must be type, so we look in globalEnums
		Obj secondEnumType = this.globalEnums.get(secondEnum.getName());
		
		// Types must not be null and must be equal
		if(!firstEnumType.equals(null) && secondEnumType != null) {
			// If type of left enum is equal to type or right enum, return true
			if(firstEnumType.equals(secondEnumType.getName())) {
				return true;
			}
			// Otherwise return false
			else {
				return false;
			}
		}
		return false;
	}
	
	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgramStart().obj);
		nVars = Tab.currentScope.getnVars();
		Obj mainMethod = Tab.find("main");
		
		if(mainMethod == Tab.noObj || mainMethod.getKind() != Obj.Meth || mainMethod.getType()!= Tab.noType || mainMethod.getLevel() != 0 ) {
			report_error("", null);
		}
		
		Tab.closeScope();
	}
	
	public void visit(ProgramStart programStart) {
		programStart.obj = Tab.insert(Obj.Prog, programStart.getLabelProgramName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(ConstantDefinition constantDefinition) {
		Obj ConstantValue = constantDefinition.getConstantValue().obj;
		
		// Check if its type of current selected type
		if(ConstantValue.getType().equals(currentType)) {
			// Name of constant is free
			if(Tab.find(constantDefinition.getLabelConstantName()) == Tab.noObj) {
				Obj insertedConstant = Tab.insert(Obj.Con, constantDefinition.getLabelConstantName(), currentType);
				insertedConstant.setAdr(ConstantValue.getAdr());
				insertedConstant.setLevel(0);
				constantCount++;
			}
			else {
				report_error("Error on line" + constantDefinition.getLine() + ", " + constantDefinition.getLabelConstantName() + " already declared", null);
			}
		}
		else {
			report_error("Error on line" + constantDefinition.getLine() + ", incompatible type", null);
		}
	}
	
	public void visit(NumConstantValue numConstantValue) {
		numConstantValue.obj = new Obj(Obj.Con, "", Tab.intType, numConstantValue.getNumValue(), Obj.NO_VALUE);
	}
	
	public void visit(CharConstantValue charConstantValue) {
		charConstantValue.obj = new Obj(Obj.Con, "", Tab.charType, charConstantValue.getCharValue().toCharArray()[1], Obj.NO_VALUE);
	}
	
	public void visit(BoolConstantValue boolConstantValue) {
		
		if(boolConstantValue.getBoolValue().toString().equals("true")) {
			boolConstantValue.obj = new Obj(Obj.Con, "", SemanticPass.boolType, 1, Obj.NO_VALUE);
		}
		else {
			boolConstantValue.obj = new Obj(Obj.Con, "", SemanticPass.boolType, 0, Obj.NO_VALUE);
		}
	}
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getLabelTypeName());
		
		// Check if there doesnt exists object with that name
		if(typeNode == Tab.noObj && typeNode.getType().getKind() != Struct.Bool) {
			// Not exists, report error
			report_error("Error on line " + type.getLine() + ", type not found in symbol table! ", null);
    		type.struct = Tab.noType;
		}
		else {
			// Check if type is regular
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
				currentType = typeNode.getType();
				currentTypeName = type.getLabelTypeName();
			}
			// If not, report errort
			else {
				report_error("Error on line " + type.getLine() + ", type not found" + type.getLabelTypeName() + " in symbol table! ", null);
    			type.struct = Tab.noType;
			}	
		}
	}
	
	public void visit(EnumDeclaration enumDeclaration) {
		Tab.chainLocalSymbols(currentEnum);
		Tab.closeScope();
		currentEnum = Tab.noObj;
		currentEnumValue = 0;
	}
	
	public void visit(EnumName enumName) {
		if(Tab.currentScope().findSymbol(enumName.getLabelEnumName()) == null) {
			enumName.obj = Tab.insert(Obj.Type, enumName.getLabelEnumName(), new Struct(Struct.Enum));
			Tab.openScope();
			currentEnum = enumName.obj;
			currentEnumValue = 0;
		}
		else {
			report_error("Error on line " + enumName.getLine() + ", enum type " + enumName.getLabelEnumName() + " already declared.", null);
			enumName.obj = Tab.noObj;
		}
	}
	
	public void visit(UnitializedEnumDefinition unitializedEnumDefinition) {
		if(Tab.currentScope().findSymbol(currentEnum.getName() + "." + unitializedEnumDefinition.getLabelName()) == null) {
			unitializedEnumDefinition.obj = Tab.insert(Obj.Con, currentEnum.getName() + "." + unitializedEnumDefinition.getLabelName(), Tab.intType);
			unitializedEnumDefinition.obj.setAdr(currentEnumValue++);
			globalEnums.put(currentEnum.getName() + "." + unitializedEnumDefinition.getLabelName(), currentEnum);
		}
		else {
			unitializedEnumDefinition.obj = Tab.noObj;
			report_error("Error on line " + unitializedEnumDefinition.getLine() + ", constant already declared inside enum.", null);
		}
	}
	
	public void visit(InitializedEnumDefinition initializedEnumDefinition) {
		if(Tab.currentScope().findSymbol(currentEnum.getName() + "." + initializedEnumDefinition.getLabelName()) == null) {
			initializedEnumDefinition.obj = Tab.insert(Obj.Con, currentEnum.getName() + "." + initializedEnumDefinition.getLabelName(), Tab.intType);
			initializedEnumDefinition.obj.setAdr(initializedEnumDefinition.getLabelEnumValue());
			globalEnums.put(currentEnum.getName() + "." + initializedEnumDefinition.getLabelName(), currentEnum);
			currentEnumValue = initializedEnumDefinition.getLabelEnumValue() + 1;
		}
		else {
			initializedEnumDefinition.obj = Tab.noObj;
			report_error("Error on line " + initializedEnumDefinition.getLine() + ", constant already declared inside enum.", null);
		} 
	}
	
	public void visit(NormalVariableDefinition normalVariableDefinition) {
		if(Tab.currentScope.findSymbol(normalVariableDefinition.getLabelVariableName()) == null) {
			normalVariableDefinition.obj = Tab.insert(Obj.Var, normalVariableDefinition.getLabelVariableName(), currentType);
			
			if(currentMethod == Tab.noObj) {
				globalVariableCount++;
				normalVariableDefinition.obj.setLevel(0);
			}
			else {
				localVariableCount++;
				normalVariableDefinition.obj.setLevel(1);
			}
			
			if(normalVariableDefinition.obj.getType().getKind() == Struct.Enum) {
				localEnums.put(normalVariableDefinition.getLabelVariableName(), currentTypeName);
			}
		}
		else {
			report_error("Error on line " + normalVariableDefinition.getLine() + ", variable " + normalVariableDefinition.getLabelVariableName() + " already declared", null);
		}
	}
	
	public void visit(ArrayVariableDefinition arrayVariableDefinition) {
		if(Tab.currentScope.findSymbol(arrayVariableDefinition.getLabelArrayName()) == null) {
			arrayVariableDefinition.obj = Tab.insert(Obj.Var, arrayVariableDefinition.getLabelArrayName(), new Struct(Struct.Array, currentType));
			
			if(currentMethod == Tab.noObj) {
				globalArrayVariableCount++;
				arrayVariableDefinition.obj.setLevel(0);
			}
			else {
				localArrayVariableCount++;
				arrayVariableDefinition.obj.setLevel(1);
			}
		}
		else {
			report_error("Error on line " + arrayVariableDefinition.getLine() + ", variable " + arrayVariableDefinition.getLabelArrayName() + " already declared", null);
		}
	}
	
	public void visit(MethodDeclaration methodDeclaration) {
		Obj localVariables = null;
		
		if(methodDeclaration.getFormParsOptional().obj != null) {
			localVariables = methodDeclaration.getFormParsOptional().obj;
		}
		else if(methodDeclaration.getMethodVariableDeclarationList().obj != null) {
			localVariables = methodDeclaration.getMethodVariableDeclarationList().obj;
		}
		
		if(localVariables != null) {
			Tab.chainLocalSymbols(currentMethod);
		}
		
		Tab.closeScope();
		
		if(currentMethod.getType() != Tab.noType && returnFound == false) {
			report_error("Error on line " + methodDeclaration.getLine() + ", method must have return statement.", null);
		}
		currentMethod = Tab.noObj;
		returnFound = false;		
	}
	
	public void visit(MethodTypeOther methodTypeOther) {
		methodTypeOther.struct = methodTypeOther.getType().struct;
		currentMethodType = methodTypeOther.struct;
	}
	
	public void visit(MethodTypeVoid methodTypeVoid) {
		methodTypeVoid.struct = Tab.noType;
		currentMethodType = Tab.noType;
		voidMethod = true;
	}
	
	public void visit(MethodName methodName) {
		if(voidMethod == false && currentMethodType == Tab.noType) {
			report_error("Error on line " + methodName.getLine() + " undefined return type of method", null);
			methodName.obj = Tab.noObj;
		}
		else {
			methodName.obj = Tab.insert(Obj.Meth, methodName.getLabelMethodName(), currentMethodType);
			methodPars.put(methodName.getLabelMethodName(), new ArrayList<>());
			
			currentMethod = methodName.obj;
			currentMethodFormPars = 0;
			returnFound = false;
			
			Tab.openScope();	
		}
	}
	
	public void visit(MultiFormParsOptional multiFormParsOptional) {
		multiFormParsOptional.obj = multiFormParsOptional.getFormPars().obj;
		currentMethod.setLevel(currentMethodFormPars);
		currentMethodFormPars = 0;
	}
	
	public void visit(EmptyFormParsOptional emptyFormParsOptional) {
		emptyFormParsOptional.obj = Tab.noObj;
		currentMethod.setLevel(0);
		currentMethodFormPars = 0;
	}
	
	public void visit(FormPars formPars) {
		formPars.obj = formPars.getFormParsDefinition().obj;
	}
		
	public void visit(NormalFormPars normalFormPars) {
		if(Tab.currentScope().findSymbol(normalFormPars.getLabelParameterName()) != null) {
			report_error("Error on line " + normalFormPars.getLine() + ", parameter already declared", null);
			normalFormPars.obj = Tab.noObj;
		}
		else if(normalFormPars.getType().struct == Tab.noType) {
			report_error("Error on line " + normalFormPars.getLine() + ", undefined parameter type", null);
			normalFormPars.obj = Tab.noObj;
		}
		else {
			normalFormPars.obj = Tab.insert(Obj.Var, normalFormPars.getLabelParameterName(), normalFormPars.getType().struct);
			methodPars.get(currentMethod.getName()).add(normalFormPars.obj);
			currentMethodFormPars++;
		}
	}
	
	public void visit(ArrayFormPars arrayFormPars) {
		if(Tab.currentScope().findSymbol(arrayFormPars.getLabelParameterName()) != null) {
			report_error("Error on line " + arrayFormPars.getLine() + ", parameter already declared", null);
			arrayFormPars.obj = Tab.noObj;
		}
		else if(arrayFormPars.getType().struct == Tab.noType) {
			report_error("Error on line " + arrayFormPars.getLine() + ", undefined parameter type", null);
			arrayFormPars.obj = Tab.noObj;
		}
		else {
			arrayFormPars.obj = Tab.insert(Obj.Var, arrayFormPars.getLabelParameterName(), new Struct(Struct.Array, arrayFormPars.getType().struct));
			methodPars.get(currentMethod.getName()).add(arrayFormPars.obj);
			currentMethodFormPars++;
		}
	}
	
	public void visit(ReadStatement readStatement) {
		if(readStatement.getDesignator().obj.getKind() != Obj.Var
				&& readStatement.getDesignator().obj.getKind() != Obj.Elem
				&& readStatement.getDesignator().obj.getKind() != Obj.Fld) {
			report_error("Error on line " + readStatement.getLine() + ", incompatible type for argument", null);
		}
		else if(readStatement.getDesignator().obj.getType().getKind() != Struct.Int
				&& readStatement.getDesignator().obj.getType().getKind() != Struct.Char
				&& readStatement.getDesignator().obj.getType().getKind() != Struct.Bool
				&& readStatement.getDesignator().obj.getType().getKind() != Struct.Enum) {
			report_error("Error on line " + readStatement.getLine() + ", incompatible type for argument", null);
		}
	}
	
	public void visit(PrintStatementWithoutArguments printStatementWithoutArguments) {
		if(printStatementWithoutArguments.getExpression().obj.getType().getKind() == Struct.Array) {
				if(printStatementWithoutArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Int
						&& printStatementWithoutArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Char
						&& printStatementWithoutArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Bool
						&& printStatementWithoutArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Enum) {
					report_error("Erorr on line " + printStatementWithoutArguments.getLine() + ", incompatibile type for argument", null);
				}
		}
		else if(printStatementWithoutArguments.getExpression().obj.getType().getKind() != Struct.Char
				&& printStatementWithoutArguments.getExpression().obj.getType().getKind() != Struct.Bool
				&& printStatementWithoutArguments.getExpression().obj.getType().getKind() != Struct.Int
				&& printStatementWithoutArguments.getExpression().obj.getType().getKind() != Struct.Enum) {
			report_error("Erorr on line " + printStatementWithoutArguments.getLine() + ", incompatibile type for argument", null);
		}
	}
	
	public void visit(PrintStatementWithArguments printStatementWithArguments) {
		if(printStatementWithArguments.getExpression().obj.getType().getKind() == Struct.Array) {
			if(printStatementWithArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Int
					&& printStatementWithArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Char
					&& printStatementWithArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Bool
					&& printStatementWithArguments.getExpression().obj.getType().getElemType().getKind() != Struct.Enum) {
				report_error("Erorr on line " + printStatementWithArguments.getLine() + ", incompatibile type for argument", null);
			}
		}
		else if(printStatementWithArguments.getExpression().obj.getType().getKind() != Struct.Char
				&& printStatementWithArguments.getExpression().obj.getType().getKind() != Struct.Int
				&& printStatementWithArguments.getExpression().obj.getType().getKind() != Struct.Bool
				&& printStatementWithArguments.getExpression().obj.getType().getKind() != Struct.Enum) {
			report_error("Erorr on line " + printStatementWithArguments.getLine() + ", incompatibile type for argument", null);
		}
	}
	
	public void visit(ReturnStatement returnStatement) {
		if(currentMethod == Tab.noObj) {
			report_error("Error on line " + returnStatement.getLine() + ", method not found", null);
		}
		else {
			returnFound = true;
			if(returnStatement.getExpressionOptional().obj != Tab.noObj) {
				if(currentMethod.getType() == Tab.noType) {
					report_error("Error on line " + returnStatement.getLine() + ", void method has no return statement", null);
				}
			}
			else {
				if(currentMethod.getType() != Tab.noType) {
					report_error("Error on line " + returnStatement.getLine() + ", method must be declared as void", null);
				}
				else if(currentMethod.getType().getKind() == Struct.Int && returnStatement.getExpressionOptional().obj.getType().getKind() == Struct.Enum) {
					
				}
				else if(!currentMethod.getType().compatibleWith(returnStatement.getExpressionOptional().obj.getType())) {
					report_error("Error on line " + returnStatement.getLine() + ", incompatible method type and return", null);
				}
			}
		}
	}
	
	public void visit(DesignatorValueAssign designatorValueAssign) {
		// Check if left value is Variable/Element/Field
		if(designatorValueAssign.getDesignator().obj.getKind() != Obj.Var
				&& designatorValueAssign.getDesignator().obj.getKind() != Obj.Elem
				&& designatorValueAssign.getDesignator().obj.getKind() != Obj.Fld) {
			report_error("Error on line " + designatorValueAssign.getLine() + "no variable/element/field found", null);
		}
		else if(designatorValueAssign.getDesignator().obj.getKind() == Obj.Elem) {
			if(!designatorValueAssign.getExpression().obj.getType().assignableTo(designatorValueAssign.getDesignator().obj.getType())) {
				report_error("Error on line " + designatorValueAssign.getLine() + ", incompatible types", null);
			}
		}
		else if(designatorValueAssign.getExpression().obj.getKind() == Obj.Elem) {
			if(designatorValueAssign.getDesignator().obj.getKind() == Obj.Elem) {
				if(!designatorValueAssign.getExpression().obj.getType().getElemType().assignableTo(designatorValueAssign.getDesignator().obj.getType().getElemType())) {
					report_error("Error on line " + designatorValueAssign.getLine() + ", incompatible types", null);
				}
			}
			else if(designatorValueAssign.getDesignator().obj.getType().getKind() == Struct.Array) {
				if(!designatorValueAssign.getExpression().obj.getType().getElemType().assignableTo(designatorValueAssign.getDesignator().obj.getType().getElemType())) {
					report_error("Error on line " + designatorValueAssign.getLine() + ", incompatible types", null);
				}
			}
		}
		else if(designatorValueAssign.getDesignator().obj.getType().getKind() == Struct.Enum
				&& designatorValueAssign.getExpression().obj.getType().getKind() == Struct.Int) {
			if(!isEnumEquals(designatorValueAssign.getDesignator().obj, designatorValueAssign.getExpression().obj)) {
				report_error("Error on line " + designatorValueAssign.getLine() + ", incompatible types", null);
			}
		}
		else if(!designatorValueAssign.getExpression().obj.getType().assignableTo(designatorValueAssign.getDesignator().obj.getType())) {
			report_error("Error on line " + designatorValueAssign.getLine() + ", incompatible types", null);
		}
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {		
		// Check if its variable/element/field
		if((designatorIncrement.getDesignator().obj.getKind() != Obj.Var) && (designatorIncrement.getDesignator().obj.getKind() != Obj.Elem) && (designatorIncrement.getDesignator().obj.getKind() != Obj.Fld)) {
			report_error("Error on line " + designatorIncrement.getLine() + ", not variable", null);			
		}
		// Check if its int type
		else if(designatorIncrement.getDesignator().obj.getType().getKind() != Struct.Int) {
			report_error("Error on line " + designatorIncrement.getLine() + ", variable " + designatorIncrement.getDesignator().obj.getName() + " not type of int", null);	
		}
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		// Check if its variable/element/field
		if((designatorDecrement.getDesignator().obj.getKind() != Obj.Var) && (designatorDecrement.getDesignator().obj.getKind() != Obj.Elem) && (designatorDecrement.getDesignator().obj.getKind() != Obj.Fld)) {
			report_error("Error on line " + designatorDecrement.getLine() + ", not variable", null);			
		}
		// Check if its int type
		else if(designatorDecrement.getDesignator().obj.getType().getKind() != Struct.Int) {
			report_error("Error on line " + designatorDecrement.getLine() + ", variable " + designatorDecrement.getDesignator().obj.getName() + " not type of int", null);	
		}
	}
	
	public void visit(DesignatorMethod designatorMethod) {
		Obj method = callMethods.pop();
		int methodActParsNumber = callMethodsParamsNumber.pop();
		
		if(method.getKind() != Obj.Meth) {
			report_error("Error on line " + designatorMethod.getLine() + ", invalid method", null);
		}
		else {
			int methodFormParsNumber = methodPars.get(method.getName()).size();
			if(methodActParsNumber != methodFormParsNumber) {
				report_error("Error on line " + designatorMethod.getLine() + ", invalid number of parameters", null);
			}
		}
	}
	
	public void visit(MethodCheck methodCheck) {
		DesignatorMethod designatorMethod = (DesignatorMethod)methodCheck.getParent();
		Obj designator = designatorMethod.getDesignator().obj;
		
		if(designator.getKind() != Obj.Meth) {
			report_error("Error on line " + methodCheck.getLine() + ", not method", null);
		}
		else {
			callMethods.push(designator);
			callMethodsParamsNumber.push(0);
		}
	}
	
	public void visit(ActParsDefinition actParsDefinition) {
		Obj method = callMethods.pop();
		if(method.getKind() != Obj.Meth) {
			callMethods.push(method);
			report_error("Error on line " + actParsDefinition.getLine() + ", invalid method", null);
		}
		else {
			int counter = callMethodsParamsNumber.pop();
			if(counter < methodPars.get(method.getName()).size()) {
				Obj formPars = methodPars.get(method.getName()).get(counter);
				
				if(formPars.getType().getKind() == Struct.Int && actParsDefinition.getExpression().obj.getType().getKind() == Struct.Enum)  {
					
				}
				else if(!actParsDefinition.getExpression().obj.getType().assignableTo(formPars.getType())) {
					report_error("Error on line " + actParsDefinition.getLine() + ", incompatible params type", null);
				}
			}
			
			counter++;
			callMethods.push(method);
			callMethodsParamsNumber.push(counter);
		}
	}
	
	public void visit(DesignatorAccess designatorAccess) {
		designatorAccess.obj = Tab.noObj;
		String enumName = designatorAccess.getLabelEnumName();
		Obj enumType = Tab.find(enumName);
		
		if(enumType == Tab.noObj) {
			report_error("Error on line " + designatorAccess.getLine() + ", not enum type.", null);
		}
		else if(enumType.getType().getKind() != Struct.Enum) {
			report_error("Error on line " + designatorAccess.getLine() + ", not enum type.", null);
		}
		else if(!globalEnums.containsValue(enumType)) {
			report_error("Error on line " + designatorAccess.getLine() + ", invalid enum type.", null);
		}
		else {
			Iterator<Obj> iterator = enumType.getLocalSymbols().iterator();
			while(iterator.hasNext()) {
				Obj current = iterator.next();
				
				if(current.getName().equals(enumType.getName() + "." + designatorAccess.getLabelEnumField())) {
					designatorAccess.obj = new Obj(Obj.Con, enumType.getName() + "." + designatorAccess.getLabelEnumField(), current.getType(), current.getAdr(), Obj.NO_VALUE);
					return;
				}
			}

			report_error("Error on line " + designatorAccess.getLine() + ", invalid enum value.", null);
		}
	}
	
	public void visit(DesignatorArray designatorArray) {
		// If variable is not declared
		String arrayName = designatorArray.getArrayStart().obj.getName();
		if(Tab.find(arrayName) == Tab.noObj) {
			report_error("Error on line " + designatorArray.getLine() + ", variable " + arrayName + " not declared", null);
		}
		// If its not array type
		else if(designatorArray.getArrayStart().obj.getType().getKind() != Struct.Array) {
			report_error("Error on line " + designatorArray.getLine() + ", variable " + arrayName + " not an array", null);	
		}
		// If its not int as index
		else if(designatorArray.getExpression().obj.getType().getKind() != Struct.Int
				&& designatorArray.getExpression().obj.getType().getKind() != Struct.Enum) {
			report_error("Error on line " + designatorArray.getLine() + ", index not an integer", null);
		}
		else {
			designatorArray.obj = new Obj(Obj.Elem, arrayName, designatorArray.getArrayStart().obj.getType().getElemType());
		}
	}

	public void visit(SimpleDesignator simpleDesignator) {
		simpleDesignator.obj = Tab.find(simpleDesignator.getLabelDesignatorName());
		if(simpleDesignator.obj.equals(Tab.noObj)) {
			report_error("Error on line " + simpleDesignator.getLine() + ", " + simpleDesignator.getLabelDesignatorName() + " nije pronadjen", null);
		}
	}
	
	public void visit(SingleExpression singleExpression) {
		singleExpression.obj = singleExpression.getTerm().obj;
	}

	public void visit(SubExpressionTerm subExpressionTerm) {
		if(subExpressionTerm.getTerm().obj.getType().getKind() != Struct.Int
				&& subExpressionTerm.getTerm().obj.getType().getKind() != Struct.Enum) {
			report_error("Error on line " + subExpressionTerm.getLine() + ", not int type", null);
		}
		subExpressionTerm.obj = subExpressionTerm.getTerm().obj;
	}
	
	public void visit(MultiExpressionTerm multiExpressionTerm) {
		if(multiExpressionTerm.getExpression().obj.getType().getKind() != Struct.Int
				&& multiExpressionTerm.getExpression().obj.getType().getKind() != Struct.Enum
				&& multiExpressionTerm.getTerm().obj.getType().getKind() != Struct.Int
				&& multiExpressionTerm.getTerm().obj.getType().getKind() != Struct.Enum) {
					report_error("Error on line " + multiExpressionTerm.getLine() + ", types are incompatible", null);
				}
		
		if(multiExpressionTerm.getExpression().obj.getType().getKind() == Struct.Int) {
			multiExpressionTerm.obj = multiExpressionTerm.getExpression().obj;
		}
		else {
			multiExpressionTerm.obj = multiExpressionTerm.getTerm().obj;
		}
	}
	
	public void visit(SingleTerm singleTerm) {
		singleTerm.obj = singleTerm.getFactor().obj;
	}
	
	public void visit(MultiTerm multiTerm) {
		if(multiTerm.getTerm().obj.getType().getKind() == Struct.Array
			&& multiTerm.getTerm().obj.getType().getElemType().getKind() != Struct.Int) {
				report_error("Error on line " + multiTerm.getLine() + ", types are incompatible", null);
		}
		else if(multiTerm.getFactor().obj.getType().getKind() == Struct.Array
				&& multiTerm.getFactor().obj.getType().getElemType().getKind() != Struct.Int) {
			report_error("Error on line " + multiTerm.getLine() + ", types are incompatible", null);
		}
		else if (multiTerm.getTerm().obj.getType().getKind() != Struct.Int
				&& multiTerm.getTerm().obj.getType().getKind() != Struct.Enum
				&& multiTerm.getFactor().obj.getType().getKind() != Struct.Int
				&& multiTerm.getFactor().obj.getType().getKind() != Struct.Enum) {
			report_error("Error on line " + multiTerm.getLine() + ", types are incompatible", null);
		}
		
		if(multiTerm.getTerm().obj.getType().equals(Tab.intType)) {
			multiTerm.obj = multiTerm.getTerm().obj;
		}
		else {
			multiTerm.obj = multiTerm.getFactor().obj;
		}
	}
	
	public void visit(MethodFactor methodFactor) {
		methodFactor.obj = methodFactor.getDesignator().obj;
	}
	
	public void visit(SingleFactorMethodActParsOptional singleFactorMethodActParsOptional) {
		Obj method = callMethods.pop();
		int methodActParsNumber = callMethodsParamsNumber.pop();
		
		if(method.getKind() != Obj.Meth) {
			report_error("Error on line " + singleFactorMethodActParsOptional.getLine() + ", invalid method", null);
		}
		else {
			int methodFormParsNumber = methodPars.get(method.getName()).size();
			if(methodActParsNumber != methodFormParsNumber) {
				report_error("Error on line " + singleFactorMethodActParsOptional.getLine() + ", invalid number of parameters", null);
			}
			singleFactorMethodActParsOptional.obj = new Obj(Obj.Meth, "", Tab.noType);
		}
	}
	
	public void visit(FactorMethodCheck factorMethodCheck) {
		SingleFactorMethodActParsOptional parentSingleFactorMethodActParsOptional = (SingleFactorMethodActParsOptional)factorMethodCheck.getParent();
		MethodFactor designatorMethod = (MethodFactor)parentSingleFactorMethodActParsOptional.getParent();
		Obj designator = designatorMethod.getDesignator().obj;
		
		if(designator.getKind() != Obj.Meth) {
			report_error("Error on line " + factorMethodCheck.getLine() + ", not method", null);
		}
		else {
			callMethods.push(designator);
			callMethodsParamsNumber.push(0);
		}
	}
	
	public void visit(NoneFactorMethodActParsOptional noneFactorMethodActParsOptional ) {
		noneFactorMethodActParsOptional.obj = null;
	}
	
	public void visit(ConstantFactor constantFactor) {
		constantFactor.obj = constantFactor.getConstantValue().obj;
	}
	
	public void visit(ExpressionFactor expressionFactor) {
		expressionFactor.obj = expressionFactor.getExpression().obj;
	}
	
	public void visit(NewFactorArray newFactorArray) {
		if(newFactorArray.getExpression().obj.getType().getKind() != Struct.Int
			&& newFactorArray.getExpression().obj.getType().getKind() != Struct.Enum) {
			report_error("Error on line " + newFactorArray.getLine() + ", expression must be type of int", null);
		}
		
		Struct array = new Struct(Struct.Array, currentType);
		newFactorArray.obj = new Obj(Obj.Type, "NewArray", array);
	}

	public void visit(ArrayStart arrayStart) {
		arrayStart.obj = Tab.find(arrayStart.getLabelArrayName());
		if(arrayStart.obj == Tab.noObj) {
			report_error("Error on line " + arrayStart.getLine() + ", variable " + arrayStart.getLabelArrayName() +" not declared", null);
		}
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
}