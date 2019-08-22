// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class InitializedEnumDefinition extends EnumDefinition {

    private String LabelName;
    private Assignop Assignop;
    private Integer LabelEnumValue;

    public InitializedEnumDefinition (String LabelName, Assignop Assignop, Integer LabelEnumValue) {
        this.LabelName=LabelName;
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.LabelEnumValue=LabelEnumValue;
    }

    public String getLabelName() {
        return LabelName;
    }

    public void setLabelName(String LabelName) {
        this.LabelName=LabelName;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Integer getLabelEnumValue() {
        return LabelEnumValue;
    }

    public void setLabelEnumValue(Integer LabelEnumValue) {
        this.LabelEnumValue=LabelEnumValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Assignop!=null) Assignop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InitializedEnumDefinition(\n");

        buffer.append(" "+tab+LabelName);
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+LabelEnumValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InitializedEnumDefinition]");
        return buffer.toString();
    }
}
