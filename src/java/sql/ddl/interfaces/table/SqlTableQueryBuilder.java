package sql.ddl.interfaces.table;

import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlTableQueryBuilder extends SqlFinalBuilder{

    SqlTableQueryBuilder addColumn(String name, String type, String constraint);

    SqlTableQueryBuilder addColumn(String name, String type);

    SqlTableQueryBuilder constraint(String expression);

    SqlTableQueryBuilder tablespace(String name);

    SqlAsTableQueryBuilder as(SqlFinalBuilder query);
}
