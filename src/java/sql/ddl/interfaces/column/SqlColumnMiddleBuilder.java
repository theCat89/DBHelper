package sql.ddl.interfaces.column;

import sql.ddl.interfaces.table.SqlAddInfoBuilder;
import sql.ddl.interfaces.table.SqlTableQueryBuilder;

public interface SqlColumnMiddleBuilder extends SqlAddInfoBuilder {

    SqlTableQueryBuilder constraint(String constraint);

    SqlColumnBuilder addColumn(String name);

}
