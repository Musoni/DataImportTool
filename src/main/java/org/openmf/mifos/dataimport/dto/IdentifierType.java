package org.openmf.mifos.dataimport.dto;


public class IdentifierType {

    private final Integer id;
    
    private final String name;
    
	public IdentifierType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
    	return this.id;
    }

    public String getName() {
        return this.name;
    }
}
