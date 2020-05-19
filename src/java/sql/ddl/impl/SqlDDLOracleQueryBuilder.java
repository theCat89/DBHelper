package sql.ddl.impl;

import sql.ddl.interfaces.SqlAlterQueryBuilder;
import sql.ddl.interfaces.SqlCreateQueryBuilder;
import sql.ddl.interfaces.SqlDDLQueryBuilder;
import sql.ddl.interfaces.SqlDropQueryBuilder;
import sql.ddl.interfaces.table.SqlTableQueryBuilder;

import static sql.constants.QueryConstants.*;

public class SqlDDLOracleQueryBuilder implements SqlDDLQueryBuilder, SqlTableQueryBuilder, SqlCreateQueryBuilder, SqlAlterQueryBuilder, SqlDropQueryBuilder {

    private StringBuilder sqlQuery = new StringBuilder();

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
        //TODO implement method
        return this;
    }

    @Override
    public SqlTableQueryBuilder primaryKey(String name) {
        //TODO implement method
        return this;
    }

    @Override
    public SqlTableQueryBuilder constraint(String name) {
        //TODO implement method
        return this;
    }

    @Override
    public SqlTableQueryBuilder tablespace(String name) {
        sqlQuery.append(TABLESPACE).append(name);
        return this;
    }

    @Override
    public String build() {
        return sqlQuery.toString().trim();
    }
}
