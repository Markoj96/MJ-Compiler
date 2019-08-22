// generated with ast extension for cup
// version 0.8
// 22/7/2019 23:12:12


package rs.ac.bg.etf.pp1.ast;

public class MultiFormParsList extends FormParsList {

    private FormParsList FormParsList;
    private FormParsDefinition FormParsDefinition;

    public MultiFormParsList (FormParsList FormParsList, FormParsDefinition FormParsDefinition) {
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.FormParsDefinition=FormParsDefinition;
        if(FormParsDefinition!=null) FormParsDefinition.setParent(this);
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
    }

    public FormParsDefinition getFormParsDefinition() {
        return FormParsDefinition;
    }

    public void setFormParsDefinition(FormParsDefinition FormParsDefinition) {
        this.FormParsDefinition=FormParsDefinition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(FormParsDefinition!=null) FormParsDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(FormParsDefinition!=null) FormParsDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(FormParsDefinition!=null) FormParsDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiFormParsList(\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsDefinition!=null)
            buffer.append(FormParsDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiFormParsList]");
        return buffer.toString();
    }
}
