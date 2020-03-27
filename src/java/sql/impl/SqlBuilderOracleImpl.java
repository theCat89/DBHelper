package sql.impl;

import java.util.Arrays;

public class SqlBuilderOracleImpl {

    private StringBuilder sqlQuery;

    public SqlBuilderOracleImpl select(String... params){
        sqlQuery.append("SELECT ").append(params != null ? Arrays.toString(params) : "*");
        return this;
    }

    public SqlBuilderOracleImpl selectAll(String tableName){
        return select("*").from(tableName);
    }

    public SqlBuilderOracleImpl from(String tableName){
        sqlQuery.append(" FROM ").append(tableName);
        return this;
    }

    public String build(){
        return sqlQuery.toString();
    }
}
