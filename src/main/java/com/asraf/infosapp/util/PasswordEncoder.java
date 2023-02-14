package com.asraf.infosapp.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncoder {
	private static final int ITERATIONS = 10;
	private static final int KEY_LENGTH = 256;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final SecureRandom RANDOM = new SecureRandom();

	public static String encode(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(hash);
	}

	public static byte[] generateSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	//to verify password
	public static boolean verifyPassword(char[] password, byte[] salt, String encodedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encodedEnteredPassword = encode(password, salt);
		return encodedEnteredPassword.equals(encodedPassword);
	}

	
}
