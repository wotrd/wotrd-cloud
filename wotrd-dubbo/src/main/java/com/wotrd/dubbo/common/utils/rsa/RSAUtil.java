package com.wotrd.dubbo.common.utils.rsa;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

public class RSAUtil {

	/**
	 * 指定加密算法为DESede
	 */
	private static String ALGORITHM = "RSA/ECB/PKCS1Padding";// MD5withRSA///RSA/ECB/PKCS1Padding
	/**
	 * 指定key的大小(64的整数倍,最小512位)
	 */
	private static int KEYSIZE = 1024;
	/* RSA最大加密明文大小 */
	private static final int MAX_ENCRYPT_BLOCK = 117;
	/* RSA最大解密密文大小 */
	private static final int MAX_DECRYPT_BLOCK = 256;
	/* 公钥模量 */
	public static String publicModulus = null;
	/* 公钥指数 */
	public static String publicExponent = null;
	/* 私钥模量 */
	public static String privateModulus = null;
	/* 私钥指数 */
	public static String privateExponent = null;
	private static KeyFactory keyFactory = null;

	static {
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public RSAUtil() {
		try {
			generateKeyPairString(KEYSIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RSAUtil(int keySize) {
		try {
			generateKeyPairString(keySize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成密钥对字符串
	 */
	private void generateKeyPairString(int keySize) throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(keySize, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		/** 用字符串将生成的密钥写入文件 */

		String algorithm = publicKey.getAlgorithm(); // 获取算法
		KeyFactory keyFact = KeyFactory.getInstance(algorithm);
		BigInteger prime ;
		BigInteger exponent;

		RSAPublicKeySpec keySpec = keyFact.getKeySpec(publicKey, RSAPublicKeySpec.class);
		prime = keySpec.getModulus();
		exponent = keySpec.getPublicExponent();
		RSAUtil.publicModulus = HexUtil.bytes2Hex(prime.toByteArray());
		RSAUtil.publicExponent = HexUtil.bytes2Hex(exponent.toByteArray());

		RSAPrivateCrtKeySpec privateKeySpec = keyFact.getKeySpec(privateKey,
				RSAPrivateCrtKeySpec.class);
		BigInteger privateModulus = privateKeySpec.getModulus();
		BigInteger privateExponent = privateKeySpec.getPrivateExponent();
		RSAUtil.privateModulus = HexUtil.bytes2Hex(privateModulus.toByteArray());
		RSAUtil.privateExponent = HexUtil.bytes2Hex(privateExponent.toByteArray());
	}

	/**
	 * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的公钥对象。
	 *
	 * @param hexModulus
	 *            系数。
	 * @param hexPublicExponent
	 *            专用指数。
	 * @return RSA专用公钥对象。
	 */
	public static RSAPublicKey getRSAPublicKey(String hexModulus, String hexPublicExponent) {
		if (isBlank(hexModulus) || isBlank(hexPublicExponent)) {
			System.out.println("hexModulus and hexPublicExponent cannot be empty. return null(RSAPublicKey).");
			return null;
		}
		byte[] modulus = null;
		byte[] publicExponent = null;
		try {
			modulus = HexUtil.hex2Bytes(hexModulus);
			publicExponent = HexUtil.hex2Bytes(hexPublicExponent);
		} catch (Exception ex) {
			System.out.println("hexModulus or hexPublicExponent value is invalid. return null(RSAPublicKey).");
			ex.printStackTrace();
		}
		if (modulus != null && publicExponent != null) {
			return generateRSAPublicKey(modulus, publicExponent);
		}
		return null;
	}

	/**
	 * 根据给定的系数和专用指数构造一个RSA专用的公钥对象。
	 *
	 * @param modulus
	 *            系数。
	 * @param publicExponent
	 *            专用指数。
	 * @return RSA专用公钥对象。
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
		} catch (InvalidKeySpecException ex) {
			System.out.println("RSAPublicKeySpec is unavailable.");
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			System.out.println("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.");
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的私钥对象。
	 *
	 * @param hexModulus
	 *            系数。
	 * @param hexPrivateExponent
	 *            专用指数。
	 * @return RSA专用私钥对象。
	 */
	public static RSAPrivateKey getRSAPrivateKey(String hexModulus, String hexPrivateExponent) {
		if (isBlank(hexModulus) || isBlank(hexPrivateExponent)) {
			System.out.println(
					"hexModulus and hexPrivateExponent cannot be empty. RSAPrivateKey value is null to return.");
			return null;
		}
		byte[] modulus = null;
		byte[] privateExponent = null;
		try {
			modulus = HexUtil.hex2Bytes(hexModulus);
			privateExponent = HexUtil.hex2Bytes(hexPrivateExponent);
		} catch (Exception ex) {
			System.out.println("hexModulus or hexPrivateExponent value is invalid. return null(RSAPrivateKey).");
			ex.printStackTrace();
		}
		if (modulus != null && privateExponent != null) {
			return generateRSAPrivateKey(modulus, privateExponent);
		}
		return null;
	}

	/**
	 * 根据给定的系数和专用指数构造一个RSA专用的私钥对象。
	 *
	 * @param modulus
	 *            系数。
	 * @param privateExponent
	 *            专用指数。
	 * @return RSA专用私钥对象。
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
		RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus),
				new BigInteger(privateExponent));
		try {
			return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
		} catch (InvalidKeySpecException ex) {
			System.out.println("RSAPrivateKeySpec is unavailable.");
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			System.out.println("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.");
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用给定的公钥加密给定的字符串。
	 *
	 * @param key
	 *            给定的公钥。
	 * @param plaintext
	 *            字符串。
	 * @return 给定字符串的密文。
	 */
	public static String encryptString(Key key, String plaintext) {
		if (key == null || plaintext == null) {
			return null;
		}
		byte[] data = plaintext.getBytes();
		try {
			byte[] en_data = encrypt(key, data);
			return new String(Base64.encodeBase64String(en_data));
			// return new String(HexUtil.bytes2Hex(en_data));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用指定的公钥加密数据。
	 *
	 * @param key
	 *            给定的公钥。
	 * @param data
	 *            要加密的数据。
	 * @return 加密后的数据。
	 */

	public static byte[] encrypt(Key key, byte[] data) throws Exception {
		Cipher ci = Cipher.getInstance(ALGORITHM);
		ci.init(Cipher.ENCRYPT_MODE, key);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = ci.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = ci.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 使用给定的公钥解密给定的字符串。
	 *
	 * @param key
	 *            给定的公钥
	 * @param encrypttext
	 *            密文
	 * @return 原文字符串。
	 */
	public static String decryptString(Key key, String encrypttext) {
		if (key == null || isBlank(encrypttext)) {
			return null;
		}
		try {
			byte[] en_data = Base64.decodeBase64(encrypttext);
			// byte[] en_data = HexUtil.hex2Bytes(encrypttext);
			byte[] data = decrypt(key, en_data);
			return new String(data);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getCause().getMessage()));
		}
		return null;
	}

	/**
	 * 使用指定的公钥解密数据。
	 *
	 * @param key
	 *            指定的公钥
	 * @param data
	 *            要解密的数据
	 * @return 原数据
	 * @throws Exception
	 */
	public static byte[] decrypt(Key key, byte[] data) throws Exception {
		Cipher ci = Cipher.getInstance(ALGORITHM);
		ci.init(Cipher.DECRYPT_MODE, key);
		// return ci.doFinal(data);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = ci.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = ci.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 判断非空字符串
	 *
	 * @param cs
	 *            待判断的CharSequence序列
	 * @return 是否非空
	 */
	private static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
}