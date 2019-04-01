package expression.visitors;

import expression.Expression;
import expression.IExpression;
import expression.LeafExpression;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileOutputVisitor implements IVisitor {

	//ArrayList<String> lines;
	private StringBuilder line;

	@Override
	public void visit(Expression expression) {
//		lines.add(expression.getOperator().toString());
//		lines.add("| \\");
//		line = new StringBuilder();
//		expression.getLeftOperand().accept(this);
//		line.append("  ");
//		expression.getRightOperand().accept(this);
//		lines.add(line.toString());
		line.append(expression.getOperator());
		line.append("| \\");
		expression.getLeftOperand().accept(this);
		expression.getRightOperand().accept(this);
		line.append("\n");
	}

	@Override
	public void visit(LeafExpression expression) {
		line.append((int) expression.getValue());
	}

	public void initialize(IExpression expression){
		String fileName = "out.txt";
		// = new ArrayList<>();
		line = new StringBuilder();
		expression.accept(this);

		try (FileWriter fileWriter = new FileWriter(fileName)) {
			try (PrintWriter printWriter = new PrintWriter(fileWriter)) {

//				for (String line : lines) {
//					printWriter.println(line);
//				}

				printWriter.println(line.toString());
			}
		} catch (IOException e) {
			System.err.println("Error occurred while writing to the output file. Quitting...");
			System.exit(-3);
		}
	}
}
