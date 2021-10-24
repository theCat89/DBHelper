package sql.dml.impl;

import sql.dml.interfaces.*;
import sql.operators.ComparisonOperators;

import java.util.Arrays;

import static sql.checker.StringCheckers.*;
import static sql.constants.QueryConstants.*;

/**
 * Spaces on start, keywords - UPPER_CASE
 */
public class SqlBuilderPostgreImpl implements SqlSelectBuilder, SqlFinalBuilder, SqlFromBuilder, SqlWhereBuilder,
        SqlJoinBuilder, SqlOnBuilder, SqlConditionBuilder {

    private final StringBuilder sqlQuery = new StringBuilder();

    public SqlBuilderPostgreImpl select(String... params) {
        sqlQuery.append(SELECT);
        if (params == null || Arrays.asList(params).isEmpty()) {
            sqlQuery.append(STAR);
            return this;
        }
        removeNullorEmpty(params).reduce((String s1, String s2) -> s1 + COMMA + s2).ifPresent(sqlQuery::append);
        sqlQuery.append(SPACE);
        return this;
    }

    public SqlBuilderPostgreImpl selectAll() {
        return select();
    }

    public SqlBuilderPostgreImpl from(String tableName) {
        throwNullOrEmptyException(tableName);
        sqlQuery.append(FROM).append(tableName).append(SPACE);
        return this;
    }

    public SqlBuilderPostgreImpl where(String clause) {
        throwNullOrEmptyException(clause);
        sqlQuery.append(WHERE).append(clause);
        return this;
    }

    public SqlBuilderPostgreImpl where(String lValue, ComparisonOperators operator, String rValue) {
        throwNullOrEmptyException(lValue, rValue);
        sqlQuery.append(WHERE).append(lValue).append(operator.getOperator()).append(rValue);
        return this;
    }

    public SqlFinalBuilder whereExists(SqlFinalBuilder query) {
        sqlQuery.append(WHERE).append(EXISTS).append(LEFT_PARENTHESIS).append(query.build()).append(RIGHT_PARENTHESIS);
        return this;
    }

    public SqlBuilderPostgreImpl innerJoin(String tableName) {
        sqlQuery.append(INNER_JOIN).append(LEFT_PARENTHESIS).append(tableName).append(RIGHT_PARENTHESIS);
        return this;
    }

    public SqlOnBuilder innerJoin(SqlFinalBuilder query) {
        sqlQuery.append(INNER_JOIN).append(LEFT_PARENTHESIS).append(query.build()).append(RIGHT_PARENTHESIS);
        return this;
    }

    public SqlBuilderPostgreImpl or(String lValue, ComparisonOperators operator, String rValue) {
        throwNullOrEmptyException(lValue, rValue);
        sqlQuery.append(OR).append(lValue).append(operator.getOperator()).append(rValue);
        return this;
    }

    public SqlBuilderPostgreImpl and(String lValue, ComparisonOperators operator, String rValue) {
        throwNullOrEmptyException(lValue, rValue);
        sqlQuery.append(AND).append(lValue).append(operator.getOperator()).append(rValue);
        return this;
    }

    public SqlBuilderPostgreImpl or(String condition) {
        sqlQuery.append(OR).append(condition);
        return this;
    }

    public SqlBuilderPostgreImpl and(String condition) {
        sqlQuery.append(AND).append(condition);
        return this;
    }

    public String build() {
        return sqlQuery.toString().trim();
    }
}
