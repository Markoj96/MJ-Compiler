// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ProgramStart implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String LabelProgramName;

    public ProgramStart (String LabelProgramName) {
        this.LabelProgramName=LabelProgramName;
    }

    public String getLabelProgramName() {
        return LabelProgramName;
    }

    public void setLabelProgramName(String LabelProgramName) {
        this.LabelProgramName=LabelProgramName;
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
        buffer.append("ProgramStart(\n");

        buffer.append(" "+tab+LabelProgramName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramStart]");
        return buffer.toString();
    }
}
