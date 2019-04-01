package requesthandler;

public class Response {
	public boolean isSuccessful;
	public String result;

	public Response(boolean isSuccessful, String result) {
		this.isSuccessful = isSuccessful;
		this.result = result;
	}
}
