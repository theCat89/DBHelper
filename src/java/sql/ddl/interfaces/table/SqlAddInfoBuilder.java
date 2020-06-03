package sql.ddl.interfaces.table;

import sql.dml.interfaces.SqlFinalBuilder;

public interface SqlAddInfoBuilder extends SqlFinalBuilder {

    SqlFinalBuilder tablespace(String name);

}
