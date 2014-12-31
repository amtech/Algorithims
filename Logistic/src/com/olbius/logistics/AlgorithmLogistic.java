package com.olbius.logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.olbius.algorithms.core.Algorithm;
import com.olbius.algorithms.core.Dijkstra;
import com.olbius.algorithms.core.Graph;
import com.olbius.algorithms.core.Node;
import com.olbius.algorithms.core.Path;

public class AlgorithmLogistic implements Algorithm {

	private Node facility;
	private Graph graph;
	private int m;

	public void algorithm(Solution solution, Customer customer) {

		if (solution.contains(customer)) {
			solution.insert(customer);
		}

		if (solution.getSize() == 0) {

			List<Path> paths = new ArrayList<Path>();

			// TODO: find path

			Dijkstra dijkstra = new Dijkstra();

			dijkstra.setGraph(graph);

			((GraphLogistic) graph).setM((int) customer.getRequest().getWeight());

			dijkstra.setStart(facility);

			dijkstra.setTarget(customer.getNode());

			Path path = new LogisticPathImpl((Path) dijkstra.runAlgorithm());

			((LogisticPath) path).setWeight((int) customer.getRequest().getWeight());

			paths.add(path);

			((GraphLogistic) graph).setM(0);

			dijkstra.setStart(customer.getNode());

			dijkstra.setTarget(facility);

			path = new LogisticPathImpl((Path) dijkstra.runAlgorithm());

			((LogisticPath) path).setWeight(0);

			paths.add(path);

			solution.add(customer, paths);

		} else {

			int w = (int) solution.getWeight();

			List<Path> paths;

			Dijkstra dijkstra = new Dijkstra();

			dijkstra.setGraph(graph);

			List<Path> list;

			paths = increase(dijkstra, facility, customer.getNode(), solution.getFirst().getNode(), w, (int) customer
					.getRequest().getWeight());

			int tmp = (int) solution.increaseValue(paths, customer.getRequest().getWeight(), 0);

			list = paths;

			int min = tmp;

			int index = 0;
			
			int i;
			
			for (i = 0; i < solution.getSize()-1; i++) {
				paths = increase(dijkstra, solution.getCustomers().get(i).getNode(), customer.getNode(), solution
						.getCustomers().get(i + 1).getNode(), (int)solution.getWeight(i), (int) customer.getRequest()
						.getWeight());
				tmp = (int) solution.increaseValue(paths, customer.getRequest().getWeight(), i+1);
				if(min > tmp) {
					min = tmp;
					list = paths;
					index = i+1;
				}
			}
			
			paths = increase(dijkstra, solution.getLast().getNode(), customer.getNode(), facility, (int) customer
					.getRequest().getWeight(), 0);
			tmp = (int) solution.increaseValue(paths, customer.getRequest().getWeight(), i+1);
			if(min > tmp) {
				min = tmp;
				list = paths;
				index = i+1;
			}
			
			solution.add(customer, list, index);
			
		}

	}

	public List<Path> increase(Dijkstra dijkstra, Node s, Node b, Node t, int w1, int w2) {
		List<Path> paths = new ArrayList<Path>();

		((GraphLogistic) dijkstra.getGraph()).setM(w1 + w2);

		dijkstra.setStart(s);

		dijkstra.setTarget(b);

		Path path = new LogisticPathImpl((Path) dijkstra.runAlgorithm());

		((LogisticPath) path).setWeight(w1 + w2);

		paths.add(path);

		((GraphLogistic) dijkstra.getGraph()).setM(w1);

		dijkstra.setStart(b);

		dijkstra.setTarget(t);

		path = new LogisticPathImpl((Path) dijkstra.runAlgorithm());

		((LogisticPath) path).setWeight(w1);

		paths.add(path);

		return paths;
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
	public Object runAlgorithm() {
		// TODO Auto-generated method stub
		return null;
	}

}
