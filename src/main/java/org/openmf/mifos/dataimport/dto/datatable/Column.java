package org.openmf.mifos.dataimport.dto.datatable;
import java.util.List;

public class Column {

    private final String columnName;
    private final String columnDisplayType;
    private final String isColumnNullable;
    private List<ColumnValue> columnValues;

    public Column(String columnName, String columnDisplayType, String isColumnNullable) {
        this.columnName = columnName;
        this.columnDisplayType = columnDisplayType;
        this.isColumnNullable = isColumnNullable;
    }

    public Column(String columnName, String columnDisplayType, String isColumnNullable, List<ColumnValue> columnValues) {
        this.columnName = columnName;
        this.columnDisplayType = columnDisplayType;
        this.isColumnNullable = isColumnNullable;
        this.columnValues = columnValues;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnDisplayType() {
        return columnDisplayType;
    }

    public String getIsColumnNullable() {
        return isColumnNullable;
    }

    public List<ColumnValue> getColumnValues() {
        return columnValues;
    }
    
    
    
	
}
