package sql.ddl.interfaces;

import sql.dml.impl.SqlBuilderPostgreImpl;

import java.util.concurrent.Callable;

public class SqlDDLTestDirector {

    static Callable<String> buildCreateSimpleSql(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table") ::build;
    }

    static Callable<String> buildCreateOneFieldTable(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table").addColumn("field1","NUMBER(5)","NOT NULL")::build;
    }

    static Callable<String> buildCreateTwoFieldTable(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table")
                .addColumn("field1","NUMBER(5)","NOT NULL")
                .addColumn("field2","NUMBER(10)")
                ::build;
    }

    static Callable<String> buildCreateTwoFieldTableByColumnBuilder(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table")
                .addColumn("field1").type("NUMBER(5)").constraint("NOT NULL")
                .addColumn("field2").type("NUMBER(10)")
                ::build;
    }

    static Callable<String> buildCreateOneFieldTableWithConstraint(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table").addColumn("field1","NUMBER(5)","NOT NULL").tableConstraint("field1 PRIMARY KEY")::build;
    }

    static Callable<String> buildCreateAs(SqlDDLQueryBuilder builder){
        return builder.create().table("my_table").as(new SqlBuilderPostgreImpl().select().from("your_table"))::build;
    }
}
