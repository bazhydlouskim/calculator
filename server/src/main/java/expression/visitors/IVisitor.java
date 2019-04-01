package expression.visitors;

import expression.Expression;
import expression.LeafExpression;

public interface IVisitor {
	void visit(Expression expression);
	void visit(LeafExpression expression);
}
