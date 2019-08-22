// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class NormalVariableDefinition extends VariableDefinition {

    private String LabelVariableName;

    public NormalVariableDefinition (String LabelVariableName) {
        this.LabelVariableName=LabelVariableName;
    }

    public String getLabelVariableName() {
        return LabelVariableName;
    }

    public void setLabelVariableName(String LabelVariableName) {
        this.LabelVariableName=LabelVariableName;
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
        buffer.append("NormalVariableDefinition(\n");

        buffer.append(" "+tab+LabelVariableName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NormalVariableDefinition]");
        return buffer.toString();
    }
}
