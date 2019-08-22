// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MultiActParsList extends ActParsList {

    private ActParsList ActParsList;
    private ActParsDefinition ActParsDefinition;

    public MultiActParsList (ActParsList ActParsList, ActParsDefinition ActParsDefinition) {
        this.ActParsList=ActParsList;
        if(ActParsList!=null) ActParsList.setParent(this);
        this.ActParsDefinition=ActParsDefinition;
        if(ActParsDefinition!=null) ActParsDefinition.setParent(this);
    }

    public ActParsList getActParsList() {
        return ActParsList;
    }

    public void setActParsList(ActParsList ActParsList) {
        this.ActParsList=ActParsList;
    }

    public ActParsDefinition getActParsDefinition() {
        return ActParsDefinition;
    }

    public void setActParsDefinition(ActParsDefinition ActParsDefinition) {
        this.ActParsDefinition=ActParsDefinition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsList!=null) ActParsList.accept(visitor);
        if(ActParsDefinition!=null) ActParsDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsList!=null) ActParsList.traverseTopDown(visitor);
        if(ActParsDefinition!=null) ActParsDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsList!=null) ActParsList.traverseBottomUp(visitor);
        if(ActParsDefinition!=null) ActParsDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiActParsList(\n");

        if(ActParsList!=null)
            buffer.append(ActParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsDefinition!=null)
            buffer.append(ActParsDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiActParsList]");
        return buffer.toString();
    }
}
