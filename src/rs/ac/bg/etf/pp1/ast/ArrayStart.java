// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ArrayStart implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String LabelArrayName;

    public ArrayStart (String LabelArrayName) {
        this.LabelArrayName=LabelArrayName;
    }

    public String getLabelArrayName() {
        return LabelArrayName;
    }

    public void setLabelArrayName(String LabelArrayName) {
        this.LabelArrayName=LabelArrayName;
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
        buffer.append("ArrayStart(\n");

        buffer.append(" "+tab+LabelArrayName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayStart]");
        return buffer.toString();
    }
}
