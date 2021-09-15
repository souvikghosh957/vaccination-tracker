package com.vaccination.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class VaccinationUtil {

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static boolean isInteger(String pincode) {
		boolean isInteger = false;
		try {
			Integer.parseInt(pincode);
			isInteger = true;
		} catch (NumberFormatException ex) {
			System.out.println("Not an integer number: " + pincode);
		}
		return isInteger;
	}

}
