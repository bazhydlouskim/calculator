package expression;

import expression.actions.Clear;
import expression.actions.Equals;
import expression.actions.erroractions.DiscardAction;
import expression.actions.erroractions.ResetAction;
import expression.actions.erroractions.ErrorAction;
import expression.operators.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Parser {

	private static HashMap<String, Class> dataTypes;

	static {
		dataTypes = new HashMap<>(18);
		dataTypes.put("+", Addition.class);
		dataTypes.put("-", Subtraction.class);
		dataTypes.put("*", Multiplication.class);
		dataTypes.put("/", Division.class);

		dataTypes.put("=", Equals.class);

		dataTypes.put("0", Integer.class);
		dataTypes.put("1", Integer.class);
		dataTypes.put("2", Integer.class);
		dataTypes.put("3", Integer.class);
		dataTypes.put("4", Integer.class);
		dataTypes.put("5", Integer.class);
		dataTypes.put("6", Integer.class);
		dataTypes.put("7", Integer.class);
		dataTypes.put("8", Integer.class);
		dataTypes.put("9", Integer.class);

		dataTypes.put("reset", ResetAction.class);
		dataTypes.put("discard", DiscardAction.class);

		dataTypes.put("c", Clear.class);
	}

	public static Type getType(String text) {
		Class myClass = dataTypes.get(text.toLowerCase());
		if (myClass == null) return null;
		if (IOperator.class.isAssignableFrom(myClass)) {
			return Type.OPERATOR;
		} else if (ErrorAction.class.isAssignableFrom(myClass)) {
			return Type.ACTION;
		} else if (myClass.equals(Integer.class)) {
			return Type.DIGIT;
		} else if (myClass.equals(Equals.class)) {
			return Type.EQUALS;
		} else if (myClass.equals(Clear.class)) {
			return Type.CLEAR;
		}

		return null;
	}

	public static IOperator getOperator(String operator) {
		try {
			return (IOperator) dataTypes.get(operator).getConstructor().newInstance();
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
