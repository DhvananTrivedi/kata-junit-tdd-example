package com.dhvanan;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple StringCalculator.
 */
public class StringCalculatorTest {
	
    @Test
    public void add_ShouldWorkWithEmptyString() {
    	final StringCalculator calculator = new StringCalculator();
    	final int add = calculator.add("");
    	Assertions.assertTrue(add == 0);
    }
    
    @Test
    public void add_ShouldWorkWith2SimpleNumbers() {
    	final StringCalculator calculator = new StringCalculator();
    	final int add = calculator.add("1,2");
    	Assertions.assertTrue(add == 3);
    }
    
    
}
