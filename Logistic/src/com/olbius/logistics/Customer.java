package com.olbius.logistics;

import com.olbius.algorithms.core.Node;

public interface Customer {
	
	Node getNode();
	
	Request getRequest();
	
	void setRequest(Request request);
}
