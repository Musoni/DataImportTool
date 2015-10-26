package org.openmf.mifos.dataimport.exception;

/** 
 * {@link RuntimeException} thrown when a required URL parameter is not provided 
 **/
public class MissingRequiredUrlParameterException extends RuntimeException {
    /**
     * @see http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     **/
    private static final long serialVersionUID = -392589182630403406L;
    
    /** 
     * Constructs a new MissingRequiredUrlParameterException with no detail message
     **/
    public MissingRequiredUrlParameterException() {
        super();
    }

    /** 
     * Constructs a new MissingRequiredUrlParameterException with the specified detail message and cause. 
     * Note that the detail message associated with cause is not automatically 
     * incorporated in this exception's detail message.
     * 
     * @param message -- the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause -- the cause (which is saved for later retrieval by the Throwable.getCause() method). 
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     **/
    public MissingRequiredUrlParameterException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /** 
     * Constructs a new MissingRequiredUrlParameterException with the specified detail message. 
     * A detail message is a String that describes this particular exception.
     * 
     * @param message -- the String that contains a detailed message
     **/
    public MissingRequiredUrlParameterException(String message) {
        super(message);
    }
    
    /** 
     * Constructs a new exception with the specified cause and a detail message of 
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause). 
     * This constructor is useful for exceptions that are little more than wrappers for other throwables 
     * (for example, java.security.PrivilegedActionException).
     * 
     * @param cause -- the cause (which is saved for later retrieval by the Throwable.getCause() method). 
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     **/
    public MissingRequiredUrlParameterException(Throwable cause) {
        super(cause);
    }
}
