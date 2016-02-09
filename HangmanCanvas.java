/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		x = getWidth() / 2 - BEAM_LENGTH;
		y = (getHeight() - SCAFFOLD_HEIGHT) / 2;
		add(new GLine(x, y, x, y + SCAFFOLD_HEIGHT));
		add(new GLine(x + BEAM_LENGTH, y, x, y));
		statusLabel = new GLabel("", x - 20, y + SCAFFOLD_HEIGHT + WORD_BUFFER);
		add(statusLabel);
		wrongLabel = new GLabel("", x - 20, y + SCAFFOLD_HEIGHT + WRONG_BUFFER);
		add(wrongLabel);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		statusLabel.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		updWrongGuesses();
		wrongCnt++;
		/*switch (wrongCnt) {
		case 1: showHead(); break;
		case 2: showBody(); break;
		case 3: showLeftArm(); break;
		case 4: showRightArm(); break;
		case 5: showLeftLeg(); break;
		case 6: showRightLeg(); break;
		case 7: showLeftFoot(); break;
		case 8: showRightFoot(); break;*/
		}
	}
	
	public void updWrongGuesses() {
		wrongGuesses += guess;
		wrongLabel.setLabel(wrongGuesses);
	}

	private int x, y;
	private int wrongCnt = 0;
	private String wrongGuesses = "";
	private GLabel statusLabel;
	private GLabel wrongLabel;
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int WORD_BUFFER = 20;
	private static final int WRONG_BUFFER = 40;
}
