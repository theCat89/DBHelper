package sql.impl;

import sql.interfaces.SqlFinalBuilder;
import sql.interfaces.SqlSelectBuilder;

import java.util.Optional;

import static java.util.stream.Stream.of;

/**
 * Spaces on start, keywords - UPPER_CASE
 */
public class SqlBuilderPostgreImpl implements SqlSelectBuilder, SqlFinalBuilder {

    private StringBuilder sqlQuery = new StringBuilder();

    public SqlBuilderPostgreImpl select(String... params) {
        sqlQuery.append("SELECT ");
        if(params == null) {
            sqlQuery.append("* ");
            return this;
        }
        of(params).reduce((String s1, String s2) -> s1 + ", " + s2).ifPresent(s -> sqlQuery.append(s));
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
