// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class SingleActParsList extends ActParsList {

    private ActParsDefinition ActParsDefinition;

    public SingleActParsList (ActParsDefinition ActParsDefinition) {
        this.ActParsDefinition=ActParsDefinition;
        if(ActParsDefinition!=null) ActParsDefinition.setParent(this);
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
        if(ActParsDefinition!=null) ActParsDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsDefinition!=null) ActParsDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsDefinition!=null) ActParsDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleActParsList(\n");

        if(ActParsDefinition!=null)
            buffer.append(ActParsDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleActParsList]");
        return buffer.toString();
    }
}
