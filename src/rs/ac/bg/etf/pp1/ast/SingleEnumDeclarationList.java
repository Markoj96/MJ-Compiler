// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class SingleEnumDeclarationList extends EnumDeclarationList {

    private EnumDefinition EnumDefinition;

    public SingleEnumDeclarationList (EnumDefinition EnumDefinition) {
        this.EnumDefinition=EnumDefinition;
        if(EnumDefinition!=null) EnumDefinition.setParent(this);
    }

    public EnumDefinition getEnumDefinition() {
        return EnumDefinition;
    }

    public void setEnumDefinition(EnumDefinition EnumDefinition) {
        this.EnumDefinition=EnumDefinition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumDefinition!=null) EnumDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDefinition!=null) EnumDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDefinition!=null) EnumDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleEnumDeclarationList(\n");

        if(EnumDefinition!=null)
            buffer.append(EnumDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleEnumDeclarationList]");
        return buffer.toString();
    }
}
