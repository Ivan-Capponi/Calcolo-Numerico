package test;

import java.util.HashMap;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import ast.*;
import derivation.DerivateComputation;

public class TestClass {
	public static void main(String[] args){
		HashMap <String, Double> h = new HashMap <String, Double>();
		h.put("x", new Double(2));
		Operation op = new Atan(new Product(new Constant("2"), new SimpleVar()));
		op = op.accept(new DerivateComputation());
		System.out.println(op.toString());
		//System.out.println(op.getNumericResult());
		Expression e = new ExpressionBuilder("2/(1+(2x)^2)").variable("x").build();
		e.setVariable("x", 2);
		System.out.println(e.evaluate());
	}
}
