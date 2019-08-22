// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorMethod extends DesignatorStatement {

    private Designator Designator;
    private MethodCheck MethodCheck;
    private ActParsOptional ActParsOptional;

    public DesignatorMethod (Designator Designator, MethodCheck MethodCheck, ActParsOptional ActParsOptional) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.MethodCheck=MethodCheck;
        if(MethodCheck!=null) MethodCheck.setParent(this);
        this.ActParsOptional=ActParsOptional;
        if(ActParsOptional!=null) ActParsOptional.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public MethodCheck getMethodCheck() {
        return MethodCheck;
    }

    public void setMethodCheck(MethodCheck MethodCheck) {
        this.MethodCheck=MethodCheck;
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
        if(Designator!=null) Designator.accept(visitor);
        if(MethodCheck!=null) MethodCheck.accept(visitor);
        if(ActParsOptional!=null) ActParsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(MethodCheck!=null) MethodCheck.traverseTopDown(visitor);
        if(ActParsOptional!=null) ActParsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(MethodCheck!=null) MethodCheck.traverseBottomUp(visitor);
        if(ActParsOptional!=null) ActParsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorMethod(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodCheck!=null)
            buffer.append(MethodCheck.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsOptional!=null)
            buffer.append(ActParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorMethod]");
        return buffer.toString();
    }
}
