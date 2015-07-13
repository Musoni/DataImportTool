package org.openmf.mifos.dataimport.handler.client;

import org.openmf.mifos.dataimport.handler.loan.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openmf.mifos.dataimport.handler.AbstractDataImportHandler;
import org.openmf.mifos.dataimport.handler.Result;
import org.openmf.mifos.dataimport.http.RestClient;
import org.openmf.mifos.dataimport.dto.client.ClientIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientIdentifierDataImportHandler extends AbstractDataImportHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoanDataImportHandler.class);

    @SuppressWarnings("CPD-START")
    private static final int CLIENT_NAME_COL = 0;
    private static final int DOC_TYPE_COL = 1;
    private static final int DOC_KEY_COL = 2;
    private static final int DOC_DESC_COL = 3;
    private static final int STATUS_COL = 4;
    private static final int DOC_ID_COL = 5;
    private static final int FAILURE_REPORT_COL = 6;

    @SuppressWarnings("CPD-END")
    private List<ClientIdentifier> clientIdentifiers = new ArrayList<ClientIdentifier>();
    private final RestClient restClient;
    private final Workbook workbook;

    public ClientIdentifierDataImportHandler(Workbook workbook, RestClient client) {
        this.workbook = workbook;
        this.restClient = client;
    }

    @Override
    public Result parse() {
        Result result = new Result();
        Sheet clientIdentifierSheet = workbook.getSheet("ClientIdentifiers");
        Integer noOfEntries = getNumberOfRows(clientIdentifierSheet, 0);
        for (int rowIndex = 1; rowIndex < noOfEntries; rowIndex++) {
            Row row;
            try {
                row = clientIdentifierSheet.getRow(rowIndex);
                if (isNotImported(row, STATUS_COL)) {
                    clientIdentifiers.add(parseAsClientIdentifier(row));
                }
            } catch (RuntimeException re) {
                logger.error("row = " + rowIndex, re);
                result.addError("Row = " + rowIndex + " , " + re.getMessage());
            }
        }

        return result;
    }

    private ClientIdentifier parseAsClientIdentifier(Row row) {

        String clientName = readAsString(CLIENT_NAME_COL, row);
        String clientId = getIdByName(workbook.getSheet("Clients"), clientName).toString();
        String documentTypeName = readAsString(DOC_TYPE_COL, row);
        String documentTypeId = getIdByName(workbook.getSheet("IdentifierTypes"), documentTypeName).toString();
        String documentKey = readAsString(DOC_KEY_COL, row);
        String description = readAsString(DOC_DESC_COL, row);
        
        return new ClientIdentifier(clientId, documentTypeId,  documentKey,  description, row.getRowNum(), null);
       
    }


    @Override
    public Result upload() {
        Result result = new Result();
        Sheet clientIdentifierSheet = workbook.getSheet("ClientIdentifiers");
        restClient.createAuthToken();
        for (ClientIdentifier clientIdentifier : clientIdentifiers) {
            try {
                Gson gson = new Gson();
                String payload = gson.toJson(clientIdentifier);
                // logger.debug(payload);
                restClient.post("clients/" + clientIdentifier.getClientId() + "/identifiers", payload);
                
                Cell statusCell = clientIdentifierSheet.getRow(clientIdentifier.getRowIndex()).createCell(STATUS_COL);
                statusCell.setCellValue("Imported");
            } catch (RuntimeException e) {
            	String message = parseStatus(e.getMessage());
            	Cell statusCell = clientIdentifierSheet.getRow(clientIdentifier.getRowIndex()).createCell(STATUS_COL);
            	statusCell.setCellValue(message);
                result.addError("Row = " + clientIdentifier.getRowIndex() + " ," + message);
            }
        }
        clientIdentifierSheet.setColumnWidth(STATUS_COL, 15000);
    	writeString(STATUS_COL, clientIdentifierSheet.getRow(0), "Status");
        return result;
    }
}


