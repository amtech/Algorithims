package com.olbius.algorithms.core;

import java.util.ArrayList;
import java.util.List;

public class PathImpl implements Path{

	private Graph graph;
	private List<Node> path;
	
	public PathImpl(Graph graph) {
		this.graph = graph;
		path = new ArrayList<Node>();
	}
	
	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public Object getConnect() {
		return path;
	}

	@Override
	public Object calcRoute() {
		int sum = 0;
		for(int i = 1; i < path.size(); i++) {
			sum += (int) graph.getWeight(path.get(i-1), path.get(i));
		}
		return sum;
	}

	@Override
	public void addTarget(Node node) {
		this.path.add(node);
	}

	@Override
	public boolean contains(Node node) {
		for(Node n : path) {
			if(n.compareTo(node) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Path path) {
		for(int i = 0; i < this.path.size(); i++) {
			if(this.path.size() < path.getSize() + i) {
				return false;
			}
			if(this.path.get(i).compareTo(path.getStart()) == 0) {
				for(int j = 1; j < path.getSize(); j++) {
					if(path.getNode(j).compareTo(this.path.get(i+j)) != 0) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Node getStart() {
		if(path.isEmpty()) {
			return null;
		}
		return path.get(0);
	}

	@Override
	public Node getTarget() {
		if(path.isEmpty()) {
			return null;
		}
		return  path.get(path.size()-1);
	}

	@Override
	public int getSize() {
		return path.size();
	}

	@Override
	public Node getNode(int index) {
		return path.get(index);
	}

	@Override
	public String toString() {
		return path.toString();
	}
}
