package sql.dml.interfaces;

public interface SqlSelectBuilder {

    SqlFromBuilder select(String... params);

    SqlFromBuilder selectAll();
}
