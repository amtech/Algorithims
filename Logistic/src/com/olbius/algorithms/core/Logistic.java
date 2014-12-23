package com.olbius.algorithms.core;

import java.util.HashMap;
import java.util.Map;

public class Logistic implements Algorithm {
	Node start;
	Node[] target;
	Graph graph;
	Algorithms algorithms;
	
	public Logistic(Graph graph, int start, int[] target) {
		algorithms = new AlgorithmsImpl();
		setGraph(graph);
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
		
		ConvertGraph convertGraph = new ConvertGraph();
		
		for(int i = 0; i < target.length; i++) {
			
			convertGraph.addEdge(algorithms.shortestPath(start, target[i]));
			convertGraph.addEdge(algorithms.shortestPath(target[i], start));
			
			for(int j = 0; j < target.length; j++) {
				if(i!=j) {
					convertGraph.addEdge(algorithms.shortestPath(target[j], target[i]));
					convertGraph.addEdge(algorithms.shortestPath(target[i], target[j]));
				}
			}
		}
		
		System.out.println(convertGraph.getGraph());
		
		return null;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}
	
	public class ConvertGraph {
		private Graph graph;
		private Map<Edge, Path> edges;
		private Map<Node, Node> nodes;
		
		public ConvertGraph() {
			edges = new HashMap<Edge, Path>();
			nodes = new HashMap<Node, Node>();
			graph = new CompleteGraph();
		}
		
		public void addEdge(Path path) {
			
			Node start = path.getStart();
			Node target = path.getTarget();
			
			int size = graph.getSize();
			
			if(nodes.get(start) == null) {
				Node node = new NodeImpl(size++);
				nodes.put(start, node);
			}
			if(nodes.get(target) == null) {
				Node node = new NodeImpl(size++);
				nodes.put(target, node);
			}
			
			Edge edge = new EdgeImpl(nodes.get(start), nodes.get(target), (int) path.calcRoute());
			
			edges.put(edge, path);
			
			graph.addEdge(edge);
		}
		
		public Graph getGraph() {
			return graph;
		}
	}
}
