package edu.miracosta.cs113;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


/**
 * PalindromeTest : a test class for isPalindrome, a method intended to utilize stacks to evaluate if a given
 * string is a palindrome.
 *
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backwards as it does
 * forwards. Such sequences include "madam," "top spot," or "no lemon, no melon".
 */
public class PalindromeTest {

    /** True test cases which include spaces and symbols */
    private static final String[] SIMPLE_TRUE = { "", " ", "A", "7", "%", "  ", "BB", "33", "**" };
    /** False test cases which include spaces and symbols */
    private static final String[] SIMPLE_FALSE = { "AC", "71", "@+" };

    /** True test cases which include spaces */
    private static final String[] WHITE_SPACE_TRUE = { " x ", " t   t  ", " 5 5", " #      # " };
    /** False test cases which include spaces */
    private static final String[] WHITE_SPACE_FALSE = { "m   n  ", "   8  7 ", "  ^      &  "};

    /** Case-sensitive palindromes */
    private static final String[] CASE_SENSITIVE_TRUE = { "ABba", "roTOR", "rAceCaR" };

    /** Complex palindromes which include spaces, symbols, and varied capitalization */
    private static final String[] COMPLEX_TRUE = { "fOO race CAR oof", "AbBa ZaBba", "1 3 3 7  331",
            "N0 LEm0n, n0 Mel0n",
            "sT RJKLEeE R#@ $A$ @# REeEL K  JRT s" };

    /**
     * Utilizes stacks to determine if the given string is a palindrome. This method ignores whitespace and case
     * sensitivity, but does not ignore digits or symbols.
     *
     * @param s a string comprised of any character
     * @return returns true if a palindrome (ignoring whitespace and case sensitivity), false otherwise
     * @throws IllegalArgumentException if input is null
     */
    public boolean isPalindrome(String s){

        if( s == null ){
            throw new IllegalArgumentException();
        }

        // a blank String and a Single character is a palindrome
        if(s.equals("") || s.length() == 1){
            return true;
        }

        // DEBUG
//        System.out.println("s.length(): " + s.length());

        // Then, remove all whitespace and put whole String in lowercase
        s = s.replaceAll(" ","");
        s = s.toLowerCase();

        // DEBUG
//        System.out.println("s: " + s);

        // Now, analyze using stacks:

        // Initialize stacks to hold characters
        ArrayListStack stackA = new ArrayListStack();
        ArrayListStack stackB = new ArrayListStack();

        // Load s characters into this ArrayListStack
        for(int k = 0; k < s.length(); k++){
            stackA.push( s.charAt(k) );
        }

        // DEBUG
//        System.out.println("this ArrayListStack: " + stackA);

        // for length of each stack, used later
        int len = 0;

        // If s has an even length
        if(s.length() % 2 == 0 ){
            for(int i = 0; i < (s.length() / 2); i++){
                stackB.push( stackA.pop() );
                len++;
            }

            // DEBUG
//            System.out.println("other ArrayListStack: " + stackB);

        }
        // If s has an odd length
        else{
            for(int i = 0; i < ( (s.length() - 1 ) / 2 ) ; i++){
                stackB.push( stackA.pop() );
                len++;
            }

            //remove middle letter from this stack
            stackA.pop();

            // DEBUG
//            System.out.println("other ArrayListStack: " + stackB);
        }

        // Finally, check that the stacks contain the same characters
        //  note: stacks should have same length
        for(int j = 0; j < len; j++){
            // DEBUG
//            System.out.println("j: " + j + "this: " + stackA + "   other: " + stackB);
            if(!stackA.pop().equals( stackB.pop() )){
                return false;
            }
        }

        return true;

    } // End of method isPalindrome

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) { /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        for (int i = 0; i < SIMPLE_TRUE.length; i ++) {
            assertTrue(isPalindrome(SIMPLE_TRUE[i]),(i + " This test is a palindrome"));

        }
    }

    @Test
    public void testSimpleFalseCases() {
        for (int i = 0; i < SIMPLE_FALSE.length; i ++) {
            assertFalse(isPalindrome(SIMPLE_FALSE[i]), (i + " This test is NOT a palindrome"));
        }
    }

    @Test
    public void testWhitespaceTrueCases() {
        for (int i = 0; i < WHITE_SPACE_TRUE.length; i ++) {
            assertTrue(isPalindrome(WHITE_SPACE_TRUE[i]), (i + " This test is a palindrome"));
        }
    }

    @Test
    public void testWhitespaceFalseCases() {
        for (int i = 0; i < WHITE_SPACE_FALSE.length; i ++) {
            assertFalse(isPalindrome(WHITE_SPACE_FALSE[i]), (i + " This test is NOT a palindrome"));
        }
    }

    @Test
    public void testCaseSensitivityCases() {
        for (int i = 0; i < CASE_SENSITIVE_TRUE.length; i ++) {
            assertTrue(isPalindrome(CASE_SENSITIVE_TRUE[i]), (i + " This test is a palindrome"));
        }
    }

    @Test
    public void testComplexCases() {
        for (int i = 0; i < COMPLEX_TRUE.length; i ++) {
            assertTrue(isPalindrome(COMPLEX_TRUE[i]), (i + " This test is a palindrome"));
        }
    }

} // End of class PalindromeTest