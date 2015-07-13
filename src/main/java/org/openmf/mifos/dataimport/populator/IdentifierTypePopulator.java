package org.openmf.mifos.dataimport.populator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openmf.mifos.dataimport.dto.IdentifierType;
import org.openmf.mifos.dataimport.handler.Result;
import org.openmf.mifos.dataimport.http.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openmf.mifos.dataimport.dto.PaymentType;


public class IdentifierTypePopulator extends AbstractWorkbookPopulator {

	private static final Logger logger = LoggerFactory
			.getLogger(IdentifierTypePopulator.class);

	private final RestClient client;
	private static final int ID_COL = 0;
	private static final int VALUE_COL = 1;

	private List<IdentifierType> identifierTypes;
	private ArrayList<String> identifierNames;

	public IdentifierTypePopulator(RestClient client) {
		this.client = client;
	}

	private String content;

	@Override
	public Result downloadAndParse() {
		Result result = new Result();
        try {
        	client.createAuthToken();
                identifierTypes = new ArrayList<IdentifierType>();
                content = client.get("codes/1/codevalues");
                Gson gson = new Gson();
                JsonElement json = new JsonParser().parse(content);
		JsonArray array = json.getAsJsonArray();
                Iterator<JsonElement> iterator = array.iterator();
                while (iterator.hasNext()) {
                        json = iterator.next();
                        IdentifierType identifierType = gson
                                        .fromJson(json, IdentifierType.class);
                        identifierTypes.add(identifierType);
                }
        } catch (Exception e) {
            result.addError(e.getMessage());
            logger.error(e.getMessage());
        }
		return result;
	}

	@Override
	public Result populate(Workbook workbook) {
		Result result = new Result();
    	try{
            int rowIndex = 1;
            Sheet identifierSheet = workbook.createSheet("IdentifierTypes");
            setLayout(identifierSheet);
            for (IdentifierType identifier : identifierTypes) {
                                    Row row = identifierSheet.createRow(rowIndex++);
                                    writeInt(ID_COL, row, identifier.getId());
                                    writeString(VALUE_COL, row, identifier.getName());
                            }
        
            identifierSheet.protectSheet("");
    	} catch (Exception e) {
    		result.addError(e.getMessage());
    		logger.error(e.getMessage());
    	}
        return result;
	}

	private void setLayout(Sheet worksheet) {
		worksheet.setColumnWidth(ID_COL, 2000);
        worksheet.setColumnWidth(VALUE_COL, 7000);
        Row rowHeader = worksheet.createRow(0);
        rowHeader.setHeight((short)500);
        writeString(ID_COL, rowHeader, "Identifier ID");
        writeString(VALUE_COL, rowHeader, "Identifier Name");
    }
	
	public Integer getIdentifierTypesSize(){
		return identifierTypes.size();
	}
	}


