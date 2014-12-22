package com.olbius.algorithms.core;

public interface Path extends ObjectGraph{
	
	Object getConnect();
	
	Object calcRoute();
	
	void addTarget(Node node);
	
	boolean contains(Node node);
	
	boolean contains(Path path);
	
	Node getStart();
	
	Node getTarget();
	
	Node getNode(int index);
	
	int getSize();
}
