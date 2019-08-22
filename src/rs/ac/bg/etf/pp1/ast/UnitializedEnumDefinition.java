// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class UnitializedEnumDefinition extends EnumDefinition {

    private String LabelName;

    public UnitializedEnumDefinition (String LabelName) {
        this.LabelName=LabelName;
    }

    public String getLabelName() {
        return LabelName;
    }

    public void setLabelName(String LabelName) {
        this.LabelName=LabelName;
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
        buffer.append("UnitializedEnumDefinition(\n");

        buffer.append(" "+tab+LabelName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnitializedEnumDefinition]");
        return buffer.toString();
    }
}
