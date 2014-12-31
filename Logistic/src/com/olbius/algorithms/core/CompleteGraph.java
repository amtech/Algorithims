package com.olbius.algorithms.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class CompleteGraph implements Graph{

	private int size;
	private TreeSet<Node> nodes;
	private Map<Node, Map<Node, Edge>> edges;
	
	public CompleteGraph() {
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
		Node[] nodes = new Node[size-1];
		int i = 0;
		Iterator<Node> iterator = this.nodes.iterator();
		while (iterator.hasNext()) {
			nodes[i++] = iterator.next();
		}
		return nodes;
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
		return true;
	}

	@Override
	public Node getNode(Object name) {
		Node node = new NodeImpl((int) name);
		return nodes.contains(node)?nodes.floor(node):null;
	}

	@Override
	public void addEdge(Node start, Node target, Object weight) {
		addNode(start);
		addNode(target);
		edges.get(start).get(target).setWeight(weight);
	}

	@Override
	public void addEdge(Edge edge) {
		addNode(edge.getStart());
		addNode(edge.getTarget());
		edges.get(edge.getStart()).put(edge.getTarget(), edge);
	}

	@Override
	public Node[] getNodes() {
		return nodes.toArray(new Node[size]);
	}

	@Override
	public Edge[] getEdges() {
		return null;
	}

	private void addNode(Node node) {
		
		if(nodes.contains(node)) {
			return;
		}
		
		Iterator<Node> iterator = nodes.iterator();
		
		edges.put(node, new HashMap<Node, Edge>());
		
		while(iterator.hasNext()) {
			Node node2 = iterator.next();
			Edge edge = new EdgeImpl(node2, node);
			edges.get(node2).put(node, edge);
			edge = new EdgeImpl(node, node2);
			edges.get(node).put(node2, edge);
		}
		nodes.add(node);
		size++;
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
