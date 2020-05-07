package sql.dml.interfaces;

import sql.dml.impl.SqlBuilderPostgreImpl;
import sql.operators.ComparisonOperators;

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

    static Callable<String> buildWhereWithOperatorStatement(SqlSelectBuilder builder) {
        return builder.select("field1").from("tableName").where("field2", ComparisonOperators.equals, "1")::build;
    }

    static Callable<String> buildWhereExists(SqlSelectBuilder builder) {
        return builder.select().from("table1")
                .whereExists(new SqlBuilderPostgreImpl().select().from("table2").where("field2", ComparisonOperators.equals, "5"))::build;

    }

}
