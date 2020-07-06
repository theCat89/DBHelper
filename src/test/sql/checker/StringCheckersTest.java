package sql.checker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import sql.exceptions.BadParamException;

import static sql.checker.StringCheckers.NULL_OR_EMPTY_CHECKER;


class StringCheckersTest {

    @Test
    void checkEmptyStrings() {
        Assertions.assertTrue(StringCheckers.checkEmptyStrings());
        Assertions.assertTrue(StringCheckers.checkEmptyStrings("STR"));
        Assertions.assertTrue(StringCheckers.checkEmptyStrings("STR", "STR2"));
        Assertions.assertFalse(StringCheckers.checkEmptyStrings("STR", ""));
        Assertions.assertFalse(StringCheckers.checkEmptyStrings(""));
        Assertions.assertFalse(StringCheckers.checkEmptyStrings("", ""));
        Assertions.assertFalse(StringCheckers.checkEmptyStrings(null));
    }

    @Test
    void checkNullStrings() {
        Assertions.assertTrue(StringCheckers.checkNullStrings());
        Assertions.assertTrue(StringCheckers.checkNullStrings("STR"));
        Assertions.assertTrue(StringCheckers.checkNullStrings("STR", "STR2"));
        Assertions.assertTrue(StringCheckers.checkNullStrings("STR", ""));
        Assertions.assertFalse(StringCheckers.checkNullStrings("STR", null));
        Assertions.assertFalse(StringCheckers.checkNullStrings(null));
        Assertions.assertFalse(StringCheckers.checkNullStrings(null, null));
        Assertions.assertFalse(StringCheckers.checkNullStrings(null, ""));
    }

    @Test
    void checkNullOrEmptyStrings() {
        Assertions.assertTrue(StringCheckers.checkNullOrEmptyStrings());
        Assertions.assertTrue(StringCheckers.checkNullOrEmptyStrings("STR"));
        Assertions.assertTrue(StringCheckers.checkNullOrEmptyStrings("STR", "STR2"));
        Assertions.assertFalse(StringCheckers.checkNullOrEmptyStrings("STR", ""));
        Assertions.assertFalse(StringCheckers.checkNullOrEmptyStrings("STR", null));
        Assertions.assertFalse(StringCheckers.checkNullOrEmptyStrings(null));

    }

    @Test
    void throwEmptyStringsException() {
        Assertions.assertDoesNotThrow((Executable) StringCheckers::throwEmptyStringsException);
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwEmptyStringsException("STR");
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwEmptyStringsException("STR", "STR2");
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            StringCheckers.throwEmptyStringsException("STR", "");
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwEmptyStringsException(null);
        });
    }

    @Test
    void throwNullStringsException() {
        Assertions.assertDoesNotThrow((Executable) StringCheckers::throwNullStringsException);
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullStringsException("STR");
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullStringsException("STR", "STR2");
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullStringsException("STR", "");
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            StringCheckers.throwNullStringsException("STR", null);
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullStringsException(null);
        });
    }

    @Test
    void throwNullOrEmptyException() {
        Assertions.assertDoesNotThrow((Executable) StringCheckers::throwNullOrEmptyException);
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullOrEmptyException("STR");
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullOrEmptyException("STR", "STR2");
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            StringCheckers.throwNullOrEmptyException("STR", "");
        });
        Assertions.assertThrows(BadParamException.class, () -> {
            StringCheckers.throwNullOrEmptyException("STR", null);
        });
        Assertions.assertDoesNotThrow(() -> {
            StringCheckers.throwNullOrEmptyException(null);
        });
    }

    @Test
    void removeNull() {
        Assertions.assertTrue(StringCheckers.removeNull("STR", null).allMatch(StringCheckers.NULL_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNull(null).allMatch(StringCheckers.NULL_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNull("STR", "STR2").allMatch(StringCheckers.NULL_CHECKER));
    }

    @Test
    void removeEmpty() {
        Assertions.assertTrue(StringCheckers.removeEmpty("STR", "").allMatch(StringCheckers.EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeEmpty("").allMatch(StringCheckers.EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeEmpty("STR", "STR2").allMatch(StringCheckers.EMPTY_CHECKER));
    }

    @Test
    void removeNullOrEmpty() {
        Assertions.assertTrue(StringCheckers.removeNullorEmpty("STR", null).allMatch(NULL_OR_EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNullorEmpty("STR", null, "").allMatch(NULL_OR_EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNullorEmpty(null, "").allMatch(NULL_OR_EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNullorEmpty("STR", "STR2").allMatch(NULL_OR_EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNullorEmpty("STR", "STR2", null).allMatch(NULL_OR_EMPTY_CHECKER));
        Assertions.assertTrue(StringCheckers.removeNullorEmpty("STR", "STR2", null, "").allMatch(NULL_OR_EMPTY_CHECKER));
    }
}