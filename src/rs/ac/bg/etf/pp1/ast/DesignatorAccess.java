// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAccess extends Designator {

    private String LabelEnumName;
    private String LabelEnumField;

    public DesignatorAccess (String LabelEnumName, String LabelEnumField) {
        this.LabelEnumName=LabelEnumName;
        this.LabelEnumField=LabelEnumField;
    }

    public String getLabelEnumName() {
        return LabelEnumName;
    }

    public void setLabelEnumName(String LabelEnumName) {
        this.LabelEnumName=LabelEnumName;
    }

    public String getLabelEnumField() {
        return LabelEnumField;
    }

    public void setLabelEnumField(String LabelEnumField) {
        this.LabelEnumField=LabelEnumField;
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
        buffer.append("DesignatorAccess(\n");

        buffer.append(" "+tab+LabelEnumName);
        buffer.append("\n");

        buffer.append(" "+tab+LabelEnumField);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAccess]");
        return buffer.toString();
    }
}
