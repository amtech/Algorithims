package com.olbius.algorithms.core;

public interface Graph {
	
	int getSize();
	
	Node[] getNext(Node node);
	
	Object getWeight(Node start, Node target);
	
	Object getWeight(Node start, Node target, int index);
	
	boolean isEdge(Node start, Node target);
	
	Node getNode(Object name);
	
	void addEdge(Node start, Node target, Object weight);
	
	void addEdge(Edge edge);
	
	Node[] getNodes();
	
	Edge[] getEdges();
	
	Edge getEdge(Node start, Node target);
}
