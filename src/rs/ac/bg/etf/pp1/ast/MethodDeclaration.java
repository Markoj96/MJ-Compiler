// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclaration implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodStart MethodStart;
    private FormParsOptional FormParsOptional;
    private MethodVariableDeclarationList MethodVariableDeclarationList;
    private StatementList StatementList;

    public MethodDeclaration (MethodStart MethodStart, FormParsOptional FormParsOptional, MethodVariableDeclarationList MethodVariableDeclarationList, StatementList StatementList) {
        this.MethodStart=MethodStart;
        if(MethodStart!=null) MethodStart.setParent(this);
        this.FormParsOptional=FormParsOptional;
        if(FormParsOptional!=null) FormParsOptional.setParent(this);
        this.MethodVariableDeclarationList=MethodVariableDeclarationList;
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodStart getMethodStart() {
        return MethodStart;
    }

    public void setMethodStart(MethodStart MethodStart) {
        this.MethodStart=MethodStart;
    }

    public FormParsOptional getFormParsOptional() {
        return FormParsOptional;
    }

    public void setFormParsOptional(FormParsOptional FormParsOptional) {
        this.FormParsOptional=FormParsOptional;
    }

    public MethodVariableDeclarationList getMethodVariableDeclarationList() {
        return MethodVariableDeclarationList;
    }

    public void setMethodVariableDeclarationList(MethodVariableDeclarationList MethodVariableDeclarationList) {
        this.MethodVariableDeclarationList=MethodVariableDeclarationList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
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
        if(MethodStart!=null) MethodStart.accept(visitor);
        if(FormParsOptional!=null) FormParsOptional.accept(visitor);
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodStart!=null) MethodStart.traverseTopDown(visitor);
        if(FormParsOptional!=null) FormParsOptional.traverseTopDown(visitor);
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodStart!=null) MethodStart.traverseBottomUp(visitor);
        if(FormParsOptional!=null) FormParsOptional.traverseBottomUp(visitor);
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclaration(\n");

        if(MethodStart!=null)
            buffer.append(MethodStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsOptional!=null)
            buffer.append(FormParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodVariableDeclarationList!=null)
            buffer.append(MethodVariableDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclaration]");
        return buffer.toString();
    }
}
