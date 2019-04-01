package expression;

import expression.operators.IOperator;
import expression.visitors.IVisitor;

public class Expression implements IExpression {

	private IExpression leftOperand;
	private IOperator operator;
	private IExpression rightOperand;

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public double getValue() {
		return operator.calculate(leftOperand, rightOperand);
	}

	public IExpression getLeftOperand() {
		return leftOperand;
	}

	public IExpression getRightOperand() {
		return rightOperand;
	}

	public IOperator getOperator() {
		return operator;
	}

	public void setLeftOperand(IExpression leftOperand) {
		this.leftOperand = leftOperand;
	}

	public void setRightOperand(IExpression rightOperand) {
		this.rightOperand = rightOperand;
	}

	public void setOperator(IOperator operator) {
		this.operator = operator;
	}
}
