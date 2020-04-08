package sql.interfaces;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface SqlBuilderConsumer {

    Callable<String> prepareSqlString(SqlSelectBuilder builder);

}
