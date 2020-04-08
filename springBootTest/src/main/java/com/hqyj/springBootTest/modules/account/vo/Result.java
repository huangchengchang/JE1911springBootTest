package com.hqyj.springBootTest.modules.account.vo;

public class Result {
	private int status;
	private String message;
	private Object object;

	public Result(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message, Object object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", object=" + object + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
	public enum ResultStatus {
		SUCCESS(200),FAILED(500);
		
		public int status;
		
		private ResultStatus(int status) {
			this.status = status;
		}
	}
}
