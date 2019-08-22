// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class SimpleDesignator extends Designator {

    private String LabelDesignatorName;

    public SimpleDesignator (String LabelDesignatorName) {
        this.LabelDesignatorName=LabelDesignatorName;
    }

    public String getLabelDesignatorName() {
        return LabelDesignatorName;
    }

    public void setLabelDesignatorName(String LabelDesignatorName) {
        this.LabelDesignatorName=LabelDesignatorName;
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
        buffer.append("SimpleDesignator(\n");

        buffer.append(" "+tab+LabelDesignatorName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleDesignator]");
        return buffer.toString();
    }
}
