package com.olbius.logistics;

import java.util.List;

import com.olbius.algorithms.core.Graph;
import com.olbius.algorithms.core.Node;
import com.olbius.algorithms.core.Path;
import com.olbius.algorithms.core.PathImpl;

public class LogisticPathImpl extends PathImpl implements LogisticPath{

	private int weight;
	
	@SuppressWarnings("unchecked")
	public LogisticPathImpl(Path path) {
		super(path.getGraph());
		((List<Node>)getConnect()).addAll((List<Node>)path.getConnect());
	}
	
	public LogisticPathImpl(Graph graph) {
		super(graph);
	}

	@Override
	public void setWeight(Object weight) {
		this.weight = (int) weight;
	}

	@Override
	public Path[] cut(Node node) {
		
		Path[] paths = new Path[2];
		
		paths[0] = new LogisticPathImpl(super.getGraph());
		
		paths[1] = new LogisticPathImpl(super.getGraph());
		
		Path p = paths[0];
		
		List<?> nodes = (List<?>) super.getConnect();
		
		for(int i = 0; i < nodes.size(); i++) {
			Node n = (Node) nodes.get(i);
			p.addTarget(n);
			if(n.equals(node)) {
				p = paths[1];
				p.addTarget(n);
			}
		}
		
		return paths;
	}

	@Override
	public Object getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
