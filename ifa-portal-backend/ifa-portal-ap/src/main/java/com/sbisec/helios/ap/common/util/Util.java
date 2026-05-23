package com.sbisec.helios.ap.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 暗号化、復号化サンプル
 */
public class Util {

	private static byte[] key = null;
	private static IvParameterSpec iv = null;

	static void init()
		throws Exception
	{
		Config cfg = Config.get("pcenter.yml");
		Util.key = convertCryptKey(cfg.getCrypt().getKey());
		Util.iv = new IvParameterSpec(cfg.getCrypt().getIv().getBytes());
	}

	/**
	 * 暗号化メソッドです。<br>
	 *
	 * @param ciphertexti	暗号化対象文字列
	 *
	 * @return 暗号化した値
	 * @throws Exception
	 */
	public static String encrypt(String ciphertext)
		throws Exception
	{
		// AES(Rijndael)を使用し復号化
		SecretKeySpec sks = new SecretKeySpec(Util.key, "AES");
		Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cip.init(Cipher.ENCRYPT_MODE, sks, Util.iv);
		byte[] cipher_text = cip.doFinal(ciphertext.getBytes());

		// Base64変換
		String str_text = Base64.getEncoder().encodeToString(cipher_text);

		return str_text;
	}

	/**
	 * 復号化メソッドです。<br>
	 * 
	 * @param ciphertext	復号化対象文字列
	 *
	 * @return 復号化した値
	 * @throws Exception IllegalArgumentException NullPointerException NoSuchAlgorithmException NoSuchPaddingException k
	 */
	public static String decrypt(String ciphertext)
		throws Exception
	{
		// Base64デコード
		byte[] base64Decode = Base64.getDecoder().decode(ciphertext);

		// AES(Rijndael)を使用し復号化
		SecretKeySpec sks = new SecretKeySpec(Util.key, "AES");
		Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cip.init(Cipher.DECRYPT_MODE, sks, Util.iv);
		return new String(cip.doFinal(base64Decode));
	}

    /**
     * 外部API getRealQuoteSnapshotの入力データ「inputstring」設定文字列の作成
     *
     * @param paramArayList 設定値配列のリスト<br>
     *     ※リスト内の要素数は必ず1以上であること、リスト要素である配列の要素数はすべて同一であること
     * @return リスト内の要素数,リスト要素の配列要素数,リスト要素の配列[0],リスト要素の配列[1],...
     */
    public static String convertInputstring(List<String[]> paramArayList) {
        
        StringBuffer rtnInputstring = null;
        if (paramArayList != null) {
            
            for (String [] paramAry : paramArayList) {
                
                if (rtnInputstring == null) {
                    rtnInputstring = new StringBuffer();
                    rtnInputstring.append(paramArayList.size());
                    rtnInputstring.append(",");
                    rtnInputstring.append(paramAry.length);
                }
                
                for (String param : paramAry) {
                    rtnInputstring.append(",");
                    rtnInputstring.append(param);
                }
            }
        }
        return (rtnInputstring == null) ? null : rtnInputstring.toString();
    }

	/**
	 * 暗号化キーのMD5ハッシュ化
	 * 
	 * @param keyX	暗号化キー
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] convertCryptKey(String keyX)
		throws NoSuchAlgorithmException
	{
		// 指定された暗号鍵をハッシュ化する
		MessageDigest md = MessageDigest.getInstance("MD5");

		// 暗号化キー[X]をパラメタにMD5ハッシュ値を取得
		md.update(keyX.getBytes());
		byte[] mdvalA = md.digest();

		// [A]をパラメタにMD5ハッシュ値を取得[B]
		md.update(mdvalA);
		byte[] mdvalB = md.digest();

		// [A]+[B]を変換後暗号化キー[Y]とする
		byte[] keyY = new byte[mdvalA.length + mdvalB.length];
		System.arraycopy(mdvalA, 0, keyY, 0, mdvalA.length);
		System.arraycopy(mdvalB, 0, keyY, mdvalA.length, mdvalB.length);

		// [Y]を暗号化キーとして使用する
		return keyY;
	}
}

