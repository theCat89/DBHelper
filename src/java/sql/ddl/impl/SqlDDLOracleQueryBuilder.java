package sql.ddl.impl;

import sql.ddl.interfaces.SqlAlterQueryBuilder;
import sql.ddl.interfaces.SqlCreateQueryBuilder;
import sql.ddl.interfaces.SqlDDLQueryBuilder;
import sql.ddl.interfaces.SqlDropQueryBuilder;
import sql.ddl.interfaces.column.SqlColumnBuilder;
import sql.ddl.interfaces.column.SqlColumnMiddleBuilder;
import sql.ddl.interfaces.table.SqlAsTableQueryBuilder;
import sql.ddl.interfaces.table.SqlInColumnStructureBuilder;
import sql.ddl.interfaces.table.SqlTableQueryBuilder;
import sql.dml.interfaces.SqlFinalBuilder;

import java.util.*;

import static sql.checker.StringCheckers.throwNullOrEmptyException;
import static sql.constants.QueryConstants.*;

public class SqlDDLOracleQueryBuilder implements SqlDDLQueryBuilder, SqlTableQueryBuilder,
        SqlCreateQueryBuilder, SqlAlterQueryBuilder, SqlDropQueryBuilder, SqlAsTableQueryBuilder,
        SqlInColumnStructureBuilder, SqlColumnMiddleBuilder, SqlColumnBuilder {

    private StringBuilder sqlQuery;
    private final List<ColumnDefinition> columns = new LinkedList<>();
    private ColumnDefinition currentColumn = null;

    @Override
    public SqlDDLOracleQueryBuilder table(String scheme, String table) {
        throwNullOrEmptyException(scheme, table);
        sqlQuery.append(scheme).append(DOT).append(table).append(SPACE);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder table(String table) {
        throwNullOrEmptyException(table);
        sqlQuery.append(TABLE).append(table).append(SPACE);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder create() {
        sqlQuery = new StringBuilder();
        sqlQuery.append(CREATE);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder alter() {
        sqlQuery.append(ALTER);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder drop() {
        sqlQuery.append(DROP);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder addColumn(String name, String type, String constraint) {
        columns.add(new ColumnDefinition(name, type, constraint));
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder addColumn(String name) {
        currentColumn = new ColumnDefinition(name);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder addColumn(String name, String type) {
        columns.add(new ColumnDefinition(name, type));
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder tableConstraint(String expression) {
        throwNullOrEmptyException(expression);
        if(currentColumn!= null){
            columns.add(currentColumn);
            currentColumn = null;
        }
        if (!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(SPACE);
        }
        sqlQuery.append(CONSTRAINT).append(expression).append(RIGHT_PARENTHESIS).append(SPACE);
        return this;
    }

    public SqlDDLOracleQueryBuilder constraint(String constraint){
        currentColumn.addConstraint(constraint);
        columns.add(currentColumn);
        currentColumn = null;
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder tablespace(String name) {
        throwNullOrEmptyException(name);
        if (!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(RIGHT_PARENTHESIS);
        }
        sqlQuery.append(TABLESPACE).append(SPACE).append(name);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder as(SqlFinalBuilder query) {
        sqlQuery.append("AS ").append(query.build());
        return this;
    }

    @Override
    public String build() {
        if (!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(RIGHT_PARENTHESIS);
        }
        return sqlQuery.toString().trim();
    }

    private void buildColumns(){
        sqlQuery.append("(");
        columns.stream()
                .map(ColumnDefinition::getColumnQuery)
                .reduce((String s1, String s2) -> s1 + COMMA + s2)
                .ifPresent(sqlQuery::append);
        columns.clear();
    }

    @Override
    public SqlFinalBuilder withNoData() {
        sqlQuery.append(" WITH NO DATA");
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder type(String type) {
        currentColumn.addType(type);
        return this;
    }
}
