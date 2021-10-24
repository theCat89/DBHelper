package sql.dml.interfaces;

import sql.operators.ComparisonOperators;

public interface SqlConditionBuilder extends SqlFinalBuilder {
    SqlConditionBuilder or(String lValue, ComparisonOperators operator, String rValue);
    SqlConditionBuilder or(String condition);
    SqlConditionBuilder and(String lValue, ComparisonOperators operator, String rValue);
    SqlConditionBuilder and(String condition);
}
