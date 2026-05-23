package com.sbisec.helios.gw.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptPassword {

	private static final String ALGORITHM = "SHA-256";
	private static final String[] UPDATES = {
			"vsQTaUVCu2D5oiGxbL8tRLOABHkkGyADY2mlhDVkl1U=",
			"SBI SECURITIES",
			"goKpRF61ApDDJN9m0OOwHtU9G56psEqJjPUdiH3kZto="
	};

	public static String encrypt(String value) throws NoSuchAlgorithmException {

		if (value == null) {
			throw new IllegalArgumentException("Value is required:[" + value + "]");
		}

		MessageDigest md = MessageDigest.getInstance(ALGORITHM);

		for (int i = 0; i < UPDATES.length; i++) {
			md.update(UPDATES[i].getBytes());
		}

		return Base64.getEncoder().encodeToString(md.digest(value.getBytes()));
	}

	public static void main(String[] args) {

		try {

			if (args.length == 0) return;

			System.out.println(encrypt(args[0]));

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
	}
}
