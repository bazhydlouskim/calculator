package state;

import expression.Expression;
import expression.LeafExpression;
import expression.Parser;
import expression.Type;

public class GettingSecondOperand extends State {
	public GettingSecondOperand(Expression expression) {
		super(expression);
	}

	@Override
	public State handle(String event) {
		Type eventType = Parser.getType(event);
		if (eventType == null) return this;

		switch (eventType) {
			case DIGIT:
				LeafExpression leaf = (LeafExpression) expression.getRightOperand();
				leaf.addDigit(event);
				return this;

			case CLEAR:
				return new StartState(null);

			case EQUALS:
				return new Calculate(expression);

			case OPERATOR:
				/////////!!!!!!!!!!!!!!!!!!!!!!!!!!\\\\\\\\\\\\\\
				Expression newExpression = new Expression();
				newExpression.setLeftOperand(expression);
				newExpression.setOperator(Parser.getOperator(event));
				return new WaitingForNextOperand(newExpression);

			default:
				return this;

		}
	}

	@Override
	public String getText() {
		return String.valueOf(expression.getRightOperand().getValue());
	}
}
