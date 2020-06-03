package sql.ddl.interfaces.table;

import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlInColumnStructureBuilder extends SqlAddInfoBuilder {

    SqlTableQueryBuilder addColumn(String name, String type, String constraint);

    SqlTableQueryBuilder addColumn(String name, String type);

    SqlTableQueryBuilder constraint(String expression);
}
