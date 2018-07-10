package evaluation_environment;

import ast.Operation;

public class Limit implements LimitInterface {

	private Operation op;
	private Double value;
	private final int PRECISION = 8;
	private final double SCRAP = 0.01;
	
	public Limit(Operation op, Double value)
	{
		if (op == null || value == null) throw new IllegalArgumentException("Invalid operation or tending value");
		this.op = op;
		this.value = value;
	}
	
	public Operation getOp(){
		return op;
	}
	
	public Double getValue(){
		return value;
	}
	
	public Double leftLimit(){
		Double approx = new Double(value - 0.1);
		Double eval = null;
		
		for (int i = 1; i != PRECISION; i++)
		{
			try
			{
				eval = op.getNumericResult(approx);
				approx = value - (0.1 / Math.pow(10, i));
			}
			catch (Exception e) { break; }
		}
		
		return eval;
	}
	
	public Double rightLimit(){
		Double approx = new Double(value + 0.1);
		Double eval = null;
		
		for (int i = 1; i != PRECISION; i++)
		{
			try
			{
				eval = op.getNumericResult(approx);
				approx = value + ((0.1)/(Math.pow(10,i)));
			}
			catch (Exception e) { break; }
		}
		
		return eval;
	}
	
	public boolean exists() {
		return (Math.abs(Math.abs(leftLimit()) - Math.abs(rightLimit())) < SCRAP);
	}
	
	public Double getLimit(){
		if (value.equals(Double.POSITIVE_INFINITY))
			return getPositiveInf();
		if (value.equals(Double.NEGATIVE_INFINITY))
			return getNegativeInf();
		if (!exists())
			throw new IllegalStateException("Limit does not exist");
		
		return (leftLimit() + rightLimit())/2;
	}

	private Double getNegativeInf() {
		double incrementalValue = 1.0;
		double returnValue = 0;
		for (int i = 0; i < PRECISION; i++, incrementalValue++) {
			try
			{
				returnValue = op.getNumericResult(-incrementalValue * Math.pow(10, incrementalValue));				
			}
			catch (Exception e) { break; }
		}
		
		return returnValue;
	}

	private Double getPositiveInf() {
		double incrementalValue = 1.0;
		double returnValue = 0;
		for (int i = 0; i < PRECISION; i++, incrementalValue++) {
			try
			{
				returnValue = op.getNumericResult(incrementalValue * Math.pow(10, incrementalValue));
				
			}
			catch (Exception e) { break; }
		}
		
		return returnValue;
	}
}
