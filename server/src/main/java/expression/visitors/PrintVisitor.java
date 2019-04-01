package expression.visitors;

import expression.Expression;
import expression.LeafExpression;

public class PrintVisitor implements IVisitor {
	@Override
	public void visit(Expression expression) {
		expression.getLeftOperand().accept(this);
		System.out.print(" " + expression.getOperator().toString() + " ");
		expression.getRightOperand().accept(this);

	}

	@Override
	public void visit(LeafExpression expression) {
		System.out.print((int) expression.getValue());
	}
}
