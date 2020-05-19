package sql.ddl.interfaces.table;

import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlTableQueryBuilder {

    SqlTableQueryBuilder addColumn(String name, String type, String constraint);

    SqlTableQueryBuilder primaryKey(String name);

    SqlTableQueryBuilder constraint(String name);

    SqlTableQueryBuilder tablespace(String name);

    String build();
}
