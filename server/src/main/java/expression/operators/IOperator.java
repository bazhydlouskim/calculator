package expression.operators;

import expression.IExpression;

public interface IOperator {
	double calculate(IExpression left, IExpression right);
}
