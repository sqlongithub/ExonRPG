package me.sql.exonrpg.util;

public class TableColumn {

    private String columnName;
    private DataType type;
    private Constraint constraint;

    public TableColumn(String columnName, DataType type, Constraint constraint) {
        this.columnName = columnName;
        this.type = type;
        this.constraint = constraint;
    }

    public void insertIntoTable(String databaseName, String tableName) {
        Database db = new Database();
        db.connectToDatabase(databaseName);
        db.addColumnToTable(tableName, this);
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public DataType getType() {
        return type;
    }

    public String getColumnName() {
        return columnName;
    }
}
