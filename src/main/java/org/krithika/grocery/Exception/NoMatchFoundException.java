package org.krithika.grocery.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author krithika
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoMatchFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMatchFoundException(String errorText) {
		super(errorText);
	}
	
	public NoMatchFoundException(String errorText, Throwable t) {
		super(errorText, t);
	}

}
