package sql.dml.impl;

import sql.dml.interfaces.SqlFinalBuilder;
import sql.dml.interfaces.SqlFromBuilder;
import sql.dml.interfaces.SqlSelectBuilder;
import sql.dml.interfaces.SqlWhereBuilder;
import sql.operators.ComparisonOperators;

import java.util.Arrays;

import static java.util.stream.Stream.of;
import static sql.constants.QueryConstants.*;

/**
 * Spaces on start, keywords - UPPER_CASE
 */
public class SqlBuilderPostgreImpl implements SqlSelectBuilder, SqlFinalBuilder, SqlFromBuilder, SqlWhereBuilder {

    private final StringBuilder sqlQuery = new StringBuilder();

    public SqlBuilderPostgreImpl select(String... params) {
        sqlQuery.append(SELECT);
        if (Arrays.asList(params).isEmpty()) {
            sqlQuery.append(STAR);
            return this;
        }
        of(params).reduce((String s1, String s2) -> s1 + COMMA + s2).ifPresent(sqlQuery::append);
        sqlQuery.append(SPACE);
        return this;
    }

    public SqlBuilderPostgreImpl selectAll() {
        return select();
    }

    public SqlBuilderPostgreImpl from(String tableName){
        sqlQuery.append(FROM).append(tableName).append(SPACE);
        return this;
    }

    public SqlFinalBuilder where(String clause) {
        sqlQuery.append(WHERE).append(clause);
        return this;
    }

    public SqlFinalBuilder where(String lValue, ComparisonOperators operator, String rValue) {
        sqlQuery.append(WHERE).append(lValue).append(operator.getOperator()).append(rValue);
        return this;
    }

    public SqlFinalBuilder whereExists(SqlFinalBuilder query) {
        sqlQuery.append(WHERE).append(EXISTS).append("(").append(query.build()).append(")");
        return this;
    }

    public String build(){
        return sqlQuery.toString().trim();
    }
}
