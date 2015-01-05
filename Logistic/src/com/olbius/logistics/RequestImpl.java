package com.olbius.logistics;

public class RequestImpl implements Request {

	private int weight;
	
	public RequestImpl(int weight) {
		this.weight = weight;
	}
	
	@Override
	public Object getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		String s = "";
		s += Integer.toString(weight);
		return s;
	}
	
}
