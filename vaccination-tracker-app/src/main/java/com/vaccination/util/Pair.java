package com.vaccination.util;

public class Pair<U, V> {
	
	public U first;
	public V second;
	
	public Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int hashCode() {
		/*
		 * Computes hash code for an object by using hash codes of the underlying
		 * objects
		 */

		int result = first.hashCode();
		result = 31 * result + second.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second +  ")";
	}

}
