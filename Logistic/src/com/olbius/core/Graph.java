package com.olbius.core;

public interface Graph {
	
	int getSize();
	
	Node[] getNext(Node node);
	
	Object[] getWeight(Node start, Node target);
	
	Object getWeight(Node start, Node target, int index);
	
	boolean isLink(Node start, Node target);
	
	Node getNode(Object name);
}
