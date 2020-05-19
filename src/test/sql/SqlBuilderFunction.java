package sql;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface SqlBuilderFunction <T> {

    Callable<String> prepareSqlString(T builder);

}
