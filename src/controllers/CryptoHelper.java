package controllers;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class CryptoHelper {
	
	private static ConfigurablePasswordEncryptor passwordEncryptor;
	
	public static ConfigurablePasswordEncryptor getPasswordEncryptor() {
		if(passwordEncryptor == null) {
			passwordEncryptor = new ConfigurablePasswordEncryptor();
			passwordEncryptor.setAlgorithm("SHA-256");
			passwordEncryptor.setPlainDigest(true);
			passwordEncryptor.setStringOutputType("hexadecimal");
		}
		
		return passwordEncryptor;
	}
	
	public static String encryptPassword(String plainTextPassword) {
		ConfigurablePasswordEncryptor passwordEncryptor = getPasswordEncryptor();
		
		return passwordEncryptor.encryptPassword(plainTextPassword);
	}

}
