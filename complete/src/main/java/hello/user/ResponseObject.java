package hello.user;

public class ResponseObject {
	private String resMsg;
	
	private String userId;
	
	private String  valErrors;
	
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getValErrors() {
		return valErrors;
	}
	public void setValErrors(String valErrors) {
		this.valErrors = valErrors;
	}
	
}
