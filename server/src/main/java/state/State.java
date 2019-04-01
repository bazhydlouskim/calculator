package state;

import expression.Expression;

public abstract class State {

	public boolean isErrorState;
	Expression expression;

	public State(Expression expression) {
		this.expression = expression;
	}

	public abstract State handle(String event);
	public abstract String getText();
}
