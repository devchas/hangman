/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

/** Max number of allowed guesses by user */
	private static final int MAXGUESS = 8;
	
	public void run() {
		initWord();
		println("Welcome to Hangman!");
		while (true) {
			if (isGameOver()) break;
			println("The word now looks like this: " + wordStatus);
			processGuess();
		}
		if (isWinner) {
			println("You win");
		} else {
			println("You lose.");
		}
	}
    
/* Sets words from list and sets the current status of the word as all dashes */
    private void initWord() {
    	setWord();
    	setWordStatus();
    }
    
/* Sets word from a given list */
    private void setWord() {
    	RandomGenerator rgen = RandomGenerator.getInstance();
    	HangmanLexicon lex = new HangmanLexicon();
    	int r = rgen.nextInt(0, lex.getWordCount() - 1);
    	word = lex.getWord(r);
    }
    
/* Converts the words to a string of dashes with len = to word len */
    private void setWordStatus() {
    	for (int i = 0; i < word.length(); i++) {
    		wordStatus += "-";
    	}
    }
    
/* Gets guess from user and updates word status, guess, list, graphic */
    private void processGuess() {
    	char guess = validateGuess(getGuess());
    	if (!guessInWord(guess)) setWrongGuesses(guess);
    }
    
/* Gets guess from the user */
    private char getGuess() {
    	return (char) readInt("Your guess: ");
    }
    
/**
 * Checks to see if guess is valid and returns capital letter of guess    
 * @param guess Raw guess
 * @return Cleaned guess
 */
    private char validateGuess(char guess) {
    	if (!Character.isLetter(guess)) {
    		return validateGuess(getGuess());
    	} else {
    		return Character.toUpperCase(guess);
    	}
    }
    
/**
 * Returns true if the guess is on of the unguessed correct letters
 * @param guess User guess
 * @return True if guess is correct, else false
 */
    private boolean guessInWord(char guess) {
    	boolean inWord = false;
    	for (int i = 0; i < word.length(); i++) {
    		if (guess == word.charAt(i)) {
    			replaceLetter(i, guess);
    			inWord = true;
    		}
    	}
    	return inWord;
    }
    
/**
 * Replaces dashes in word for correct letter
 * @param i Index of letter to replaced
 * @param guess Letter to insert in word
 */
    private void replaceLetter(int i, char guess) {
    	String w1 = wordStatus.substring(0, i);
    	String w2 = wordStatus.substring(++i);
    	wordStatus = w1 + guess + w2;
    }
    
/**
 * Sets string of wrongly guessed letters
 * @param guess User guess
 */
    private void setWrongGuesses(char guess) {
    	wrongGuesses += guess;
    	guessCnt++;
    }
    
/* Returns true if game is over, else false */
    private boolean isGameOver() {
    	if (wordStatus.equals(word)) {
    		isWinner = true;
    		return true;
    	} else if (guessCnt >= MAXGUESS) {
    		isWinner = false;
    		return true;
    	} else {
    		return false;
    	}	
    }

    private boolean isWinner;
    private String word;
    private String wordStatus = "";
    private String wrongGuesses = "";
    private int guessCnt = 0;
}
