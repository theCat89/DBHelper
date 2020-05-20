package sql.ddl.interfaces.table;

import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlAsTableQueryBuilder extends SqlFinalBuilder {

    SqlFinalBuilder withNoData();
}
