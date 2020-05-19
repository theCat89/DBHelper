package sql.ddl.interfaces;

import org.junit.jupiter.api.Test;
import sql.SqlBuilderFunction;
import sql.ddl.impl.SqlDDLOracleQueryBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlDDLQueryBuilderTest {

    @Test
    public void testSimpleCreate(){
        validation("CREATE TABLE my_table", SqlDDLTestDirector::buildCreateSimpleSql, new SqlDDLOracleQueryBuilder());
    }

    public void validation(String str, SqlBuilderFunction<SqlDDLQueryBuilder> testingMethod, SqlDDLQueryBuilder testingBuilder){
        try {
            assertEquals(str, testingMethod.prepareSqlString(testingBuilder).call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}