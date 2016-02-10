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
		add(new GLine(getWidth() / 2, y, getWidth() / 2, y + ROPE_LENGTH));
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
		updWrongGuesses(letter);
		wrongCnt++;
		switch (wrongCnt) {
		case 1: showHead(); break;
		case 2: showBody(); break;
		case 3: showLeftArm(); break;
		case 4: showRightArm(); break;
		case 5: showLeftLeg(); break;
		case 6: showRightLeg(); break;
		case 7: showLeftFoot(); break;
		case 8: showRightFoot(); break;
		}
	}
	
	private void showHead() {
		add(new GOval(getWidth() / 2 - HEAD_RADIUS , y + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2));
	}
	
	private void showBody() {
		int y1 = y + ROPE_LENGTH + HEAD_RADIUS * 2;
		add(new GLine(getWidth() / 2, y1, getWidth() / 2, y1 + BODY_LENGTH));
	}
	
	private void showLeftArm() {
		int x1 = getWidth() / 2 - UPPER_ARM_LENGTH;
		showArm(x1);
	}
	
	private void showRightArm() {
		int x1 = getWidth() / 2 + UPPER_ARM_LENGTH;
		showArm(x1);
	}
	
	private void showArm(int x1) {
		int y1 = y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		add(new GLine(x1, y1, getWidth() / 2, y1));
		add(new GLine(x1, y1, x1, y1 + LOWER_ARM_LENGTH));
	}
	
	private void showLeftLeg() {
		int x1 = getWidth() / 2 - HIP_WIDTH;
		showLeg(x1);
	}
	
	private void showRightLeg() {
		int x1 = getWidth() / 2 + HIP_WIDTH;
		showLeg(x1);
	}
	
	private void showLeg(int x1) {
		int y1 = y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		add(new GLine(x1, y1, getWidth() / 2, y1));
		add(new GLine(x1, y1, x1, y1 + LEG_LENGTH));
	}
	
	private void showLeftFoot() {
		int x1 = getWidth() / 2 - HIP_WIDTH;
		int x2 = x1 - FOOT_LENGTH;
		showFoot(x1, x2);
	}
	
	private void showRightFoot() {
		int x1 = getWidth() / 2 + HIP_WIDTH;
		int x2 = x1 + FOOT_LENGTH;
		showFoot(x1, x2);
	}
	
	private void showFoot(int x1, int x2) {
		int y1 = y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		add(new GLine(x1, y1, x2, y1));
	}
	
	
	
	private void updWrongGuesses(char letter) {
		wrongGuesses += letter;
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
