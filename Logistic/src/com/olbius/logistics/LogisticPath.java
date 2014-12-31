package com.olbius.logistics;

import com.olbius.algorithms.core.Node;
import com.olbius.algorithms.core.Path;

public interface LogisticPath {
	
	void setWeight(Object weight);
	
	Object getWeight();
	
	Path[] cut(Node node);
}
