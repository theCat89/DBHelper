package sql.ddl.interfaces;

import sql.ddl.interfaces.table.SqlTableQueryBuilder;

public interface SqlCreateQueryBuilder {

    SqlTableQueryBuilder table(String scheme, String table);

    SqlTableQueryBuilder table(String table);
}
