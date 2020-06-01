package com.dhvanan;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple StringCalculator.
 */
public class StringCalculatorTest {
	
    @Test
    public void add_EmptyString_ShouldReturnZero() {
    	Assertions.assertEquals(0, StringCalculator.add(""));
    }
    
    @Test
    public void add_2SimpleNumbers_CalculatedResult() {
    	Assertions.assertEquals(3, StringCalculator.add("1,2"));
    }
    
    @Test
    public void add_RandomAmountOfNumbers_CalculatedResults() {
    	Assertions.assertEquals(6, StringCalculator.add("1,2,3"));
    	Assertions.assertEquals(25, StringCalculator.add("1,2,3,4,5,10"));
    	Assertions.assertEquals(51, StringCalculator.add("1,2,3,4,5,10,11,15"));
    }
    
    @Test
    public void add_NewLineBetweenNumbers_ConsiderNewLineAsAnotherDelimiter() {
    	Assertions.assertEquals(6, StringCalculator.add("1\n2,3"));
    }
    
    
    @Test
    public void add_CustomOptionalDelimiterSuppliedAtTheStartOfTheString_WorkAsDelimiterIfSupplied() {
    	Assertions.assertEquals(3, StringCalculator.add("//;\n1;2"));
    	Assertions.assertEquals(3, StringCalculator.add("1,2"));
    }
    
    @Test
    public void add_NegativeNumbers_ShouldThrowAndException() {
    	
    	final Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                	StringCalculator.add("-1,2");
                }
        );
     
    	Assertions.assertEquals("negatives not allowed - [-1] is supplied", exception.getMessage());
    }
    
    @Test
    public void add_MultipleNegativeNumbers_ShouldThrowAndException() {
    	
    	final Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                	StringCalculator.add("-1,2,-3");
                }
        );
     
    	Assertions.assertEquals("negatives not allowed - [-1, -3] is supplied", exception.getMessage());
    }
    
    @Test
    public void add_NumberBiggerThan1000IsAdded_NumberBiggerThan1000ShouldBeIgnored() {
    	Assertions.assertEquals(3, StringCalculator.add("1,2,1003"));
    }
    
    @Test
    public void add_DelimitersOfAnyLength_ShouldCalculateBasedOnTheDelimiters() {
    	Assertions.assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    	Assertions.assertEquals(10, StringCalculator.add("//[****]\n2****3****5"));
    }
    
    @Test
    public void add_MultipleDelimitersAreSupplied_AllOfTheSuppliedDelimitersShouldBeConsidered() {
    	Assertions.assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }
    
    
    @Test
    public void add_MultipleDelimitersOfAnyLenghtAreSupplied_AllOfTheSuppliedDelimitersShouldBeConsidered() {
    	Assertions.assertEquals(6, StringCalculator.add("//[**][%%]\n1**2%%3"));
    }
    
    
}
