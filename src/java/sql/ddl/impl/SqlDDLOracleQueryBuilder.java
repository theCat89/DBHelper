package sql.ddl.impl;

import sql.ddl.interfaces.SqlAlterQueryBuilder;
import sql.ddl.interfaces.SqlCreateQueryBuilder;
import sql.ddl.interfaces.SqlDDLQueryBuilder;
import sql.ddl.interfaces.SqlDropQueryBuilder;
import sql.ddl.interfaces.table.SqlAsTableQueryBuilder;
import sql.ddl.interfaces.table.SqlInColumnStructureBuilder;
import sql.ddl.interfaces.table.SqlTableQueryBuilder;
import sql.dml.interfaces.SqlFinalBuilder;

import java.util.LinkedList;
import java.util.ListIterator;

import static sql.constants.QueryConstants.*;

public class SqlDDLOracleQueryBuilder implements SqlDDLQueryBuilder, SqlTableQueryBuilder,
        SqlCreateQueryBuilder, SqlAlterQueryBuilder, SqlDropQueryBuilder, SqlAsTableQueryBuilder,
        SqlInColumnStructureBuilder {

    private StringBuilder sqlQuery;
    private final LinkedList<ColumnDefinition> columns = new LinkedList<>();

    @Override
    public SqlDDLOracleQueryBuilder table(String scheme, String table) {
        sqlQuery.append(scheme).append(DOT).append(table).append(SPACE);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder table(String table) {
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
    public SqlDDLOracleQueryBuilder addColumn(String name, String type) {
        columns.add(new ColumnDefinition(name, type));
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder constraint(String expression) {
        if(!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(SPACE);
        }
        sqlQuery.append(CONSTRAINT).append(expression).append(")").append(SPACE);
        return this;
    }

    @Override
    public SqlDDLOracleQueryBuilder tablespace(String name) {
        if(!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(")");
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
        if(!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(")");
        }
        return sqlQuery.toString().trim();
    }

    private void buildColumns(){
        sqlQuery.append("(");
        sqlQuery.append(columns.getFirst().getColumnQuery());
        ListIterator<ColumnDefinition> iterator = columns.listIterator(1);
        while (iterator.hasNext()){
            ColumnDefinition next = iterator.next();
            sqlQuery.append(COMMA);
            sqlQuery.append(next.getColumnQuery());
        }
        columns.clear();
    }

    @Override
    public SqlFinalBuilder withNoData() {
        sqlQuery.append(" WITH NO DATA");
        return this;
    }
}
