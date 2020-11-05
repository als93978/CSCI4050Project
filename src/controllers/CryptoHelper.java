package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoHelper {
	
	private static String keyFile = "crypto.txt";
	private static String ivFile = "iv.txt";
	
	public static byte[] getRandomIV() {
		SecureRandom sr = new SecureRandom();
		byte[] iv = new byte[16]; // 16 bytes IV
		
		sr.nextBytes(iv);
		
		return iv;
	}
	
	public static SecretKey generateKeyAndIVAndSaveToFile() throws NoSuchAlgorithmException, IOException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		
		SecretKey key = keyGenerator.generateKey();
		
		File keyFileObj = new File(keyFile);
		File ivFileObj = new File(ivFile);
		
		if(!keyFileObj.isFile()) {
			System.out.println("Here!");
			FileOutputStream keyOut = new FileOutputStream(keyFile);
			byte[] keyEncoded = key.getEncoded();
			keyOut.write(keyEncoded);
		}
		
		if(!ivFileObj.isFile()) {
			System.out.println("Here!");
			byte[] iv = getRandomIV();
			FileOutputStream ivOut = new FileOutputStream(ivFile);
			ivOut.write(iv);
		}
		
		System.out.println("After here!");
		
		return key;
	}
	
	public static String encrypt(String plainText) throws Exception {
		generateKeyAndIVAndSaveToFile();
		
		byte[] iv = Files.readAllBytes(Paths.get(ivFile));
		byte[] keyBytes = Files.readAllBytes(Paths.get(keyFile));
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
		
		byte[] plainTextBytes = plainText.getBytes("UTF-8");
		
		return new String(cipher.doFinal(plainTextBytes), StandardCharsets.UTF_8);
	}
	
	public static String decrypt(String encryptedText) throws Exception {
		byte[] iv = Files.readAllBytes(Paths.get(ivFile));
		byte[] keyBytes = Files.readAllBytes(Paths.get(keyFile));
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, iv));
		
		byte[] encryptedTextBytes = encryptedText.getBytes("UTF-8");
		
		return new String(cipher.doFinal(encryptedTextBytes), StandardCharsets.UTF_8);
		
	}
}
