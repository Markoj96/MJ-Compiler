// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class EmptyFormParsList extends FormParsList {

    public EmptyFormParsList () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EmptyFormParsList(\n");

        buffer.append(tab);
        buffer.append(") [EmptyFormParsList]");
        return buffer.toString();
    }
}
