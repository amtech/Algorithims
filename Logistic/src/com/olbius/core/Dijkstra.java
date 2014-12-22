package com.olbius.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra implements Algorithm{
	
	Graph graph;
	Node start;
	Node target;
	
	
	@Override
	public Object runAlgorithm() {
		Map<Node, Integer> dist = new HashMap<Node, Integer>();
		Map<Node, Node> previous = new HashMap<Node, Node>();
		Set<Node> Q = new HashSet<Node>();
		Node v = start;
		Node u = null;
		
		dist.put(v, 0);
		
		for(Node node : graph.getNodes()) {
			if(!node.equals(start)) {
				dist.put(node, Integer.MAX_VALUE);
				Q.add(node);
			}
		}
		
		while(!Q.isEmpty()) {
			int min = Integer.MAX_VALUE;
			for(Node node : Q) {
				if(dist.get(node) < min) {
					min = dist.get(node);
					u = node;
				}
			}
			Q.remove(u);
			
			Node[] nodes = graph.getNext(u);
			int alt = 0;
			
			for(Node node : nodes) {
				alt = dist.get(u) + (int)graph.getWeight(u, node);
				if(dist.get(node) > alt) {
					dist.put(node, alt);
					previous.put(node, u);
				}
			}
		}
		
		return null;
	}

	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}
	
}
