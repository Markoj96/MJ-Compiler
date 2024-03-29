// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class PrintStatementWithArguments extends Statement {

    private Expression Expression;
    private Integer LabelNumConst;

    public PrintStatementWithArguments (Expression Expression, Integer LabelNumConst) {
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
        this.LabelNumConst=LabelNumConst;
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public Integer getLabelNumConst() {
        return LabelNumConst;
    }

    public void setLabelNumConst(Integer LabelNumConst) {
        this.LabelNumConst=LabelNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expression!=null) Expression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatementWithArguments(\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+LabelNumConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatementWithArguments]");
        return buffer.toString();
    }
}
