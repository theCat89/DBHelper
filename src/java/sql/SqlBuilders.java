package sql;

import sql.dml.impl.SqlBuilderPostgreImpl;
import sql.dml.interfaces.SqlSelectBuilder;

public class SqlBuilders {

    public static SqlSelectBuilder getPostgresSqlSelectBuilder(){
        return new SqlBuilderPostgreImpl();
    }
}
