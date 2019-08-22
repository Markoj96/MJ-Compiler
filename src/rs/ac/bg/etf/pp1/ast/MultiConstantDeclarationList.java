// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MultiConstantDeclarationList extends ConstantDeclarationList {

    private ConstantDeclarationList ConstantDeclarationList;
    private ConstantDefinition ConstantDefinition;

    public MultiConstantDeclarationList (ConstantDeclarationList ConstantDeclarationList, ConstantDefinition ConstantDefinition) {
        this.ConstantDeclarationList=ConstantDeclarationList;
        if(ConstantDeclarationList!=null) ConstantDeclarationList.setParent(this);
        this.ConstantDefinition=ConstantDefinition;
        if(ConstantDefinition!=null) ConstantDefinition.setParent(this);
    }

    public ConstantDeclarationList getConstantDeclarationList() {
        return ConstantDeclarationList;
    }

    public void setConstantDeclarationList(ConstantDeclarationList ConstantDeclarationList) {
        this.ConstantDeclarationList=ConstantDeclarationList;
    }

    public ConstantDefinition getConstantDefinition() {
        return ConstantDefinition;
    }

    public void setConstantDefinition(ConstantDefinition ConstantDefinition) {
        this.ConstantDefinition=ConstantDefinition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantDeclarationList!=null) ConstantDeclarationList.accept(visitor);
        if(ConstantDefinition!=null) ConstantDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantDeclarationList!=null) ConstantDeclarationList.traverseTopDown(visitor);
        if(ConstantDefinition!=null) ConstantDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantDeclarationList!=null) ConstantDeclarationList.traverseBottomUp(visitor);
        if(ConstantDefinition!=null) ConstantDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiConstantDeclarationList(\n");

        if(ConstantDeclarationList!=null)
            buffer.append(ConstantDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstantDefinition!=null)
            buffer.append(ConstantDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiConstantDeclarationList]");
        return buffer.toString();
    }
}
