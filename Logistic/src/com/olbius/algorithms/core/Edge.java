package com.olbius.algorithms.core;

public interface Edge {
	
	Node getStart();
	
	Node getTarget();
	
	Object getWeight();
	
	void setWeight(Object weight);
}
