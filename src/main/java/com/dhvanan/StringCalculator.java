package com.dhvanan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringCalculator {

	private static final Logger LOG = LoggerFactory.getLogger(StringCalculator.class);
	
	private static final String DEFAULT_DELIMITER = ",|\n";

	public static int add(String numbers) {
		
		if (StringUtils.isBlank(numbers)) {
			return 0;
		}
		
		String delimiter = null;
		
		if (numbers.startsWith("//")) {
			final String[] numbersSplitLineWise = numbers.split("\n");
			
			final String firstLine = numbersSplitLineWise[0].replace("//", "");
			
			final String[] delims = firstLine.split("\\]\\[");
			
			final StringBuilder suppliedDelimiters = new StringBuilder();
			for (String delim : delims) {
				String cleanDelim = delim.replace("[", "").replace("]", "");
				for(char d : cleanDelim.toCharArray()) {
					suppliedDelimiters.append("\\").append(d);
				}
				suppliedDelimiters.append("|");
			}
			
			delimiter = suppliedDelimiters + DEFAULT_DELIMITER;
			
			// selects the entire supplied string except the first line  
			final StringBuilder numbersForCalculation = new StringBuilder();
			for (int i = 1; i < numbersSplitLineWise.length; i++) {
				numbersForCalculation.append(numbersSplitLineWise[i]);
			}
			numbers = numbersForCalculation.toString();
		} else {
			delimiter = DEFAULT_DELIMITER;
		}
		
		final List<String> numbersList = Arrays.asList(numbers.split(delimiter));
		
		final List<Integer> negativeNumbers = new ArrayList<>();
		final int sum = numbersList.stream()
									.mapToInt(Integer::parseInt)
									.map(i -> {
										if (i < 0) {
											negativeNumbers.add(i);
										}
										return i;
									})
									.filter(i -> i < 1000)
									.sum();
		
		if (!negativeNumbers.isEmpty()) {
			throw new IllegalArgumentException("negatives not allowed - " + negativeNumbers + " is supplied");
		}
		LOG.info("addition of [{}] = {}", numbers, sum);
		return sum;
	}

}