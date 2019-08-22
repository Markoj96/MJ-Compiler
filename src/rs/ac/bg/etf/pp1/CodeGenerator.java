package rs.ac.bg.etf.pp1;

import java.util.ArrayDeque;
import java.util.Deque;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	int mainPC;
	boolean currentMethodTypeVoid;
	
	Deque<Integer> queueAddop;
	Deque<Integer> queueMulop;
	
	public CodeGenerator() {
		currentMethodTypeVoid = false;
		
		queueAddop = new ArrayDeque<>();
		queueMulop = new ArrayDeque<>();
		
		Obj ord = Tab.find("ord");
		ord.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj chr = Tab.find("chr");
		chr.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj len = Tab.find("len");
		len.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(NumConstantValue numConstantValue) {
		Code.load(numConstantValue.obj);
	}
	
	public void visit(CharConstantValue charConstantValue) {
		Code.load(charConstantValue.obj);
	}
	
	public void visit(BoolConstantValue boolConstantValue) {
		Code.load(boolConstantValue.obj);
	}
	
	public void visit(UnitializedEnumDefinition unitializedEnumDefinition) {
		Code.load(unitializedEnumDefinition.obj);
	}

	public void visit(InitializedEnumDefinition initializedEnumDefinition) {
		Code.load(initializedEnumDefinition.obj);
	}
	
	public void visit(MethodDeclaration methodDeclaration) {
		if (currentMethodTypeVoid == false) {
			Code.put(Code.trap);
			Code.put(Code.const_1);
		}
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MethodTypeVoid methodTypeVoid) {
		currentMethodTypeVoid = true;
	}
	
	public void visit(MethodTypeOther methodTypeOther) {
		currentMethodTypeVoid = false;
	}
	
	public void visit(MethodName methodName) {
		if("main".equalsIgnoreCase(methodName.getLabelMethodName())) {
			mainPC = Code.pc;
		}
		
		methodName.obj.setAdr(Code.pc);
		SyntaxNode methodNode = methodName.getParent();
		
		VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
		methodNode.traverseTopDown(variableDeclarationVisitor);
		
		FormParsVisitor formParsVisitor = new FormParsVisitor();
		methodNode.traverseTopDown(formParsVisitor);
		
		Code.put(Code.enter);
		Code.put(methodName.obj.getLevel());
		Code.put(methodName.obj.getLocalSymbols().size());
	}
	
	public void visit(ReadStatement readStatement) {
		Obj designator = readStatement.getDesignator().obj;
		
		if(designator.getType().getKind() != Struct.Char) {
			Code.put(Code.read);
		}
		else {
			Code.put(Code.bread);
		}
		Code.store(designator);
	}
	
	public void visit(PrintStatementWithoutArguments printStatementWithoutArguments) {
		if(printStatementWithoutArguments.getExpression().obj.getType().getKind() != Struct.Char) {
			Code.put(Code.const_5);
			Code.put(Code.print);
		}
		else {
			Code.put(Code.const_3);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(PrintStatementWithArguments printStatementWithArguments) {
		if(printStatementWithArguments.getExpression().obj.getType().getKind() != Struct.Char) {
			Code.loadConst(printStatementWithArguments.getLabelNumConst());
			Code.put(Code.print);
		}
		else {
			Code.loadConst(printStatementWithArguments.getLabelNumConst());
			Code.put(Code.bprint);
		}
	}

	public void visit(ReturnStatement returnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorValueAssign designatorValueAssign) {
		Obj designator = designatorValueAssign.getDesignator().obj;
		
		Code.store(designator);
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {
		Obj designator = designatorIncrement.getDesignator().obj;
			
		if(designator.getType().getKind() == Struct.Array) {
			if(designator.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.dup2);
				Code.put(Code.baload);
				Code.put(Code.const_1);
				Code.put(Code.add);
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.dup2);
				Code.put(Code.aload);;
				Code.put(Code.const_1);
				Code.put(Code.add);
				Code.put(Code.astore);
			}
		}
		else {
			Code.put(Code.dup2);
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(designator);
		}
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		Obj designator = designatorDecrement.getDesignator().obj;
			
		if(designator.getType().getKind() == Struct.Array) {
			if(designator.getType().getElemType().getKind() == Struct.Char) {
				Code.put(Code.dup2);
				Code.put(Code.baload);
				Code.put(Code.const_1);
				Code.put(Code.sub);
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.dup2);
				Code.put(Code.aload);;
				Code.put(Code.const_1);
				Code.put(Code.sub);
				Code.put(Code.astore);
			}
		}
		else {
			Code.put(Code.dup);
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(designator);
		}
	}
	
	public void visit(DesignatorMethod designatorMethod) {
		Obj designator = designatorMethod.getDesignator().obj;
		
		int offset = designator.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(DesignatorAccess designatorAccess) {
		Obj designator = designatorAccess.obj;

		Code.load(designator);
	}
	
	public void visit(DesignatorArray designatorArray) {
		SyntaxNode syntaxNode = designatorArray.getParent();
		
		while(syntaxNode != null) {
			if(syntaxNode instanceof Expression) {
				if(((Expression) syntaxNode).obj.getType().getKind() == Struct.Char) {
					Code.put(Code.baload);
				}
				else {
					Code.put(Code.aload);	
				}
				
				return;
			}	
			
			syntaxNode = syntaxNode.getParent();
		}
	}
	
	public void visit(ArrayStart arrayStart) {
		Obj designator = arrayStart.obj;
		
		Code.load(designator);
	}
	
	public void visit(SimpleDesignator simpleDesignator) {
		Obj designator = simpleDesignator.obj;
		
		if(designator.getKind() != Obj.Type && designator.getKind() != Obj.Meth) {
			Code.load(designator);
		}
	}
	
	public void visit(SubExpressionTerm subExpressionTerm) {
		Code.put(Code.neg);
	}
	
	public void visit(MultiExpressionTerm multiExpressionTerm) {
		Code.put(queueAddop.pollLast());
	}
	
	public void visit(MultiTerm multiTerm) {
		Code.put(queueMulop.pollLast());
	}

	public void visit(MethodFactor methodFactor) {
		Obj designator = methodFactor.getDesignator().obj;

		if(methodFactor.getFactorMethodActParsOptional().obj != null) {
			int offset = designator.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	public void visit(NewFactorArray newFactorArray) {
		if(newFactorArray.obj.getType().getKind() == Struct.Array) {
			if(newFactorArray.obj.getType().getElemType().getKind() == Struct.Int || newFactorArray.obj.getType().getElemType().getKind() == Struct.Bool) {
				Code.put(Code.newarray);
				Code.put(1);
			}
			else {
				Code.put(Code.newarray);
				Code.put(0);
			}
		}
		else {
			if(newFactorArray.obj.getType().getKind() == Struct.Int || newFactorArray.obj.getType().getKind() == Struct.Bool) {
				Code.put(Code.newarray);
				Code.put(1);
			}
			else {
				Code.put(Code.newarray);
				Code.put(0);
			}
		}
	}
	
	public void visit(AddopAdd addopAdd) {
		queueAddop.addLast(Code.add);
	}
	
	public void visit(AddopSub addopSub) {
		queueAddop.addLast(Code.sub);
	}
	
	public void visit(MulopMul mulopMul) {
		queueMulop.addLast(Code.mul);
	}
	
	public void visit(MulopDiv mulopDiv) {
		queueMulop.addLast(Code.div);
	}
	
	public void visit(MulopMod mulopMod) {
		queueMulop.addLast(Code.rem);
	}
}
