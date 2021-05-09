package me.sql.exonrpg.util;

import me.sql.exonrpg.ExonRPG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Database {

    public static String databaseDirectory = "jdbc:sqlite:" + ExonRPG.plugin.getDataFolder().getAbsolutePath()+"db/";
    public static final String STATS_DATABASE = "STATS";

    private Connection dbConnection = null;
    private String dbName = "NOT CONNECTED TO DATABASE";

    public void connectToDatabase(String databaseName) {
        if(dbConnection!=null)
            return;
        databaseName+=".db";
        try {
            dbConnection = DriverManager.getConnection(databaseDirectory+databaseName);
        } catch (SQLException e) {
            ExonRPG.error("Failed trying to connect to database "+databaseName+" at "+databaseDirectory.substring(11)+databaseName+"");
        }
        dbName = databaseName;
    }

    public void createTable(String tableName, List<TableColumn> columns, int primaryKey) {
        if(dbConnection==null) {
            ExonRPG.error("Tried creating table while not connected to database! Make sure to establish a connection before modifying the database.");
        }
        try {
            Statement stmt = dbConnection.createStatement();
            StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS "+tableName+"(\n");
            for(int i = 0; i<columns.size(); i++) {
                TableColumn column = columns.get(i);
                sql.append("      ").append(column.getColumnName()).append(" ").append(column.getType().toString());
                if(i==primaryKey-1) {
                    sql.append(" PRIMARY KEY");
                } else {
                    sql.append(column.getConstraint().getStatementConstraint());
                }
                if(i!=columns.size()-1) {
                    sql.append(",");
                }
                sql.append("\n");
            }
            sql.append(");");
            stmt.execute(sql.toString());
        } catch(SQLException e) {
            ExonRPG.error("Failed creating table "+tableName+" in "+dbName);
        }

    }

    public void addColumnToTable(String tableName, TableColumn column, Object defaultNonNullValue) {
        // TODO: Add column to table
        if(dbConnection==null) {
            ExonRPG.error("Fatal: No Database Connection to database "+dbName+" in "+databaseDirectory.substring(11));
            return;
        }
        Class<?> typeClass = column.getType().getTypeClass();
        if(!typeClass.isInstance(defaultNonNullValue) && column.getConstraint() == Constraint.NOT_NULL || defaultNonNullValue == null) {
            ExonRPG.error("Tried adding column "+column.getColumnName()+" to table "+tableName+". The default value must not be null and of type "+column.getType().toString());
            return;
        }
        try {
            Statement stmt = dbConnection.createStatement();
            String sql = "ALTER TABLE "+tableName+"\n"+
                    "ADD COLUMN "+column.getColumnName()+" "+column.getType();
            if(column.getConstraint()!=Constraint.NONE) {
                if(column.getConstraint()==Constraint.NOT_NULL)
                sql+=" "+column.getConstraint().getStatementConstraint()+" ";
            }
            stmt.execute(sql);
        } catch(SQLException e) {
            ExonRPG.error("Failed adding column "+column.getColumnName()+" to table "+tableName+" in "+dbName);
        }
    }

    public void addColumnToTable(String tableName, TableColumn column) {
        // TODO: Add column to table
        if(dbConnection==null) {
            ExonRPG.error("Fatal: No Database Connection to database "+dbName+" in "+databaseDirectory.substring(11));
            return;
        }
        if(column.getConstraint() == Constraint.NOT_NULL) {
            ExonRPG.error("Tried adding column "+column.getColumnName()+" to table "+tableName+". When specifying NOT NULL constraint you must specify a default value!");
            return;
        }
        try {
            Statement stmt = dbConnection.createStatement();
            String sql = "ALTER TABLE "+tableName+"\n"+
                    "ADD COLUMN "+column.getColumnName()+" "+column.getType();
            if(column.getConstraint()!=Constraint.NONE) {
                if(column.getConstraint()==Constraint.NOT_NULL)
                    sql+=" "+column.getConstraint().getStatementConstraint()+" ";
            }
            stmt.execute(sql);
        } catch(SQLException e) {
            ExonRPG.error("Failed adding column "+column.getColumnName()+" to table "+tableName+" in "+dbName);
        }
    }



}
