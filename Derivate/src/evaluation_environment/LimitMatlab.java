package evaluation_environment;

import java.io.StringWriter;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import ast.Operation;

public class LimitMatlab implements LimitInterface{
	private Operation op;
	private Double value;
	private MatlabEngine eng;
	
	public LimitMatlab(Operation op, Double value)
	{
		if (op == null || value == null) throw new IllegalArgumentException("Invalid operation or tending value");
		this.op = op;
		this.value = value;
		try { eng = MatlabEngine.startMatlab(); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public LimitMatlab() {
		try { eng = MatlabEngine.startMatlab(); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public void setValue(Operation op, Double value){
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
		StringWriter writer = new StringWriter();
		try {
			eng.eval("syms x h;", null, null);
			eng.eval("f = " + op, null, null);
			if (!value.equals(Double.POSITIVE_INFINITY) && !value.equals(Double.NEGATIVE_INFINITY))
				eng.eval("double(limit(f,x," + value + ",'left'))", writer, null);
			else
				throw new IllegalStateException("Could not evaluate right hand limit for INF value");
		}
		catch (Exception e) {System.err.println("Matlab error:\n"); e.printStackTrace(); }
		String result = writer.toString().split("=")[1].trim();
		if (result.equals("Inf") || result.equals("-Inf"))
				return Double.MAX_VALUE;
		else
			return Double.valueOf(result);
	}
	
	public Double rightLimit(){
		StringWriter writer = new StringWriter();
		try {
			eng.eval("syms x h;", null, null);
			eng.eval("f = " + op, null, null);
			if (!value.equals(Double.POSITIVE_INFINITY) && !value.equals(Double.NEGATIVE_INFINITY))
				eng.eval("double(limit(f,x," + value + ",'right'))", writer, null);
			else
				throw new IllegalStateException("Could not evaluate right hand limit for INF value");
		}
		catch (Exception e) {System.err.println("Matlab error:\n"); e.printStackTrace(); } 
		String result = writer.toString().split("=")[1].trim();
		if (result.equals("Inf") || result.equals("-Inf"))
			return Double.MAX_VALUE;
	else
		return Double.valueOf(result);
	}
	
	public boolean exists() {
		return (Math.round(leftLimit()) == Math.round(rightLimit()));
	}
	
	public Double getLimit(){
		if (value.equals(Double.POSITIVE_INFINITY)){
			StringWriter writer = new StringWriter();
			try {
				eng.eval("syms x h;", null, null);
				eng.eval("f = " + op, null, null);
				eng.eval("double(limit(f,x,Inf))", writer, null);
				String result = writer.toString().split("=")[1].trim();
				if (result.equals("Inf") || result.equals("-Inf"))
					return Double.POSITIVE_INFINITY;
			else
				return Double.valueOf(result);
			}
			catch (Exception e) {}
		}
		else if (value.equals(Double.NEGATIVE_INFINITY)){
			StringWriter writer = new StringWriter();
			try {
				eng.eval("syms x h;", null, null);
				eng.eval("f = " + op, null, null);
				eng.eval("double(limit(f,x,-Inf))", writer, null);
				String result = writer.toString().split("=")[1].trim();
				if (result.equals("Inf"))
					return Double.POSITIVE_INFINITY;
				else if (result.equals("-Inf"))
					return Double.NEGATIVE_INFINITY;
			else
				return Double.valueOf(result);
			}
			catch (Exception e) {}
		}
		
		if (!exists())
			throw new IllegalStateException("Limit does not exist");
		
		return (leftLimit());
	}

	public void close() throws EngineException {
		eng.close();
	}
}
