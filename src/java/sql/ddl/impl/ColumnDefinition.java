package sql.ddl.impl;

import sql.constants.ParametrizedExceptionMessages;
import sql.exceptions.BadParamException;

import static sql.constants.ParametrizedExceptionMessages.*;
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

    private void addConstraint(String constraint) {
        if (constraint == null || constraint.isEmpty())
            return;
        columnQuery += SPACE + constraint;
    }
}
