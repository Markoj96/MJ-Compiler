// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class SingleFactorMethodActParsOptional extends FactorMethodActParsOptional {

    private FactorMethodCheck FactorMethodCheck;
    private ActParsOptional ActParsOptional;

    public SingleFactorMethodActParsOptional (FactorMethodCheck FactorMethodCheck, ActParsOptional ActParsOptional) {
        this.FactorMethodCheck=FactorMethodCheck;
        if(FactorMethodCheck!=null) FactorMethodCheck.setParent(this);
        this.ActParsOptional=ActParsOptional;
        if(ActParsOptional!=null) ActParsOptional.setParent(this);
    }

    public FactorMethodCheck getFactorMethodCheck() {
        return FactorMethodCheck;
    }

    public void setFactorMethodCheck(FactorMethodCheck FactorMethodCheck) {
        this.FactorMethodCheck=FactorMethodCheck;
    }

    public ActParsOptional getActParsOptional() {
        return ActParsOptional;
    }

    public void setActParsOptional(ActParsOptional ActParsOptional) {
        this.ActParsOptional=ActParsOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorMethodCheck!=null) FactorMethodCheck.accept(visitor);
        if(ActParsOptional!=null) ActParsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorMethodCheck!=null) FactorMethodCheck.traverseTopDown(visitor);
        if(ActParsOptional!=null) ActParsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorMethodCheck!=null) FactorMethodCheck.traverseBottomUp(visitor);
        if(ActParsOptional!=null) ActParsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleFactorMethodActParsOptional(\n");

        if(FactorMethodCheck!=null)
            buffer.append(FactorMethodCheck.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsOptional!=null)
            buffer.append(ActParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleFactorMethodActParsOptional]");
        return buffer.toString();
    }
}
