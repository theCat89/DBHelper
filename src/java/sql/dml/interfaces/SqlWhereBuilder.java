package sql.dml.interfaces;

import sql.operators.ComparisonOperators;

public interface SqlWhereBuilder extends SqlFinalBuilder {
    SqlConditionBuilder where(String clause);
    SqlConditionBuilder where(String lValue, ComparisonOperators operator, String rValue);
    SqlFinalBuilder whereExists(SqlFinalBuilder query);
}
