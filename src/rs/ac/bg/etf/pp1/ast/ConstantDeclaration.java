// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ConstantDeclaration implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConstantDeclarationList ConstantDeclarationList;

    public ConstantDeclaration (Type Type, ConstantDeclarationList ConstantDeclarationList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstantDeclarationList=ConstantDeclarationList;
        if(ConstantDeclarationList!=null) ConstantDeclarationList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstantDeclarationList getConstantDeclarationList() {
        return ConstantDeclarationList;
    }

    public void setConstantDeclarationList(ConstantDeclarationList ConstantDeclarationList) {
        this.ConstantDeclarationList=ConstantDeclarationList;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstantDeclarationList!=null) ConstantDeclarationList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstantDeclarationList!=null) ConstantDeclarationList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstantDeclarationList!=null) ConstantDeclarationList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstantDeclarationList!=null)
            buffer.append(ConstantDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantDeclaration]");
        return buffer.toString();
    }
}
