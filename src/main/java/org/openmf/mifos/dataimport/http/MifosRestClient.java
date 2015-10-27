package org.openmf.mifos.dataimport.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

import org.openmf.mifos.dataimport.http.SimpleHttpRequest.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MifosRestClient implements RestClient {
	
	private final String baseURL;

    private final String tenantId;

    private HttpServletRequest request;

    private String authToken;
    
    static {
    	if(System.getProperty("mifos.endpoint").contains("localhost")) {
	    //for localhost testing only
	       HttpsURLConnection.setDefaultHostnameVerifier(
	       new HostnameVerifier(){

	    	  @Override
	          public boolean verify(String hostname, @SuppressWarnings("unused") SSLSession sslSession) {
	              if (hostname.equals("localhost")) {
	                  return true;
	              }
	              return false;
	          }
	       });
    	}
	}
    
    public MifosRestClient(HttpServletRequest request) {

        this.request = request;
        this.baseURL  = System.getProperty("mifos.endpoint");
        this.tenantId = System.getProperty("mifos.tenant.id");
        
        // set the value of the "authToken" variable to the Authorization from the current HTTP request
        authToken = this.request.getHeader("Authorization");
    };

    public static final class Header {
        public static final String AUTHORIZATION = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String MIFOS_TENANT_ID = "X-Mifos-Platform-TenantId";
    }
    

    @Override
    public String post(String path, String payload) {
        String url = baseURL + path;
        try {
                SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.POST)
                                .addHeader(Header.AUTHORIZATION, authToken)
                                .addHeader(Header.CONTENT_TYPE, "application/json; charset=utf-8")
                                .addHeader(Header.MIFOS_TENANT_ID, tenantId)
                                .withContent(payload).execute();

                String content = readContentAndClose(response.getContent());
            if (response.getStatus() != HttpURLConnection.HTTP_OK) 
              { 
            	throw new IllegalStateException(content);
              }
            return content;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public String get(String path) {
    	String url = baseURL + path;
    	try {
    		      SimpleHttpResponse response = new HttpRequestBuilder().withURL(url).withMethod(Method.GET)
    		    		          .addHeader(Header.AUTHORIZATION, authToken)
    		    		          .addHeader(Header.MIFOS_TENANT_ID,tenantId)
    		    		          .execute();
    		      
    		      String content = readContentAndClose(response.getContent());
    		      if(response.getStatus() != HttpURLConnection.HTTP_OK)
    		      {
    		    	  throw new IllegalStateException(content);
    		      }
    		      return content;
    	} catch (IOException e) {
    		  throw new IllegalStateException(e);
    	}
    }

    private String readContentAndClose(InputStream content) throws IOException {
        InputStreamReader stream = new InputStreamReader(content,"UTF-8");
        BufferedReader reader = new BufferedReader(stream);
        String data = reader.readLine();
        stream.close();
        reader.close();
        return data;
    }
}
