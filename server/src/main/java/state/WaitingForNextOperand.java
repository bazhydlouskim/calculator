package state;

import expression.Expression;
import expression.LeafExpression;
import expression.Parser;
import expression.Type;

public class WaitingForNextOperand extends State {
	public WaitingForNextOperand(Expression expression) {
		super(expression);
	}

	@Override
	public State handle(String event) {
		Type eventType = Parser.getType(event);
		if (eventType == null) return this;

		switch (eventType) {
			case DIGIT:
				LeafExpression leaf = new LeafExpression();
				leaf.addDigit(event);
				expression.setRightOperand(leaf);
				return new GettingSecondOperand(expression);

			case CLEAR:
				return new StartState(null);

			case EQUALS:
			case OPERATOR:
				return new ErrorWaitingForNextOperand(expression);

			default:
				return this;

		}
	}

	@Override
	public String getText() {
		return expression.getLeftOperand().getValue() + " " + expression.getOperator().toString();
	}
}
