package com.olbius.algorithms.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra implements Algorithm{
	
	private Graph graph;
	private Node start;
	private Node target;
	
	
	public void setStart(Node start) {
		this.start = start;
	}
	
	public void setTarget(Node target) {
		this.target = target;
	}
	
	@Override
	public Object runAlgorithm() {
		Map<Node, Integer> dist = new HashMap<Node, Integer>();
		Map<Node, Node> previous = new HashMap<Node, Node>();
		Set<Node> Q = new HashSet<Node>();
		Node v = start;
		Node u = null;
		
		dist.put(v, 0);
		
		Q.add(v);
		
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
			
			if(u.equals(target)) break;
			
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
		
		List<Node> list = new ArrayList<Node>();
		
		u = target;
		
		while(!u.equals(start)) {
			list.add(u);
			u = previous.get(u);
		}
		
		list.add(start);
		
		Path path = new PathImpl(graph);
		
		for(int i = list.size()-1; i >= 0; i--) {
			path.addTarget(list.get(i));
		}
		
		return path;
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
