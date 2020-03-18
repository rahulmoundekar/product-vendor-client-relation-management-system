package com.vcr.app.helpers;

import java.security.SecureRandom;
import java.util.Random;

public class UniqueCode {

	static final private String ALPHABET = "0123456789";
	final private static Random rng = new SecureRandom();

	private static char randomChar() {
		return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
	}

	public static String randomUUID(int length, int spacing, char spacerChar) {
		StringBuilder sb = new StringBuilder();
		int spacer = 0;
		while (length > 0) {
			if (spacer == spacing) {
				sb.append(spacerChar);
				spacer = 0;
			}
			length--;
			spacer++;
			sb.append(randomChar());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(randomUUID(4, 0, '0'));
	}

}
