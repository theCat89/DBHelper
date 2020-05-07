package sql.operators;

public enum ComparisonOperators {
    equals (" = "),
    notEquals (" <> "),
    less (" < "),
    more (" > "),
    lessOrEquals (" <= "),
    moreOrEquals (" >= ");

    private String operator;

    ComparisonOperators(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
