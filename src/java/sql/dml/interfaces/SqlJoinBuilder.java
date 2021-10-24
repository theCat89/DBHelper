package sql.dml.interfaces;

public interface SqlJoinBuilder {
    SqlOnBuilder innerJoin(String table_name);
    SqlOnBuilder innerJoin(SqlFinalBuilder query);
}
