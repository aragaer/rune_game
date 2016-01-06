package com.aragaer.rune;


public class LineDescription {
    private final int[] numbers;

    public LineDescription(int... numbers) {
	this.numbers = numbers.clone();
    }

    public int[] getHoleNumbers() {
	return numbers.clone();
    }
}
