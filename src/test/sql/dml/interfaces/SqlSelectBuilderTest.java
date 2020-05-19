package sql.dml.interfaces;

import org.junit.jupiter.api.Test;
import sql.SqlBuilderFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sql.SqlBuilders.getPostgresSqlSelectBuilder;

class SqlSelectBuilderTest {

    @Test
    public void testSimpleSelect(){
        validation("SELECT * FROM tableName", SqlTestDirector::buildSelectSimpleSql, getPostgresSqlSelectBuilder());
    }

    @Test
    public void testSimpleSelectWithParams(){
        validation("SELECT field1, field2 FROM tableName", SqlTestDirector::buildSelectSimpleSqlWithFields, getPostgresSqlSelectBuilder());
    }

    @Test
    public void testSimpleSelectAll() {
        validation("SELECT * FROM tableName", SqlTestDirector::buildSelectAllSimpleSql, getPostgresSqlSelectBuilder());
    }

    @Test
    public void testSimpleWhere() {
        validation("SELECT field1 FROM tableName WHERE field2 = 1", SqlTestDirector::buildSimpleWhereStatement, getPostgresSqlSelectBuilder());
    }

    @Test
    public void testWhereWithComparisonOperator() {
        validation("SELECT field1 FROM tableName WHERE field2 = 1", SqlTestDirector::buildWhereWithOperatorStatement, getPostgresSqlSelectBuilder());
    }

    @Test
    public void testWhereExists() {
        validation("SELECT * FROM table1 WHERE EXISTS (SELECT * FROM table2 WHERE field2 = 5)", SqlTestDirector::buildWhereExists, getPostgresSqlSelectBuilder());
    }

    public void validation(String str, SqlBuilderFunction<SqlSelectBuilder> testingMethod, SqlSelectBuilder testingBuilder){
        try {
            assertEquals(str, testingMethod.prepareSqlString(testingBuilder).call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}