package state;

import expression.*;
import expression.visitors.FileOutputVisitor;
import expression.visitors.PrintVisitor;
import server.Server;

public class Calculate extends State {
	public Calculate(Expression expression) {
		super(expression);

		System.out.println("Current expression: ");
		expression.accept(new PrintVisitor());
		System.out.println(" = " + expression.getValue());

		Server.addToHistory(expression);

		System.out.println("Expression history: ");
		for (IExpression exp : Server.getHistory()) {
			exp.accept(new PrintVisitor());
			System.out.println(" = " + expression.getValue());
		}
		System.out.println();

		System.out.println("Number of expressions in the history" + Server.getHistory().size());

		new FileOutputVisitor().initialize(expression);


	}

	@Override
	public State handle(String event) {
		Type eventType = Parser.getType(event);
		if (eventType == null) return this;

		switch (eventType) {
			case DIGIT:
				Expression newExpression = new Expression();
				LeafExpression leaf = new LeafExpression();
				leaf.addDigit(event);
				newExpression.setLeftOperand(leaf);
				return new GettingFirstOperand(newExpression);

			default:
				return new StartState(null);

		}
	}

	@Override
	public String getText() {
		return String.valueOf(expression.getValue());
	}
}
