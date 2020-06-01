package com.dhvanan;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringCalculator {

	private static final Logger LOG = LoggerFactory.getLogger(StringCalculator.class);

	public int add(String numbers) {
		final List<String> numbersList = Arrays.asList(numbers.split(","));
		
		final int sum = numbersList.stream()
									.mapToInt(Integer::parseInt)
									.sum();
		
		LOG.info("addition of [{}] = {}", numbers, sum);
		return sum;
	}

}