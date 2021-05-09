package me.sql.exonrpg.util;

public enum DataType {

    INTEGER(Integer.class),
    TEXT(String.class),
    REAL(Float.class),
    BLOB(Object.class);

    private Class typeClass;

    DataType(Class typeClass) {
        this.typeClass = typeClass;
    }

    public Class getTypeClass() {
        return this.typeClass;
    }

}
