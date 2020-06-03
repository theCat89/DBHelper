package sql.ddl.interfaces;

import org.junit.jupiter.api.Assertions;
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

    @Test
    public void testMethodAccess(){
        //TODO refactor  reflection see class not interface, how to see only interface methods?
//        SqlDDLQueryBuilder sqlDDLOracleQueryBuilder = new SqlDDLOracleQueryBuilder();
//
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create(), "table", String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create(), "table", String.class, String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table(""), "tablespace", String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table(""), "addColumn", String.class, String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table(""), "addColumn", String.class, String.class, String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table(""), "as", SqlFinalBuilder.class);
//       // waitFailedAccess(sqlDDLOracleQueryBuilder.create().table("",""), "constraint", String.class);
//
//
//       // waitFailedAccess(sqlDDLOracleQueryBuilder.create().table("","").addColumn("",""), "as", SqlFinalBuilder.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table("").addColumn("",""), "tablespace", String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table("").addColumn("",""), "constraint", String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table("").addColumn("",""), "addColumn", String.class, String.class);
//        waitSuccessAccess(sqlDDLOracleQueryBuilder.create().table("").addColumn("",""), "addColumn", String.class, String.class, String.class);

    }

    private void waitFailedAccess(Object object, String method, Class<?>... params){
        Assertions.assertThrows(NoSuchMethodException.class, () -> object.getClass().getMethod(method, params));
    }

    private void waitSuccessAccess(Object object, String method, Class<?>... params){
        Assertions.assertDoesNotThrow( () -> {
            object.getClass().getMethod(method, params);
        });
    }
}