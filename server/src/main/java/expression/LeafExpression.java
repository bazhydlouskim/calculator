package expression;

import expression.visitors.IVisitor;

public class LeafExpression implements IExpression {

	private long value = 0;

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public double getValue() {
		return value;
	}

	public void addDigit(String digit) {
		if (value == 0) {
			try {
				value = Long.parseLong(digit);
			} catch (Exception e) {
				System.err.println("Cannot parse digit " + digit);
			}
		} else {
			String newValue = value + digit;
			try {
				value = Long.parseLong(newValue);
			} catch (Exception e) {
				System.err.println("Cannot parse digit " + digit);
			}
		}
	}
}
