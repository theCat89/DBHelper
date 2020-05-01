package sql.dml.impl;

import sql.dml.interfaces.SqlFinalBuilder;
import sql.dml.interfaces.SqlSelectBuilder;

import static java.util.stream.Stream.of;
import static sql.constants.QueryConstants.*;

/**
 * Spaces on start, keywords - UPPER_CASE
 */
public class SqlBuilderPostgreImpl implements SqlSelectBuilder, SqlFinalBuilder {

    private StringBuilder sqlQuery = new StringBuilder();
    //TODO add test for null and empty string
    public SqlBuilderPostgreImpl select(String... params) {
        sqlQuery.append(SELECT);
        if(params == null || params.length == 0) {
            sqlQuery.append(STAR);
            return this;
        }
        of(params).reduce((String s1, String s2) -> s1 + COMMA + s2).ifPresent(s -> sqlQuery.append(s));
        sqlQuery.append(SPACE);
        return this;
    }



    public SqlBuilderPostgreImpl selectAll(String tableName){
        return select().from(tableName);
    }

    public SqlBuilderPostgreImpl from(String tableName){
        sqlQuery.append(FROM).append(tableName);
        return this;
    }

    public String build(){
        return sqlQuery.toString();
    }
}
