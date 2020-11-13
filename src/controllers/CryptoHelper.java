package controllers;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;

public class CryptoHelper {
	
	private static ConfigurablePasswordEncryptor passwordEncryptor;
	private static AES256TextEncryptor textEncryptor;
	private static String textEncryptionPassword = "xn4#lWd7!eg8H4Ps@0eyBA7jB";
	
	public static ConfigurablePasswordEncryptor getPasswordEncryptor() {
		if(passwordEncryptor == null) {
			passwordEncryptor = new ConfigurablePasswordEncryptor();
			passwordEncryptor.setAlgorithm("SHA-256");
			passwordEncryptor.setPlainDigest(true);
			passwordEncryptor.setStringOutputType("hexadecimal");
		}
		
		return passwordEncryptor;
	}
	
	public static AES256TextEncryptor getTextEncryptor() {
		if(textEncryptor == null) {
			textEncryptor = new AES256TextEncryptor();
			textEncryptor.setPassword(textEncryptionPassword);
		}
		
		return textEncryptor;
	}
	
	public static String encryptPassword(String plainTextPassword) {
		passwordEncryptor = getPasswordEncryptor();
		
		return passwordEncryptor.encryptPassword(plainTextPassword);
	}
	
	public static String encryptText(String plainText) {
		textEncryptor = getTextEncryptor();
		
		return textEncryptor.encrypt(plainText);
	}
	
	public static String decryptText(String encryptedText) {
		textEncryptor = getTextEncryptor();
		
		return textEncryptor.decrypt(encryptedText);
	}

}
