// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ArrayVariableDefinition extends VariableDefinition {

    private String LabelArrayName;

    public ArrayVariableDefinition (String LabelArrayName) {
        this.LabelArrayName=LabelArrayName;
    }

    public String getLabelArrayName() {
        return LabelArrayName;
    }

    public void setLabelArrayName(String LabelArrayName) {
        this.LabelArrayName=LabelArrayName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayVariableDefinition(\n");

        buffer.append(" "+tab+LabelArrayName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayVariableDefinition]");
        return buffer.toString();
    }
}
