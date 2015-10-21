package org.openmf.mifos.dataimport.dto.datatable;

import org.openmf.mifos.dataimport.dto.*;


public class ColumnValue {
	
	private final Integer id;
	
	
	private final String value;

	public ColumnValue(Integer id,  String value) {
		this.id = id;
		this.value = value;
	}
	
	public Integer getId() {
    	return this.id;
    }
	
	
	public String getValue() {
    	return this.value;
    }

}
