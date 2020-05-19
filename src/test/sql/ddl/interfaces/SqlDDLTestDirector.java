package sql.ddl.interfaces;

import java.util.concurrent.Callable;

public class SqlDDLTestDirector {

    static Callable<String> buildCreateSimpleSql(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table") ::build;
    }
}
