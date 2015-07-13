package org.openmf.mifos.dataimport.populator.client;

import org.openmf.mifos.dataimport.populator.loan.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.openmf.mifos.dataimport.dto.loan.LoanProduct;
import org.openmf.mifos.dataimport.handler.Result;
import org.openmf.mifos.dataimport.populator.AbstractWorkbookPopulator;
import org.openmf.mifos.dataimport.populator.ClientSheetPopulator;
import org.openmf.mifos.dataimport.populator.OfficeSheetPopulator;
import org.openmf.mifos.dataimport.populator.IdentifierTypePopulator;



public class ClientIdentifierWorkbookPopulator extends AbstractWorkbookPopulator {
	
	private OfficeSheetPopulator officeSheetPopulator;
	private ClientSheetPopulator clientSheetPopulator;
	private IdentifierTypePopulator identifierTypePopulator;
        
	
	@SuppressWarnings("CPD-START")
    private static final int CLIENT_NAME_COL = 0;
    private static final int DOC_TYPE_COL = 1;
    private static final int DOC_KEY_COL = 2;
    private static final int DOC_DESC_COL = 3;

    @SuppressWarnings("CPD-END")
	
	public ClientIdentifierWorkbookPopulator(OfficeSheetPopulator officeSheetPopulator, ClientSheetPopulator clientSheetPopulator, IdentifierTypePopulator identifierTypePopulator) {
    	this.officeSheetPopulator = officeSheetPopulator;
    	this.clientSheetPopulator = clientSheetPopulator;
        this.identifierTypePopulator = identifierTypePopulator;
         }
	
	    @Override
	    public Result downloadAndParse() {
	    	Result result =  officeSheetPopulator.downloadAndParse();
	    	if(result.isSuccess())
	    		result = clientSheetPopulator.downloadAndParse();
                if(result.isSuccess())
	    		result = identifierTypePopulator.downloadAndParse();
	    	return result;
	    }

	    @Override
	    public Result populate(Workbook workbook) {
	    	Sheet clientIdentifierWorkbook = workbook.createSheet("ClientIdentifiers");
	    	Result result = officeSheetPopulator.populate(workbook);
	    	if(result.isSuccess())
	    		result = clientSheetPopulator.populate(workbook);
                if(result.isSuccess())
	    		result = identifierTypePopulator.populate(workbook);
	    	setLayout(clientIdentifierWorkbook);
	    	if(result.isSuccess())
	            result = setRules(clientIdentifierWorkbook);
	    	if(result.isSuccess()) 
	            result = setDefaults(clientIdentifierWorkbook);
	    	
	        return result;
	    }
	    
	    private void setLayout(Sheet worksheet) {
	    	Row rowHeader = worksheet.createRow(0);
	        rowHeader.setHeight((short)500);
            worksheet.setColumnWidth(CLIENT_NAME_COL, 4000);
            worksheet.setColumnWidth(DOC_TYPE_COL, 4000);
            worksheet.setColumnWidth(DOC_KEY_COL, 4000);
            worksheet.setColumnWidth(DOC_DESC_COL, 3200);
          
            writeString(CLIENT_NAME_COL, rowHeader, "Client Name*");
            writeString(DOC_TYPE_COL, rowHeader, "Document Type*");
            writeString(DOC_KEY_COL, rowHeader, "Document Key*");
            writeString(DOC_DESC_COL, rowHeader, "Description");
            
            CellStyle borderStyle = worksheet.getWorkbook().createCellStyle();
            CellStyle doubleBorderStyle = worksheet.getWorkbook().createCellStyle();
            borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
            doubleBorderStyle.setBorderBottom(CellStyle.BORDER_THIN);
            doubleBorderStyle.setBorderRight(CellStyle.BORDER_THICK);
            for(int colNo = 0; colNo < 35; colNo++) {
            	Cell cell = rowHeader.getCell(colNo);
            	if(cell == null)
            		rowHeader.createCell(colNo);
            	rowHeader.getCell(colNo).setCellStyle(borderStyle);
            }
	    }
	    
	    private Result setRules(Sheet worksheet) {
	    	Result result = new Result();
	    	try {
                        CellRangeAddressList clientNameRange = new  CellRangeAddressList(1, SpreadsheetVersion.EXCEL97.getLastRowIndex(), CLIENT_NAME_COL, CLIENT_NAME_COL);
	              	CellRangeAddressList docTypeRange = new CellRangeAddressList(1, SpreadsheetVersion.EXCEL97.getLastRowIndex(), DOC_TYPE_COL, DOC_TYPE_COL);
	        	
	        	DataValidationHelper validationHelper = new HSSFDataValidationHelper((HSSFSheet)worksheet);
	        	
	        	setNames(worksheet);
                        DataValidationConstraint clientNameConstraint = validationHelper.createFormulaListConstraint("Client");
                        DataValidationConstraint docTypeConstraint = validationHelper.createFormulaListConstraint("IdentifierTypes");
                        
	        	DataValidation clientValidation = validationHelper.createValidation(clientNameConstraint, clientNameRange);
	        	DataValidation docTypeValidation = validationHelper.createValidation(docTypeConstraint, docTypeRange);

                        worksheet.addValidationData(clientValidation);
                        worksheet.addValidationData(docTypeValidation);
	           
	          
	    	} catch (RuntimeException re) {
	    		result.addError(re.getMessage());
	    	}
	       return result;
	    }
	    
	    private Result setDefaults(Sheet worksheet) {
	    	Result result = new Result();
                
	       return result;
	    }
	    
	    private void setNames(Sheet worksheet) {
	    	Workbook clientIdentifierWorkbook = worksheet.getWorkbook();
       
    		Name clientName = clientIdentifierWorkbook.createName();
    	        clientName.setNameName("Client");
    	        clientName.setRefersToFormula("Clients!$B$2:$B" + (clientSheetPopulator.getClientsSize()+ 1));

        	//Product Name
        	Name identifierGroup = clientIdentifierWorkbook.createName();
        	identifierGroup.setNameName("IdentifierTypes");
        	identifierGroup.setRefersToFormula("IdentifierTypes!$B$2:$B" + (identifierTypePopulator.getIdentifierTypesSize() + 1));
        	
	    }
	    
}
