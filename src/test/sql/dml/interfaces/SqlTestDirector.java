package sql.dml.interfaces;

import java.util.concurrent.Callable;

public class SqlTestDirector {

    static Callable<String> buildSelectSimpleSql(SqlSelectBuilder builder){
        return builder.select("*").from("tableName")::build;
    }

    static Callable<String> buildSelectSimpleSqlWithFields(SqlSelectBuilder builder){
        return builder.select("field1", "field2").from("tableName")::build;
    }

    static Callable<String> buildSelectAllSimpleSql(SqlSelectBuilder builder) {
        return builder.selectAll().from("tableName")::build;
    }

    static Callable<String> buildSimpleWhereStatement(SqlSelectBuilder builder) {
        return builder.select("field1").from("tableName").where("field2 = 1")::build;
    }
}
