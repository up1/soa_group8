package demo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadParameterException extends RuntimeException{
	public BadParameterException(String name, Long value){
		super("Bad parameter \"" + name + "\" value: " + value);
	}
}
