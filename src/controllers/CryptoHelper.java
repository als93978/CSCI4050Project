package controllers;

import org.jasypt.util.text.BasicTextEncryptor;

public class CryptoHelper {
	
	private static String encryptionPassword = "xn4#lWd7!eg8H4Ps@0eyBA7jB";
	
	public static String encrypt(String plainText) throws Exception {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(encryptionPassword);
		
		return textEncryptor.encrypt(plainText);
	}
	
	public static String decrypt(String encryptedText) throws Exception {
		BasicTextEncryptor textDecryptor = new BasicTextEncryptor();
		textDecryptor.setPassword(encryptionPassword);
		
		return textDecryptor.decrypt(encryptedText);
	}
}
