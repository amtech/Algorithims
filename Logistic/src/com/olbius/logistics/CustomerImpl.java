package com.olbius.logistics;

import com.olbius.algorithms.core.Node;

public class CustomerImpl implements Customer{

	private Node node;
	private Request request;
	
	public CustomerImpl(Node node) {
		this.node = node;
	}
	
	public CustomerImpl(Node node, Request request) {
		this.node = node;
		this.request = request;
	}
	
	@Override
	public Node getNode() {
		return this.node;
	}

	@Override
	public Request getRequest() {
		return this.request;
	}

	@Override
	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		String s = "";
		s += node.toString() + " : " + request.toString();
		return s;
	}
	
}
