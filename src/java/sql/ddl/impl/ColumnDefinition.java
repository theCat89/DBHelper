package sql.ddl.impl;

import static sql.constants.QueryConstants.SPACE;

public class ColumnDefinition {

    private String columnQuery;

    public ColumnDefinition(String name, String type, String constraint) {
        columnQuery = name + SPACE + type + SPACE + constraint;
    }

    public ColumnDefinition(String name, String type) {
        columnQuery = name + SPACE + type;
    }

    public String getColumnQuery() {
        return columnQuery;
    }

}
