package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {

	long id;

	public Client() {
		id = new Random().nextLong();
	}

	public Response sendEvent(String event) {

		System.out.println("Sending Event to the Server: " + event);
		Request request = new Request();
		request.id = id;
		request.event = event;
		ObjectMapper objectMapper = new ObjectMapper();
		String str = null;
		try {
			str = objectMapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		try {
			Socket socket = new Socket("localhost", 7777);

			OutputStream output = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
			objectOutputStream.writeObject(str);

			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			Object obj = objectInputStream.readObject();

			socket.close();

			if (obj instanceof String) {
				return objectMapper.readValue(obj.toString(), Response.class);
			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			System.err.println("Error occurred while communicating with the server!");
			e1.printStackTrace();
		}
		return null;
	}
}
