// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ConstantFactor extends Factor {

    private ConstantValue ConstantValue;

    public ConstantFactor (ConstantValue ConstantValue) {
        this.ConstantValue=ConstantValue;
        if(ConstantValue!=null) ConstantValue.setParent(this);
    }

    public ConstantValue getConstantValue() {
        return ConstantValue;
    }

    public void setConstantValue(ConstantValue ConstantValue) {
        this.ConstantValue=ConstantValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantValue!=null) ConstantValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantValue!=null) ConstantValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantValue!=null) ConstantValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantFactor(\n");

        if(ConstantValue!=null)
            buffer.append(ConstantValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantFactor]");
        return buffer.toString();
    }
}
