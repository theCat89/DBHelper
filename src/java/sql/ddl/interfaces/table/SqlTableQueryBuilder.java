package sql.ddl.interfaces.table;

import sql.ddl.interfaces.column.SqlColumnBuilder;
import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlTableQueryBuilder extends SqlAddInfoBuilder{

    SqlInColumnStructureBuilder addColumn(String name, String type, String constraint);

    SqlColumnBuilder addColumn(String name);

    SqlInColumnStructureBuilder addColumn(String name, String type);

    SqlAsTableQueryBuilder as(SqlFinalBuilder query);
}
