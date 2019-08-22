// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MultiVariableDeclarationList extends VariableDeclarationList {

    private VariableDeclarationList VariableDeclarationList;
    private VariableDefinition VariableDefinition;

    public MultiVariableDeclarationList (VariableDeclarationList VariableDeclarationList, VariableDefinition VariableDefinition) {
        this.VariableDeclarationList=VariableDeclarationList;
        if(VariableDeclarationList!=null) VariableDeclarationList.setParent(this);
        this.VariableDefinition=VariableDefinition;
        if(VariableDefinition!=null) VariableDefinition.setParent(this);
    }

    public VariableDeclarationList getVariableDeclarationList() {
        return VariableDeclarationList;
    }

    public void setVariableDeclarationList(VariableDeclarationList VariableDeclarationList) {
        this.VariableDeclarationList=VariableDeclarationList;
    }

    public VariableDefinition getVariableDefinition() {
        return VariableDefinition;
    }

    public void setVariableDefinition(VariableDefinition VariableDefinition) {
        this.VariableDefinition=VariableDefinition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VariableDeclarationList!=null) VariableDeclarationList.accept(visitor);
        if(VariableDefinition!=null) VariableDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VariableDeclarationList!=null) VariableDeclarationList.traverseTopDown(visitor);
        if(VariableDefinition!=null) VariableDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VariableDeclarationList!=null) VariableDeclarationList.traverseBottomUp(visitor);
        if(VariableDefinition!=null) VariableDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiVariableDeclarationList(\n");

        if(VariableDeclarationList!=null)
            buffer.append(VariableDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VariableDefinition!=null)
            buffer.append(VariableDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiVariableDeclarationList]");
        return buffer.toString();
    }
}
