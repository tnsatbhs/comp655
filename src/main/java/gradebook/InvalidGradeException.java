package gradebook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid grade")
public class InvalidGradeException extends RuntimeException {
	
	public InvalidGradeException(String exception)
	{
		super(exception);
	}

}