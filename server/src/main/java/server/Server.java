package server;

import expression.IExpression;
import requesthandler.RequestHandler;
import state.StartState;
import state.State;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

	private static Map<Long, State> states;

	private static ArrayList<IExpression> history;

	public static void main (String[] args) {

		states = new HashMap<>();
		history = new ArrayList<>();

		System.out.println("Server started...");
		try {
			ServerSocket socket = new ServerSocket(7777);

			while (true) {
				Socket client = socket.accept();
				RequestHandler rh = new RequestHandler(client);
				rh.start();
			}

		} catch (Exception e1) {
			System.err.println("Error occurred while communicating with the request handler");
			e1.printStackTrace();
		}
	}

	public synchronized static State getState(long index) {
		State state = states.get(index);
		if (state == null) {
			state = new StartState(null);
			states.put(index, state);
		}
		return state;
	}

	public synchronized static void updateState(long index, State state) {
		states.put(index, state);
	}

	public synchronized static void addToHistory(IExpression expression) {
		history.add(expression);
	}

	public static List<IExpression> getHistory() {
		return history;
	}
}
