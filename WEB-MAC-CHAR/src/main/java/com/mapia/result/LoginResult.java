package com.mapia.result;

// TODO @대훈 LoginResult와 SignUpResult에 상당히 많은 중복 코드 있음. AbstractResult와 같은 클래스 생성해 중복 제거한다.
public class LoginResult implements Result {
	private Status statusCode;
	private String errorMessage;
	
	private LoginResult(Status statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}
	
	public Status getStatusCode() {
		return statusCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static LoginResult ok() {
		return new LoginResult(Status.Ok, null);
	}
	
	public static LoginResult emailNotFound(String errorMessage) {
		return new LoginResult(Status.EmailNotFound, errorMessage);
	}
	
	public static LoginResult invalidPassword(String errorMessage) {
		return new LoginResult(Status.InvalidPassword, errorMessage);
	}
}
