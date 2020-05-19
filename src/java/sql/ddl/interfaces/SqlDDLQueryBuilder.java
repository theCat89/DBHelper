package sql.ddl.interfaces;

public interface SqlDDLQueryBuilder {

    SqlCreateQueryBuilder create();

    SqlAlterQueryBuilder alter();

    SqlDropQueryBuilder drop();
}
