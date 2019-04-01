package expression;

import expression.visitors.IVisitor;

public interface IExpression {

	void accept(IVisitor visitor);
	double getValue();
}
