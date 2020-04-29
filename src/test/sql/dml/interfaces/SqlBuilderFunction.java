package sql.dml.interfaces;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface SqlBuilderFunction {

    Callable<String> prepareSqlString(SqlSelectBuilder builder);

}
