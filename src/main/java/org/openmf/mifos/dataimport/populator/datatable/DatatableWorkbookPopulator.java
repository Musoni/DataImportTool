package org.openmf.mifos.dataimport.populator.datatable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openmf.mifos.dataimport.populator.client.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.openmf.mifos.dataimport.dto.datatable.Column;
import org.openmf.mifos.dataimport.handler.Result;
import org.openmf.mifos.dataimport.http.RestClient;
import org.openmf.mifos.dataimport.populator.AbstractWorkbookPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatatableWorkbookPopulator extends AbstractWorkbookPopulator {
    
    private static final Logger logger = LoggerFactory
                         .getLogger(DatatableWorkbookPopulator.class);

    private final RestClient client;
    private String content;
    private String datatable;
    private List<Column> columns;

    public DatatableWorkbookPopulator(String datatable, RestClient client) {
        this.datatable = datatable;
        this.client = client;
    }
	
    @Override
    public Result downloadAndParse() {
        Result result = new Result();
        try {
                client.createAuthToken();
                columns = new ArrayList<Column>();
                content = client.get("datatables/" + datatable + "/");                
                Gson gson = new Gson();
                JsonObject json = new JsonParser().parse(content).getAsJsonObject();
                JsonArray array = json.getAsJsonArray("columnHeaderData");
              Iterator<JsonElement> iterator = array.iterator();
               while (iterator.hasNext()) {
                       JsonElement jsonElement = iterator.next();
                       Column column = gson.fromJson(jsonElement, Column.class);
                       columns.add(column);
               }
        }catch (Exception e) {
                    result.addError(e.getMessage());
                    e.printStackTrace();
        }
        return result;
    }

	
    @Override
    public Result populate(Workbook workbook) {

        Sheet datatableSheet = workbook.createSheet(datatable);
        Result result = new Result();
        setLayout(datatableSheet);
                
	return result;
    }

    private void setLayout(Sheet worksheet) {
        Row rowHeader = worksheet.createRow(0);
        rowHeader.setHeight((short)500);

        int columnIndex = 0;
        for (Column column : columns) {     
            
                logger.debug("Column Name: " + column.getColumnName() + " - ID: " + columnIndex);
                
                worksheet.setColumnWidth(columnIndex, 4000);
                writeString(columnIndex, rowHeader, column.getColumnName());
                columnIndex++;
        }
    }

    private Result setRules(Sheet worksheet) {
            Result result = new Result();
            return result;
    }
	
}
