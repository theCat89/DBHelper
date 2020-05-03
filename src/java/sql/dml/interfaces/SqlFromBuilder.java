package sql.dml.interfaces;

public interface SqlFromBuilder {
    SqlWhereBuilder from(String tableName);
}
