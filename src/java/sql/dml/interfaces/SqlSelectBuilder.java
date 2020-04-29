package sql.dml.interfaces;

public interface SqlSelectBuilder {

    SqlSelectBuilder select(String... params);

    SqlFinalBuilder selectAll(String tableName);

    SqlFinalBuilder from(String tableName);
}
