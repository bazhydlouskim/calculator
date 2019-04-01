package expression.operators;

import expression.IExpression;

public class Multiplication implements IOperator {
	@Override
	public double calculate(IExpression left, IExpression right) {
		return left.getValue() * right.getValue();
	}

	public String toString() {
		return "*";
	}
}
