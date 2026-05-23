package com.sbisec.helios.ap.common.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import lombok.Data;

@Data
public class FrameworkSessionInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private final String frameworkSessionId;
	private final SecretKey secretKey;
	private final byte[] IvParameterBytes;
	private String encryptedAuthenticationToken;
	private String jwt;
	
	public FrameworkSessionInfo(String frameworkSessionId) throws Exception{
		this.frameworkSessionId = frameworkSessionId;
		this.secretKey = generateSecretKey();
		this.IvParameterBytes = generateIvParameterBytes();
		this.encryptedAuthenticationToken = encryptAuthenticationToken();
	}
	
	// トークンの検証。
	public boolean isValidEncryptAuthenticationToken(String targetToken) throws Exception{
		// トークンの復号。
		String originalToken =  getDecriptAuthenticationToken(this.encryptedAuthenticationToken);
		String requestedToken = getDecriptAuthenticationToken(targetToken);
		// トークンの比較結果を返却。
		return originalToken.equals(requestedToken);
	}
	
	// 共通鍵の生成。
	private SecretKey generateSecretKey() throws Exception {
		int keySizeAsBit = 256;
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(keySizeAsBit);
		return keyGenerator.generateKey();
	}
	
	// IVの生成。
	private byte[] generateIvParameterBytes() {
		byte[] iv = new byte[16];
		for (int i = 0; i < 16; i++) {
			iv[i] = (byte) (Math.random() * 256);
		}
		return iv;
	}
	
	// 符号化・暗号化済みトークンの生成。
	private String encryptAuthenticationToken() throws Exception{
		
		// 暗号化インスタンスの初期化。
		Cipher encryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
		encryptor.init(Cipher.ENCRYPT_MODE, this.secretKey, new IvParameterSpec(this.IvParameterBytes));
		
		// 認証トークン用UUIDの生成。
		byte[] authenticationTokenBytes = UUID.randomUUID().toString().getBytes();
		// 認証トークン用UUIDの暗号化。
		byte[] encryptedToken = encryptor.doFinal(authenticationTokenBytes);

		// Base64符号化。
		return new String(Base64.getEncoder().encode(encryptedToken));
	}

	// 符号化・暗号化済みトークンの復号。
	private String getDecriptAuthenticationToken(String encryptedAuthenticationToken) throws Exception{
		// 復号化インスタンスの初期化。		
		Cipher decryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
		decryptor.init(Cipher.DECRYPT_MODE, this.secretKey, new IvParameterSpec(this.IvParameterBytes));
		// 暗号化済みトークンの復号。
		byte[] decodedToken = Base64.getDecoder().decode(encryptedAuthenticationToken);
		byte[] decryptedToken = decryptor.doFinal(decodedToken);

		return new String(decryptedToken);
	}
}
