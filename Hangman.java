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
		initGame();
		println("Welcome to Hangman!");
		while (true) {
			if (isGameOver()) break;
			processGuess();
		}
		if (isWinner) {
			println("You win");
		} else {
			println("You lose.");
		}
	}
    
/* Sets words from list and sets the current status of the word as all dashes */
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    	canvas.setVisible(true);
    	canvas.reset();
    }
    
    private void initGame() {
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
    private String getGuess() {
    	return readLine("Your guess: ");
    }
    
/**
 * Checks to see if guess is valid and returns capital letter of guess    
 * @param guess Raw guess
 * @return Cleaned guess
 */
    private char validateGuess(String guess) {
    	if (guess.length() == 1) {
    		char cGuess = guess.charAt(0);
	    	if (!Character.isLetter(cGuess)) {
	    		inValidGuess(guess);
	    		return validateGuess(getGuess());
	    	} else {
	    		return Character.toUpperCase(cGuess);
	    	}
    	} else {
    		inValidGuess(guess);
    		return validateGuess(getGuess());
    	}
    }
    
/* Returns error message to user if invalid entry */
    private void inValidGuess(String guess) {
    	println(guess + " is not a valid entry.");
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
    	updateStatus(inWord, guess);
    	return inWord;
    }
    
private void updateStatus(boolean isCorrect, char guess) {
	if (isCorrect) {
		println("That guess is correct.");
	} else {
		println("There are no " + guess + "'s in the word.");
	}
	println("The word now looks like this: " + wordStatus);
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
    private HangmanCanvas canvas;
}
