package com.olbius.algorithms.core;

public interface Algorithms extends ObjectGraph{
	
	Path shortestPath(Node start, Node target);
	
	Path findPath(Node start, Node target);
}
