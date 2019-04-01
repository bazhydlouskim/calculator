package state;

import expression.Expression;
import expression.LeafExpression;
import expression.Parser;
import expression.Type;

public class StartState extends State {

	public StartState(Expression expression) {
		super(expression);
		this.expression = new Expression();
	}

	@Override
	public State handle(String event) {

		Type eventType = Parser.getType(event);
		if (eventType == null) return this;

		switch (eventType) {
			case DIGIT:
				LeafExpression leaf = new LeafExpression();
				leaf.addDigit(event);
				expression.setLeftOperand(leaf);
				return new GettingFirstOperand(expression);

			default:
				return this;

		}
	}

	@Override
	public String getText() {
		return "";
	}
}
