// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ConstantDefinition implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String LabelConstantName;
    private Assignop Assignop;
    private ConstantValue ConstantValue;

    public ConstantDefinition (String LabelConstantName, Assignop Assignop, ConstantValue ConstantValue) {
        this.LabelConstantName=LabelConstantName;
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.ConstantValue=ConstantValue;
        if(ConstantValue!=null) ConstantValue.setParent(this);
    }

    public String getLabelConstantName() {
        return LabelConstantName;
    }

    public void setLabelConstantName(String LabelConstantName) {
        this.LabelConstantName=LabelConstantName;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public ConstantValue getConstantValue() {
        return ConstantValue;
    }

    public void setConstantValue(ConstantValue ConstantValue) {
        this.ConstantValue=ConstantValue;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Assignop!=null) Assignop.accept(visitor);
        if(ConstantValue!=null) ConstantValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(ConstantValue!=null) ConstantValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(ConstantValue!=null) ConstantValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantDefinition(\n");

        buffer.append(" "+tab+LabelConstantName);
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstantValue!=null)
            buffer.append(ConstantValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantDefinition]");
        return buffer.toString();
    }
}
