package com.olbius.logistics;

import com.olbius.algorithms.core.Node;

public interface Car {
	
	Object getWeight();
	
	Object getMaxWeight();
	
	Node getCurNode();
	
	void setCurNode(Node node);
	
	void setWeight(Object weight);
	
	boolean isAdd(Object weight);
}
