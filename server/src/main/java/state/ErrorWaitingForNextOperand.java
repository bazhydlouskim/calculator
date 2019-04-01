package state;

import expression.Expression;
import expression.Parser;
import expression.Type;

public class ErrorWaitingForNextOperand extends State {
	public ErrorWaitingForNextOperand(Expression expression) {
		super(expression);
		isErrorState = true;
	}

	@Override
	public State handle(String event) {
		Type eventType = Parser.getType(event);
		if (eventType == null) return this;

		if (eventType == Type.ACTION) {
			if (event.toLowerCase().equals("discard")) {
				return new WaitingForNextOperand(expression);
			} else if (event.toLowerCase().equals("reset")) {
				return new StartState(null);
			}
		}
		return this;
	}

	@Override
	public String getText() {
		return "Cannot have multiple operators in the expression! \n" +
				"Would you like to dismiss the last action or reset to the initial state?";
	}
}
