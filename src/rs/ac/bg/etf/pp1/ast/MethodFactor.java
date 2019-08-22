// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MethodFactor extends Factor {

    private Designator Designator;
    private FactorMethodActParsOptional FactorMethodActParsOptional;

    public MethodFactor (Designator Designator, FactorMethodActParsOptional FactorMethodActParsOptional) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactorMethodActParsOptional=FactorMethodActParsOptional;
        if(FactorMethodActParsOptional!=null) FactorMethodActParsOptional.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactorMethodActParsOptional getFactorMethodActParsOptional() {
        return FactorMethodActParsOptional;
    }

    public void setFactorMethodActParsOptional(FactorMethodActParsOptional FactorMethodActParsOptional) {
        this.FactorMethodActParsOptional=FactorMethodActParsOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactorMethodActParsOptional!=null) FactorMethodActParsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactorMethodActParsOptional!=null) FactorMethodActParsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactorMethodActParsOptional!=null) FactorMethodActParsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodFactor(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorMethodActParsOptional!=null)
            buffer.append(FactorMethodActParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodFactor]");
        return buffer.toString();
    }
}
