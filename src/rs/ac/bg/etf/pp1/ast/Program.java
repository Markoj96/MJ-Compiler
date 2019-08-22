// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ProgramStart ProgramStart;
    private GlobalDeclarationList GlobalDeclarationList;
    private MethodDeclarationList MethodDeclarationList;

    public Program (ProgramStart ProgramStart, GlobalDeclarationList GlobalDeclarationList, MethodDeclarationList MethodDeclarationList) {
        this.ProgramStart=ProgramStart;
        if(ProgramStart!=null) ProgramStart.setParent(this);
        this.GlobalDeclarationList=GlobalDeclarationList;
        if(GlobalDeclarationList!=null) GlobalDeclarationList.setParent(this);
        this.MethodDeclarationList=MethodDeclarationList;
        if(MethodDeclarationList!=null) MethodDeclarationList.setParent(this);
    }

    public ProgramStart getProgramStart() {
        return ProgramStart;
    }

    public void setProgramStart(ProgramStart ProgramStart) {
        this.ProgramStart=ProgramStart;
    }

    public GlobalDeclarationList getGlobalDeclarationList() {
        return GlobalDeclarationList;
    }

    public void setGlobalDeclarationList(GlobalDeclarationList GlobalDeclarationList) {
        this.GlobalDeclarationList=GlobalDeclarationList;
    }

    public MethodDeclarationList getMethodDeclarationList() {
        return MethodDeclarationList;
    }

    public void setMethodDeclarationList(MethodDeclarationList MethodDeclarationList) {
        this.MethodDeclarationList=MethodDeclarationList;
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
        if(ProgramStart!=null) ProgramStart.accept(visitor);
        if(GlobalDeclarationList!=null) GlobalDeclarationList.accept(visitor);
        if(MethodDeclarationList!=null) MethodDeclarationList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramStart!=null) ProgramStart.traverseTopDown(visitor);
        if(GlobalDeclarationList!=null) GlobalDeclarationList.traverseTopDown(visitor);
        if(MethodDeclarationList!=null) MethodDeclarationList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramStart!=null) ProgramStart.traverseBottomUp(visitor);
        if(GlobalDeclarationList!=null) GlobalDeclarationList.traverseBottomUp(visitor);
        if(MethodDeclarationList!=null) MethodDeclarationList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(ProgramStart!=null)
            buffer.append(ProgramStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalDeclarationList!=null)
            buffer.append(GlobalDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclarationList!=null)
            buffer.append(MethodDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
