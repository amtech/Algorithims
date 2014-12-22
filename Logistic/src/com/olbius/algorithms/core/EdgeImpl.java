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
	
	public EdgeImpl(int start, int target, int weight) {
		this.start = new NodeImpl(start);
		this.target = new NodeImpl(target);
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
	
	@Override
	public String toString() {
		return start + " " + target + " " + weight;
	}
}
