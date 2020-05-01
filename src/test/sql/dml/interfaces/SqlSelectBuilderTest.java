package sql.dml.interfaces;

import org.junit.jupiter.api.Test;

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

    public void validation(String str, SqlBuilderFunction testingMethod, SqlSelectBuilder testingBuilder){
        try {
            assertEquals(str, testingMethod.prepareSqlString(testingBuilder).call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}