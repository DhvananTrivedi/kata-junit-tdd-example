package com.dhvanan;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringCalculator {

	private static final Logger LOG = LoggerFactory.getLogger(StringCalculator.class);
	
	private static final String DEFAULT_DELIMITER = ",|\n";

	public static int add(String numbers) {
		
		String delimiter = null;
		
		if (numbers.startsWith("//")) {
			final String[] numbersSplitLineWise = numbers.split("\n");
			
			final String firstLine = numbersSplitLineWise[0];
			final String suppliedDelimiter = firstLine.replace("//", "");
			delimiter = suppliedDelimiter + "|" + DEFAULT_DELIMITER;
			
			final StringBuilder numbersForCalculation = new StringBuilder();
			for (int i = 1; i < numbersSplitLineWise.length; i++) {
				numbersForCalculation.append(numbersSplitLineWise[i]);
			}
			numbers = numbersForCalculation.toString();
		} else {
			delimiter = DEFAULT_DELIMITER;
		}
		
		final List<String> numbersList = Arrays.asList(numbers.split(delimiter));
		
		final int sum = numbersList.stream()
									.mapToInt(Integer::parseInt)
									.sum();
		
		LOG.info("addition of [{}] = {}", numbers, sum);
		return sum;
	}

}