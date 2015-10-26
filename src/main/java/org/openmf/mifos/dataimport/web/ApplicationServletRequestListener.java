package org.openmf.mifos.dataimport.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.openmf.mifos.dataimport.exception.MissingRequiredUrlParameterException;
import org.openmf.mifos.dataimport.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ApplicationServletRequestListener implements ServletRequestListener {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationServletRequestListener.class);
    private static final String TENANT_IDENTIFIER_PARAMETER_KEY = "tenantIdentifier";

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        final ServletRequest servletRequest = sre.getServletRequest();
        final String tenantIdentifier = servletRequest.getParameter(TENANT_IDENTIFIER_PARAMETER_KEY);
        
        if (StringUtils.isNotBlank(tenantIdentifier)) {
            // log tenant identifier
            logger.info("Tenant Identifier: " + tenantIdentifier);
            
            System.setProperty("mifos.tenant.id", tenantIdentifier);
        }
        
        else {
            // if the "tenantIdentifier" variable is empty, then throw a "ServletException" exception
            try {
                throw new MissingRequiredUrlParameterException("Please provide a tenant identifier in the request URL (add tenantIdentifier to URL)");
            } 
            
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
