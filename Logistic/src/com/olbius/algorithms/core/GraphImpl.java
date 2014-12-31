package com.olbius.algorithms.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class GraphImpl implements Graph{

	private int size;
	private TreeSet<Node> nodes;
	private Map<Node, Map<Node, Edge>> edges;
	
	public GraphImpl() {
		nodes = new TreeSet<Node>();
		edges = new HashMap<Node, Map<Node,Edge>>();
		size = 0;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public Node[] getNext(Node node) {
		return edges.get(node).keySet().toArray(new Node[edges.get(node).keySet().size()]);
	}

	@Override
	public Object getWeight(Node start, Node target) {
		return edges.get(start).get(target).getWeight();
	}

	@Override
	public Object getWeight(Node start, Node target, int index) {
		return null;
	}

	@Override
	public boolean isEdge(Node start, Node target) {
		return (edges.get(start).get(target) != null);
	}

	@Override
	public Node getNode(Object name) {
		Node node = new NodeImpl((int) name);
		return nodes.contains(node)?nodes.floor(node):null;
	}

	@Override
	public void addEdge(Node start, Node target, Object weight) {
		
		addNode(start, target);
		
		if(edges.get(start)!= null) {
			if(edges.get(start).get(target)!= null) {
				edges.get(start).get(target).setWeight(weight);
			} else {
				edges.get(start).put(target, new EdgeImpl(start, target, (int) weight));
			}
		} else {
			Map<Node, Edge> map = new HashMap<Node, Edge>();
			map.put(target, new EdgeImpl(start, target, (int) weight));
			edges.put(start, map);
		}
	}

	private void addEdge(Node start, Node target, Edge edge) {
		
		addNode(start, target);
		
		if(edges.get(start)!= null) {
			edges.get(start).put(target, edge);
		} else {
			Map<Node, Edge> map = new HashMap<Node, Edge>();
			map.put(target, edge);
			edges.put(start, map);
		}
	}
	
	@Override
	public void addEdge(Edge edge) {
		addEdge(edge.getStart(), edge.getTarget(), edge);
	}

	@Override
	public Node[] getNodes() {
		return nodes.toArray(new Node[size]);
	}

	@Override
	public Edge[] getEdges() {
		return null;
	}
	
	private void addNode(Node start, Node target) {
		if(!nodes.contains(start)) {
			nodes.add(start);
			size++;
		}
		if(!nodes.contains(target)) {
			nodes.add(target);
			size++;
		}
	}
	
	@Override
	public String toString() {
		String string = "";
		string += nodes.toString();
		string += "\n" + edges.toString();
		return string;
	}

	@Override
	public Edge getEdge(Node start, Node target) {
		return isEdge(start, target) ? edges.get(start).get(target) : null;
	}
}
