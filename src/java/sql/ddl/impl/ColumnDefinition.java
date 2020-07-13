package sql.ddl.impl;

import sql.exceptions.BadParamException;

import static sql.constants.ParametrizedExceptionMessages.EMPTY_QUERY_PARAM;
import static sql.constants.ParametrizedExceptionMessages.NULL_QUERY_PARAM;
import static sql.constants.QueryConstants.SPACE;

public class ColumnDefinition {

    private String columnQuery;

    public ColumnDefinition(String name, String type, String constraint) {
        checkParams(name, type, constraint);
        columnQuery = name + SPACE + type;
        addConstraint(constraint);
    }

    public ColumnDefinition(String name, String type) {
        this(name, type, "");
    }

    public ColumnDefinition(String name) {
        columnQuery = name;
    }

    public String getColumnQuery() {
        return columnQuery;
    }

    private void checkParams(String name, String type, String constraint) {
        if (name == null)
            throw new BadParamException(String.format(NULL_QUERY_PARAM, "name"));
        if (name.trim().isEmpty())
            throw new BadParamException(String.format(EMPTY_QUERY_PARAM, "name"));
        if (type == null)
            throw new BadParamException(String.format(NULL_QUERY_PARAM, "type"));
        if (type.trim().isEmpty())
            throw new BadParamException(String.format(EMPTY_QUERY_PARAM, "type"));
    }

    public void addConstraint(String constraint) {
        if (constraint == null || constraint.isEmpty())
            return;
        columnQuery += SPACE + constraint;
    }

    public void addType(String type) {
        if (type == null || type.isEmpty())
            return;
        columnQuery += SPACE + type;
    }
}
