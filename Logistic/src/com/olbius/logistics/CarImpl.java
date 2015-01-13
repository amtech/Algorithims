package com.olbius.logistics;

import com.olbius.algorithms.core.Node;

public class CarImpl implements Car{

	private int maxWeight;
	private int weight;
	private Node curNode;
	
	public CarImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public CarImpl(int max) {
		this.maxWeight = max;
	}
	
	@Override
	public Object getWeight() {
		return this.weight;
	}

	@Override
	public Object getMaxWeight() {
		return this.maxWeight;
	}

	@Override
	public Node getCurNode() {
		return this.curNode;
	}

	@Override
	public void setCurNode(Node node) {
		this.curNode = node;
	}

	@Override
	public void setWeight(Object weight) {
		this.weight = (int) weight;
	}

	@Override
	public boolean isAdd(Object weight) {
		return this.weight + (int)weight <= this.maxWeight;
	}

}
