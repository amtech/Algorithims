package com.olbius.logistics;

import com.olbius.algorithms.core.Node;

public class CarImpl implements Car{

	private int maxWeight;
	private int weight;
	private Node curNode;
	
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

}
