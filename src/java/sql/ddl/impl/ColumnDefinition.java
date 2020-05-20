package sql.ddl.impl;

public class ColumnDefinition {

    String name;
    String type;
    String constraint;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getConstraint() {
        return constraint;
    }

    public ColumnDefinition(String name, String type, String constraint) {
        this.name = name;
        this.type = type;
        this.constraint = constraint;
    }


}
