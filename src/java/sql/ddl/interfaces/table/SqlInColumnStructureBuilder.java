package sql.ddl.interfaces.table;

public interface SqlInColumnStructureBuilder extends SqlAddInfoBuilder {

    SqlTableQueryBuilder addColumn(String name, String type, String constraint);

    SqlTableQueryBuilder addColumn(String name, String type);

    SqlTableQueryBuilder tableConstraint(String expression);
}
