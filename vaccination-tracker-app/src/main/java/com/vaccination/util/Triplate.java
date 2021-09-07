package com.vaccination.util;

public class Triplate<U, V, T> {

	public U first;
	public V second;
	public T third;

	public Triplate(U first, V second, T third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public int hashCode() {
		/*
		 * Computes hash code for an object by using hash codes of the underlying
		 * objects
		 */

		int result = first.hashCode();
		result = 31 * result + second.hashCode();
		result = 31 * result + third.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ", " + third + ")";
	}

}
