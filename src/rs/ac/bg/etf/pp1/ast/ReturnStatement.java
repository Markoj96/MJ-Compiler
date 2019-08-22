// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement extends Statement {

    private ExpressionOptional ExpressionOptional;

    public ReturnStatement (ExpressionOptional ExpressionOptional) {
        this.ExpressionOptional=ExpressionOptional;
        if(ExpressionOptional!=null) ExpressionOptional.setParent(this);
    }

    public ExpressionOptional getExpressionOptional() {
        return ExpressionOptional;
    }

    public void setExpressionOptional(ExpressionOptional ExpressionOptional) {
        this.ExpressionOptional=ExpressionOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExpressionOptional!=null) ExpressionOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExpressionOptional!=null) ExpressionOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExpressionOptional!=null) ExpressionOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStatement(\n");

        if(ExpressionOptional!=null)
            buffer.append(ExpressionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement]");
        return buffer.toString();
    }
}
