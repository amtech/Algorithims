package com.olbius.algorithms.core;

public class Logistic implements Algorithm {
	Node start;
	Node[] target;
	Graph graph;
	Algorithms algorithms;
	
	public Logistic(int start, int[] target) {
		this.start = graph.getNode(start);
		this.target = new Node[target.length];
		for(int i = 0; i < target.length;i++) {
			this.target[i] = graph.getNode(target[i]);
		}
	}
	
	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
		this.algorithms.setGraph(graph);
	}
	@Override
	public Object runAlgorithm() {
		Path[] paths = new Path[target.length*2];
		for(int i = 0; i < target.length; i++) {
			paths[2*i] = algorithms.shortestPath(start, target[i]);
			paths[2*i+1] = algorithms.shortestPath(target[i], start);
		}
		
		return null;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}
}
