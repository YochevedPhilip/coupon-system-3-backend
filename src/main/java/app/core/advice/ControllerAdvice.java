package app.core.advice;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.core.exceptions.CouponSystemException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = CouponSystemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail couponSystemErrorHandler(CouponSystemException exception) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
//		return ErrorDetails.builder().whatHappened("coupon system exception").whyHappened(exception.getMessage())
//				.build();
	}

	@ExceptionHandler(value = SQLException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails sqlErrorHandler(SQLException exception) {
		return ErrorDetails.builder().whatHappened("sql exception")
				.whyHappened("Something went wrong with database, plese try again later").build();
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails couponSystemErrorHandler(Exception exception) {
		return ErrorDetails.builder().whatHappened("General exception")
				.whyHappened("Something went wrong, please try again later").build();
	}

}
