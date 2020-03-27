package sql.impl;

import sql.interfaces.SqlFinalBuilder;
import sql.interfaces.SqlSelectBuilder;

import java.util.Arrays;

/**
 * Spaces on start, keywords - UPPER_CASE
 */
public class SqlBuilderPostgreImpl implements SqlSelectBuilder, SqlFinalBuilder {

    private StringBuilder sqlQuery;

    public SqlBuilderPostgreImpl select(String... params){
        sqlQuery.append("SELECT ").append(params != null ? Arrays.toString(params) : "*");
        return this;
    }

    public SqlBuilderPostgreImpl selectAll(String tableName){
        return select("*").from(tableName);
    }

    public SqlBuilderPostgreImpl from(String tableName){
        sqlQuery.append(" FROM ").append(tableName);
        return this;
    }

    public String build(){
        return sqlQuery.toString();
    }
}
