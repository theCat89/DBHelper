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

    @Test
    public void testSimpleCreateWithOneField(){
        validation("CREATE TABLE my_table (field1 NUMBER(5) NOT NULL)", SqlDDLTestDirector::buildCreateOneFieldTable, new SqlDDLOracleQueryBuilder());
    }

    @Test
    public void testSimpleCreateWithTwoField(){
        validation("CREATE TABLE my_table (field1 NUMBER(5) NOT NULL, field2 NUMBER(10))", SqlDDLTestDirector::buildCreateTwoFieldTable, new SqlDDLOracleQueryBuilder());
    }

    @Test
    public void testSimpleCreateOneFieldConstraint(){
        validation("CREATE TABLE my_table (field1 NUMBER(5) NOT NULL CONSTRAINT field1 PRIMARY KEY)", SqlDDLTestDirector::buildCreateOneFieldTableWithConstraint, new SqlDDLOracleQueryBuilder());
    }

    @Test
    public void testCreateAs(){
        validation("CREATE TABLE my_table AS SELECT * FROM your_table", SqlDDLTestDirector::buildCreateAs, new SqlDDLOracleQueryBuilder());
    }

    public void validation(String str, SqlBuilderFunction<SqlDDLQueryBuilder> testingMethod, SqlDDLQueryBuilder testingBuilder){
        try {
            assertEquals(str, testingMethod.prepareSqlString(testingBuilder).call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}