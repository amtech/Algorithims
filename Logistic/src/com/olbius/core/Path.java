package com.olbius.core;

public interface Path extends ObjectGraph{
	
	Object[] getConnect();
	
	Object calcRoute();
	
	void addTarget(Node node);
	
	boolean contains(Node node);
	
	boolean contains(Path path);
}
