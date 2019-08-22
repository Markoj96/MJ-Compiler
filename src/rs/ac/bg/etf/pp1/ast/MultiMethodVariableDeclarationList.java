// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MultiMethodVariableDeclarationList extends MethodVariableDeclarationList {

    private MethodVariableDeclarationList MethodVariableDeclarationList;
    private VariableDeclaration VariableDeclaration;

    public MultiMethodVariableDeclarationList (MethodVariableDeclarationList MethodVariableDeclarationList, VariableDeclaration VariableDeclaration) {
        this.MethodVariableDeclarationList=MethodVariableDeclarationList;
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.setParent(this);
        this.VariableDeclaration=VariableDeclaration;
        if(VariableDeclaration!=null) VariableDeclaration.setParent(this);
    }

    public MethodVariableDeclarationList getMethodVariableDeclarationList() {
        return MethodVariableDeclarationList;
    }

    public void setMethodVariableDeclarationList(MethodVariableDeclarationList MethodVariableDeclarationList) {
        this.MethodVariableDeclarationList=MethodVariableDeclarationList;
    }

    public VariableDeclaration getVariableDeclaration() {
        return VariableDeclaration;
    }

    public void setVariableDeclaration(VariableDeclaration VariableDeclaration) {
        this.VariableDeclaration=VariableDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.accept(visitor);
        if(VariableDeclaration!=null) VariableDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.traverseTopDown(visitor);
        if(VariableDeclaration!=null) VariableDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodVariableDeclarationList!=null) MethodVariableDeclarationList.traverseBottomUp(visitor);
        if(VariableDeclaration!=null) VariableDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiMethodVariableDeclarationList(\n");

        if(MethodVariableDeclarationList!=null)
            buffer.append(MethodVariableDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VariableDeclaration!=null)
            buffer.append(VariableDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiMethodVariableDeclarationList]");
        return buffer.toString();
    }
}
