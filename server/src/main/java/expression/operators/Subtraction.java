package expression.operators;

import expression.IExpression;

public class Subtraction implements IOperator {
	@Override
	public double calculate(IExpression left, IExpression right) {
		return left.getValue() - right.getValue();
	}

	public String toString() {
		return "-";
	}
}
