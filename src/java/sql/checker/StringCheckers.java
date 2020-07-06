package sql.checker;

import sql.exceptions.BadParamException;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StringCheckers {

    public static NotNullChecker NULL_CHECKER = new NotNullChecker();
    public static NotEmptyChecker EMPTY_CHECKER = new NotEmptyChecker();
    public static Predicate<String> NULL_OR_EMPTY_CHECKER = NULL_CHECKER.or(EMPTY_CHECKER);

    private static class NotNullChecker implements Predicate<String> {
        @Override
        public boolean test(String s) {
            return s != null;
        }
    }

    private static class NotEmptyChecker implements Predicate<String> {
        @Override
        public boolean test(String s) {
            return !s.isEmpty();
        }
    }

    public static boolean checkEmptyStrings(String... strings) {
        return strings != null && Arrays.stream(strings).allMatch(EMPTY_CHECKER);
    }

    public static boolean checkNullStrings(String... strings) {
        return strings != null && Arrays.stream(strings).allMatch(NULL_CHECKER);
    }

    public static boolean checkNullOrEmptyStrings(String... strings) {
        return strings != null && Arrays.stream(strings).allMatch(NULL_CHECKER.and(EMPTY_CHECKER));
    }

    public static void throwEmptyStringsException(String... strings) {
        if (strings != null && strings.length != 0 && !Arrays.stream(strings).allMatch(EMPTY_CHECKER))
            throw new BadParamException("Empty string param");
    }

    public static void throwNullStringsException(String... strings) {
        if (strings != null && strings.length != 0 && !Arrays.stream(strings).allMatch(NULL_CHECKER))
            throw new BadParamException("Null string param");
    }

    public static void throwNullOrEmptyException(String... strings) {
       if (strings != null && strings.length != 0 && !Arrays.stream(strings).allMatch(NULL_CHECKER.and(EMPTY_CHECKER)))
           throw new BadParamException("Null or Empty string param");
    }

    public static Stream<String> removeEmpty(String... strings) {
        if(strings == null)
            return Stream.empty();
        return Arrays.stream(strings).filter(EMPTY_CHECKER);
    }

    public static Stream<String> removeNull(String... strings) {
        if(strings == null)
            return Stream.empty();
        return Arrays.stream(strings).filter(NULL_CHECKER);
    }

    public static Stream<String> removeNullorEmpty(String... strings) {
        if(strings == null)
            return Stream.empty();
        return Arrays.stream(strings).filter(NULL_CHECKER).filter(EMPTY_CHECKER);
    }
}
