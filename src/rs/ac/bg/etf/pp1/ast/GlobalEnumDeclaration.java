// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class GlobalEnumDeclaration extends GlobalDeclaration {

    private EnumDeclaration EnumDeclaration;

    public GlobalEnumDeclaration (EnumDeclaration EnumDeclaration) {
        this.EnumDeclaration=EnumDeclaration;
        if(EnumDeclaration!=null) EnumDeclaration.setParent(this);
    }

    public EnumDeclaration getEnumDeclaration() {
        return EnumDeclaration;
    }

    public void setEnumDeclaration(EnumDeclaration EnumDeclaration) {
        this.EnumDeclaration=EnumDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumDeclaration!=null) EnumDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclaration!=null) EnumDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclaration!=null) EnumDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalEnumDeclaration(\n");

        if(EnumDeclaration!=null)
            buffer.append(EnumDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalEnumDeclaration]");
        return buffer.toString();
    }
}
