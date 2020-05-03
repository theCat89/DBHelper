package sql.dml.interfaces;

public interface SqlWhereBuilder extends SqlFinalBuilder {
    SqlFinalBuilder where(String clause);
}
