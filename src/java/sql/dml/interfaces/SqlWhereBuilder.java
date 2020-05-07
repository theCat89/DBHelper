package sql.dml.interfaces;

import sql.operators.ComparisonOperators;

public interface SqlWhereBuilder extends SqlFinalBuilder {
    SqlFinalBuilder where(String clause);
    SqlFinalBuilder where(String lValue, ComparisonOperators operator, String rValue);
    SqlFinalBuilder whereExists(SqlFinalBuilder query);
}
