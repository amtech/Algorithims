package com.olbius.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.olbius.algorithms.core.Algorithm;
import com.olbius.algorithms.core.Dijkstra;
import com.olbius.algorithms.core.Graph;
import com.olbius.algorithms.core.Node;
import com.olbius.algorithms.core.Path;

public class AlgorithmLogisticTest implements Algorithm {

	private Node facility;
	private Graph graph;
	private Dijkstra dijkstra;
	private int maxW;
	
	private Stack<Customer> stack;
	
	public AlgorithmLogisticTest() {
		stack = new Stack<Customer>();
		dijkstra = new Dijkstra();
	}
	
	public void setStack(Stack<Customer> stack) {
		this.stack = stack;
	}
	
	public void pushCustomer(Customer customer) {
		this.stack.push(customer);
	}
	
	/*public Solution algorithm() {
		Solution solution = new SolutionImpl(new CarImpl());
		
		if(!stack.isEmpty()) {
			algorithm(solution, stack.pop());
		}
		
		return solution;
	}*/
	
	public void algorithm(List<Solution> solutions, Customer customer, int maxWeight) {

		Solution s = null;
		
		List<Path> paths;

		List<Path> list = null;
		
		int w;
		
		int min = Integer.MAX_VALUE;

		int index = 0;
		
		if(solutions.isEmpty()) {
			
			s = new SolutionImpl(new CarImpl(maxWeight));
			
			paths = paths(dijkstra, facility, customer.getNode(), (int) customer.getRequest().getWeight());
			
			s.add(customer, paths);
			
			s.calcRoute();
			
			solutions.add(s);
			
		} else  {
			for(Solution solution : solutions) {
				if(!solution.getCar().isAdd(customer.getRequest().getWeight())) {
					continue;
				}
				if (solution.contains(customer)) {
					solution.insert(customer);
					s = solution;
					break;
				} else {
					w = (int) solution.getWeight();

					paths = increase(dijkstra, facility, customer.getNode(), solution.getFirst().getNode(), w, (int) customer
							.getRequest().getWeight());

					int tmp = (int) solution.increaseValue(paths, customer.getRequest().getWeight(), 0);

					list = paths;

					if(min > tmp) {
						min = tmp;
						list = paths;
						index = 0;
						s = solution;
					}
					
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
							s = solution;
						}
					}
					
					paths = increase(dijkstra, solution.getLast().getNode(), customer.getNode(), facility, (int) customer
							.getRequest().getWeight(), 0);
					tmp = (int) solution.increaseValue(paths, customer.getRequest().getWeight(), i+1);
					
					if(min >= tmp) {
						min = tmp;
						list = paths;
						index = i+1;
						s = solution;
					}
				}
			}
			
			paths = paths(dijkstra, facility, customer.getNode(), (int) customer.getRequest().getWeight());
			
			Solution tmp = new SolutionImpl(new CarImpl(maxWeight));
			
			tmp.add(customer, paths);
			
			tmp.calcRoute();
			
			if((int)tmp.getValue() < min) {
				solutions.add(tmp);
			} else {
				s.add(customer, list, index);
				s.calcRoute();
			}
		}
		
		
		if(!stack.isEmpty()) {
			algorithm(solutions, stack.pop(), maxWeight);
		} else {
			return;
		}
		
	}

	public List<Path> paths(Dijkstra dijkstra, Node s, Node t, int w) {
		
		List<Path> paths = new ArrayList<Path>();
		
		((GraphLogistic) dijkstra.getGraph()).setM(w);
		
		dijkstra.setStart(s);

		dijkstra.setTarget(t);

		Path path = new LogisticPathImpl((Path) dijkstra.runAlgorithm());

		((LogisticPath) path).setWeight(w);

		paths.add(path);
		
		((GraphLogistic) graph).setM(0);

		dijkstra.setStart(t);

		dijkstra.setTarget(s);

		path = new LogisticPathImpl((Path) dijkstra.runAlgorithm());

		((LogisticPath) path).setWeight(0);

		paths.add(path);
		
		return paths;
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
		dijkstra.setGraph(this.graph);
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public Object runAlgorithm() {
		List<Solution> solutions = new ArrayList<Solution>();
		
		if(!stack.isEmpty()) {
			algorithm(solutions, stack.pop(), maxW);
		}
		
		return solutions;
	}

	public void setFacility(Node facility) {
		this.facility = facility;
	}
	
	public void setMaxWeight(int maxWeight) {
		this.maxW = maxWeight;
	}
}
