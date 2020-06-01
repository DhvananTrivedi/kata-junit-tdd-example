package com.dhvanan;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple StringCalculator.
 */
public class StringCalculatorTest {
	
    @Test
    public void add_EmptyString_ShouldReturnZero() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(0, calculator.add(""));
    }
    
    @Test
    public void add_2SimpleNumbers_CalculatedResult() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(3, calculator.add("1,2"));
    }
    
    @Test
    public void add_RandomAmountOfNumbers_CalculatedResults() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(6, calculator.add("1,2,3"));
    	Assertions.assertEquals(25, calculator.add("1,2,3,4,5,10"));
    	Assertions.assertEquals(51, calculator.add("1,2,3,4,5,10,11,15"));
    }
    
    @Test
    public void add_NewLineBetweenNumbers_ConsiderNewLineAsAnotherDelimiter() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(6, calculator.add("1\n2,3"));
    }
    
    
    @Test
    public void add_CustomOptionalDelimiterSuppliedAtTheStartOfTheString_WorkAsDelimiterIfSupplied() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(3, calculator.add("//;\n1;2"));
    	Assertions.assertEquals(6, calculator.add("|\n2|4"));
    	Assertions.assertEquals(3, calculator.add("1,2"));
    }
    
    @Test
    public void add_NegativeNumbers_ShouldThrowAndException() {
    	
    	final Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                	final StringCalculator calculator = new StringCalculator();
                	calculator.add("-1,2");
                }
        );
     
    	Assertions.assertEquals("negatives not allowed - [-1] is supplied", exception.getMessage());
    }
    
    @Test
    public void add_NumberBiggerThan1000IsAdded_NumberBiggerThan1000ShouldBeIgnored() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(3, calculator.add("1,2,1003"));
    }
    
    @Test
    public void add_DelimitersOfAnyLength_ShouldCalculateBasedOnTheDelimiters() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(6, calculator.add("//[***]\n1***2***3"));
    	Assertions.assertEquals(10, calculator.add("//[****]\n2****3****5"));
    }
    
    @Test
    public void add_MultipleDelimitersAreSupplied_AllOfTheSuppliedDelimitersShouldBeConsidered() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }
    
    
    @Test
    public void add_MultipleDelimitersOfAnyLenghtAreSupplied_AllOfTheSuppliedDelimitersShouldBeConsidered() {
    	final StringCalculator calculator = new StringCalculator();
    	Assertions.assertEquals(6, calculator.add("//[**][%%]\n1**2%%3"));
    }
    
    
}
