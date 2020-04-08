package sql.interfaces;

import org.junit.jupiter.api.Test;
import sql.impl.SqlBuilderPostgreImpl;

import static org.junit.jupiter.api.Assertions.*;

class SqlSelectBuilderTest {

    @Test
    public void testSimpleSelect(){
        validation("SELECT * FROM tableName", SqlTestDirector::buildSelectSimpleSql, new SqlBuilderPostgreImpl());
    }

    @Test
    public void testSimpleSelectWithParams(){
        validation("SELECT field1, field2 FROM tableName", SqlTestDirector::buildSelectSimpleSqlWithFields, new SqlBuilderPostgreImpl());
    }

    public void validation(String str, SqlBuilderConsumer testingMethod, SqlSelectBuilder testingBuilder){
        try {
            assertEquals(str, testingMethod.prepareSqlString(testingBuilder).call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}