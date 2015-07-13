package org.openmf.mifos.dataimport.dto.client;

import java.util.ArrayList;
import java.util.Locale;

public class ClientIdentifier {
	
	    private final transient Integer rowIndex;
	    private final transient String status;
	    private final transient String clientId;
            // private final Locale locale;	    
	    private final String description;	    
	    private final String documentKey;	    
	    private final String documentTypeId;
            
	    
	    public ClientIdentifier(String clientId, String documentTypeId, String documentKey, String description, Integer rowIndex, String status) {
	        this.documentTypeId = documentTypeId;
                this.clientId = clientId;
	        this.documentKey = documentKey;
	        this.description = description;
                this.status = status;
	        // this.locale = Locale.ENGLISH;
                this.rowIndex = rowIndex;
	    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getDocumentKey() {
        return documentKey;
    }

    public String getDocumentTypeId() {
        return documentTypeId;
    }

    public String getClientId() {
        return clientId;
    }

            

}
