package com.olbius.algorithms.core;

public class AlgorithmsImpl implements Algorithms {

	private Graph graph;
	
	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public Path shortestPath(Node start, Node target) {
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.setGraph(graph);
		dijkstra.setStart(start);
		dijkstra.setTarget(target);
		return (Path) dijkstra.runAlgorithm();
	}

	@Override
	public Path findPath(Node start, Node target) {
		return null;
	}

}
