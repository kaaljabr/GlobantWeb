package com.globant.challenge.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globant.challenge.exception.PasswordManagerException;

public class PasswordManager {
	
	private static Logger log = LoggerFactory.getLogger(PasswordManager.class);
	
	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	SecretKey key;
	private volatile static PasswordManager _instance;
	
	private PasswordManager() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException{
		 myEncryptionKey = "ThisIsSpartaThisIsSparta";
         myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
         arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
         ks = new DESedeKeySpec(arrayBytes);
         skf = SecretKeyFactory.getInstance(myEncryptionScheme);
         cipher = Cipher.getInstance(myEncryptionScheme);
         key = skf.generateSecret(ks);
	}
    
    public static PasswordManager getInstance() throws PasswordManagerException{
    	if(_instance ==null){
    		synchronized (PasswordManager.class) {
				try {
					_instance = new PasswordManager();
				} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException| InvalidKeySpecException e) {
					log.error("An error occured while trying to create an instance of PasswordManager: {}",e.getMessage());
					throw new PasswordManagerException(e.getMessage(), e);
				} 
			}
    	}
    	return _instance;
    }
  
    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
