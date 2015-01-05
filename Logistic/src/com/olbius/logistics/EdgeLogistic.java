package com.olbius.logistics;

import com.olbius.algorithms.core.EdgeImpl;
import com.olbius.algorithms.core.Node;

public class EdgeLogistic extends EdgeImpl {

	public int cost;
	
	public EdgeLogistic(Node start, Node target, int weight, int cost) {
		super(start, target, weight);
		this.cost = cost;
	}

	public EdgeLogistic(Node start, Node target) {
		super(start, target);
	}

	public EdgeLogistic(int start, int target, int weight, int cost) {
		super(start, target, weight);
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + cost;
	}
	
}
