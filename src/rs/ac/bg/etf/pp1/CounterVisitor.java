package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.NormalFormPars;
import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	int count;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParsVisitor extends CounterVisitor {
		public void visit(NormalFormPars normalFormPars) {
			count++;
		}
		
		public void visit(ArrayFormPars arrayFormPars) {
			count++;
		}
	}
	
	public static class VariableDeclarationVisitor extends CounterVisitor {
		public void visit(NormalVariableDefinition normalVariableDefinition) {
			count++;
		}
		
		public void visit(ArrayVariableDefinition arrayVariableDefinition) {
			count++;
		}
	}
}
