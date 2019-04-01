package requesthandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.Server;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread {

	private Socket client;

	public RequestHandler(Socket client) {
		this.client = client;
	}

	public void run() {

		Request request = getRequest();
		Response response;

		if (request != null) {

			state.State state = Server.getState(request.id);
			state.State newState = state.handle(request.event);
			Server.updateState(request.id, newState);

			response = new Response(!newState.isErrorState, newState.getText());
		} else {
			response = new Response(false, "Invalid Request!");
		}

		sendResponse(response);
	}

	private Request getRequest() {
		ObjectInputStream objectInputStream;
		try {

			InputStream inputStream = client.getInputStream();

			objectInputStream = new ObjectInputStream(inputStream);
			Object obj = objectInputStream.readObject();

			ObjectMapper objectMapper = new ObjectMapper();
			if (obj instanceof String) {
				return objectMapper.readValue(obj.toString(), Request.class);
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error parsing request!");
		}

		return null;
	}

	private void sendResponse(Response response) {

		ObjectMapper objectMapper = new ObjectMapper();
		String str;
		try {
			str = objectMapper.writeValueAsString(response);
			ObjectOutputStream objectOutputStream;
			try (OutputStream output = client.getOutputStream()) {
				objectOutputStream = new ObjectOutputStream(output);
				objectOutputStream.writeObject(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
