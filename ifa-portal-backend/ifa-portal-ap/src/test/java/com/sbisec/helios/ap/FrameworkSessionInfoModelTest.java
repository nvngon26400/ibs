package com.sbisec.helios.ap;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import org.junit.jupiter.api.Test;

class FrameworkSessionInfoModelTest {


    // 各インスタンスで固有のパラメタが生成されること。
    @Test
    void generateModelTest() throws Exception {
		FrameworkSessionInfo model1 = new FrameworkSessionInfo(UUID.randomUUID().toString());
		FrameworkSessionInfo model2 = new FrameworkSessionInfo(UUID.randomUUID().toString());
		assertTrue(model1.getSecretKey() != model2.getSecretKey());
		assertTrue(model1.getIvParameterBytes() != model2.getIvParameterBytes());
        assertNotEquals(model1.getEncryptedAuthenticationToken(), model2.getEncryptedAuthenticationToken());
	}

    // 引数のトークンと保持しているトークンが合致する場合、isValidEncryptAuthenticationTokenメソッドがtrueを返却すること。
    @Test
    void validTokenTest() throws Exception {
		FrameworkSessionInfo model = new FrameworkSessionInfo(UUID.randomUUID().toString());
		assertTrue(model.isValidEncryptAuthenticationToken(model.getEncryptedAuthenticationToken()));
	}

    // 引数のトークンと保持しているトークンが合致しない場合、isValidEncryptAuthenticationTokenメソッドがfalseを返却すること。 
    @Test
    void invalidTokenTest() throws Exception {
		
		FrameworkSessionInfo model = new FrameworkSessionInfo(UUID.randomUUID().toString());

		Cipher encryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
		encryptor.init(Cipher.ENCRYPT_MODE, model.getSecretKey(), new IvParameterSpec(model.getIvParameterBytes()));
		byte[] authenticationTokenBytes = UUID.randomUUID().toString().getBytes();
		byte[] encryptedToken = encryptor.doFinal(authenticationTokenBytes);
		String dummyTokenString = new String(Base64.getEncoder().encode(encryptedToken));		
		
		assertFalse(model.isValidEncryptAuthenticationToken(dummyTokenString));
	}
}
