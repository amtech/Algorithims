package com.olbius.algorithms.core;

public class EdgeImpl implements Edge{

	private Node start;
	private Node target;
	private int weight;
	
	public EdgeImpl(Node start, Node target) {
		this.start = start;
		this.target = target;
	}
	
	public EdgeImpl(Node start, Node target, int weight) {
		this.start = start;
		this.target = target;
		this.weight = weight;
	}
	
	@Override
	public Node getStart() {
		return start;
	}

	@Override
	public Node getTarget() {
		return target;
	}

	@Override
	public Object getWeight() {
		return weight;
	}

	@Override
	public void setWeight(Object weight) {
		this.weight = (Integer)weight;
	}
	
}
