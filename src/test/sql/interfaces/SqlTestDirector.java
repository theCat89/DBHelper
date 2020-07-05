package sql.interfaces;

import java.util.concurrent.Callable;

public class SqlTestDirector {

    static Callable<String> buildSelectSimpleSql(SqlSelectBuilder builder){
        return builder.select("*").from("tableName")::build;
    }

    static Callable<String> buildSelectSimpleSqlWithFields(SqlSelectBuilder builder){
        return builder.select("field1", "field2").from("tableName")::build;
    }
}
