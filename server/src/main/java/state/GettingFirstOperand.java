package state;

import expression.Expression;
import expression.LeafExpression;
import expression.Parser;
import expression.Type;

public class GettingFirstOperand extends State {
	public GettingFirstOperand(Expression expression) {
		super(expression);
	}

	@Override
	public State handle(String event) {

		Type eventType = Parser.getType(event);
		if (eventType == null) return this;

		switch (eventType) {
			case DIGIT:
				LeafExpression leaf = (LeafExpression) expression.getLeftOperand();
				leaf.addDigit(event);
				return this;

			case CLEAR:
				return new StartState(null);

			case EQUALS:
				return new ErrorGettingFirstOperand(expression);

			case OPERATOR:
				expression.setOperator(Parser.getOperator(event));
				return new WaitingForNextOperand(expression);

			default:
				return this;

		}
	}

	@Override
	public String getText() {
		return String.valueOf(expression.getLeftOperand().getValue());
	}
}
