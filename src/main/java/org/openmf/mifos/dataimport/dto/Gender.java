package org.openmf.mifos.dataimport.dto;


public class Gender {

    private final Integer id;
    
    private final String name;
    
	public Gender(Integer id, String name) {
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
