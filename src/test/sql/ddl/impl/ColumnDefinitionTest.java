package sql.ddl.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sql.exceptions.BadParamException;

class ColumnDefinitionTest {

    @Test
    public void simpleNull() {
        Assertions.assertThrows(BadParamException.class, () -> {
            new ColumnDefinition(null, "type", "").getColumnQuery();
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            new ColumnDefinition("name", null, "").getColumnQuery();
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            new ColumnDefinition(null, null, "").getColumnQuery();
        });
    }

    @Test
    public void simpleEmpty() {
        Assertions.assertThrows(BadParamException.class, () -> {
            new ColumnDefinition("", "type", "").getColumnQuery();
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            new ColumnDefinition("name", "", "").getColumnQuery();
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            new ColumnDefinition("", "", "").getColumnQuery();
        });
    }

    @Test
    public void constraintCheck() {
        Assertions.assertEquals("name type", new ColumnDefinition("name", "type", "").getColumnQuery());
        Assertions.assertEquals("name type", new ColumnDefinition("name", "type", null).getColumnQuery());
        Assertions.assertEquals("name type NOT NULL", new ColumnDefinition("name", "type", "NOT NULL").getColumnQuery());
    }
}