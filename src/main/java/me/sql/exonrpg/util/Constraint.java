package me.sql.exonrpg.util;

public enum Constraint {

    NOT_NULL("NOT NULL"),
    UNIQUE("UNIQUE"),
    NONE("");
    // TODO: Check constraint
    private String statementConstraint;

    Constraint(String statementConstraint) {
        this.statementConstraint = statementConstraint;
    }

    public String getStatementConstraint() {
        return this.statementConstraint;
    }


}
