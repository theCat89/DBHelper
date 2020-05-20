package sql.ddl.impl;

import sql.ddl.interfaces.SqlAlterQueryBuilder;
import sql.ddl.interfaces.SqlCreateQueryBuilder;
import sql.ddl.interfaces.SqlDDLQueryBuilder;
import sql.ddl.interfaces.SqlDropQueryBuilder;
import sql.ddl.interfaces.table.SqlAsTableQueryBuilder;
import sql.ddl.interfaces.table.SqlTableQueryBuilder;
import sql.dml.interfaces.SqlFinalBuilder;

import java.util.LinkedList;
import java.util.ListIterator;

import static sql.constants.QueryConstants.*;

public class SqlDDLOracleQueryBuilder implements SqlDDLQueryBuilder, SqlTableQueryBuilder,
        SqlCreateQueryBuilder, SqlAlterQueryBuilder, SqlDropQueryBuilder, SqlAsTableQueryBuilder {

    private StringBuilder sqlQuery = new StringBuilder();
    private LinkedList<ColumnDefinition> columns = new LinkedList<>();

    @Override
    public SqlTableQueryBuilder table(String scheme, String table) {
        sqlQuery.append(scheme).append(DOT).append(table).append(SPACE);
        return this;
    }

    @Override
    public SqlTableQueryBuilder table(String table) {
        sqlQuery.append(TABLE).append(table).append(SPACE);
        return this;
    }

    @Override
    public SqlCreateQueryBuilder create() {
        sqlQuery.append(CREATE);
        return this;
    }

    @Override
    public SqlAlterQueryBuilder alter() {
        sqlQuery.append(ALTER);
        return this;
    }

    @Override
    public SqlDropQueryBuilder drop() {
        sqlQuery.append(DROP);
        return this;
    }

    @Override
    public SqlTableQueryBuilder addColumn(String name, String type, String constraint) {
        columns.add(new ColumnDefinition(name, type, constraint));
        return this;
    }

    @Override
    public SqlTableQueryBuilder constraint(String expression) {
        if(!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(SPACE);
        }
        sqlQuery.append(CONSTRAINT).append(expression).append(")").append(SPACE);
        return this;
    }

    @Override
    public SqlTableQueryBuilder tablespace(String name) {
        if(!columns.isEmpty()) {
            buildColumns();
            sqlQuery.append(")");
        }
        sqlQuery.append(TABLESPACE).append(SPACE).append(name);
        return this;
    }

    @Override
    public SqlAsTableQueryBuilder as(SqlFinalBuilder query) {
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
        sqlQuery.append(columns.getFirst().getName()).append(SPACE);
        sqlQuery.append(columns.getFirst().getType()).append(SPACE);
        sqlQuery.append(columns.getFirst().getConstraint());
        ListIterator<ColumnDefinition> iterator = columns.listIterator(1);
        while (iterator.hasNext()){
            ColumnDefinition next = iterator.next();
            sqlQuery.append(COMMA);
            sqlQuery.append(next.getName()).append(SPACE);
            sqlQuery.append(next.getType()).append(SPACE);
            sqlQuery.append(next.getConstraint());
        }

        columns.clear();
    }

    @Override
    public SqlFinalBuilder withNoData() {
        sqlQuery.append(" WITH NO DATA");
        return this;
    }
}
