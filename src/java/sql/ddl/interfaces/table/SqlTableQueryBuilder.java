package sql.ddl.interfaces.table;

import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlTableQueryBuilder extends SqlAddInfoBuilder{

    SqlInColumnStructureBuilder addColumn(String name, String type, String constraint);

    SqlInColumnStructureBuilder addColumn(String name, String type);

    SqlAsTableQueryBuilder as(SqlFinalBuilder query);
}
