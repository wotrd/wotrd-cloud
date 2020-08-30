package com.wotrd.dubbo.common.utils.rsa;

import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

public class RSACoder {

	public static final String KEY_ALGORITHM = "RSA";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	// 获得公钥
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		// 获得map中的公钥对象 转为key对象
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		// byte[] publicKey = key.getEncoded();
		// 编码返回字符串
		return encryptBASE64(key.getEncoded());
	}

	// 获得私钥
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		// 获得map中的私钥对象 转为key对象
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		// byte[] privateKey = key.getEncoded();
		// 编码返回字符串
		return encryptBASE64(key.getEncoded());
	}

	// 编码返回字符串
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	// map对象中存放公私钥
	public static Map<String, Object> initKey() throws Exception {
		// 获得对象 KeyPairGenerator 参数 RSA 1024个字节
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		// 通过对象 KeyPairGenerator 获取对象KeyPair
		KeyPair keyPair = keyPairGen.generateKeyPair();

		// 通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 公私钥对象存入map中
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * RSA 解密
	 * 
	 * @param encript
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(String encript) throws Exception {

		PrivateKey privateKey = RSAUtil
				.getPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJAcIG5gH95OrGc//UB1WDfYwEDA"
						+ "h71xjV1qS/28gA/oOo75rdgm1QxZN9wMvlM6j/51jjGxluNWBplHHJFF8tGb2zpVxOx63F/ywexy"
						+ "MF95NhhxkkSv8DMi/tO0pdqq9kOmWZwBso92TptcfmaMMPuRnAmtkLkT3gk6QqNIY8BZAgMBAAEC"
						+ "gYB+RJbJ/2GM+OQop7Gv2+GLNVjmo4uMG0WhFzU0Qwj2ZyWABxcisIu2ASpnnabZ6BMpAzmvIwqx"
						+ "cLvha6SFSYPCfFtjrLGkUOCMtihws9e+8gxbg3bzPDq3DoIhAQC/9u0HaZrdW7YwAfr/n4hfahqN"
						+ "xNVYRnunSRTq6fu/nr4sAQJBAOku1oQ4g0BdcIQ6NbdQ8Lu2npYzPNhzVp87a3hQWJ71mZOftCUt"
						+ "Mo0aqpvI3XBaPXfMTwh1mw2g/dnXIfUtD4ECQQCeNgjzyR4X1HUrIHRzEWMUIbf+zW72V8AiFska"
						+ "BVHLGvz5VfjcuT04wilVSkgjWdgChGLOkSXOFTBLg0OvP5zZAkAo3hqepcDrIPU9V25eqILog0Mc"
						+ "E/LQBLbjxWg6d+pwQZ8e7liGQ3uem1PXZcbZkBdBzb70PphkNRkIetnqvHEBAkBtpQ8hPNGhPDmT"
						+ "kOODV5ptukyUxvTOvtPGDDqE0Qj1lJebiQx2k94s9srEdD14WmyMmgkMFaW4pBCbVLZUvuZJAkBI"
						+ "ZeOyRPZTlf0YGoXWfnfzOWob/WrMKi3HvCg8wG87SLkClvRDqy099sHtQpF2RoJ+l3JF8cgoqXKcCg4S2Cgi");

		String oldSource = RSAUtil.decryptString(privateKey, encript);
		System.out.println("解密后数据:" + oldSource);
		return oldSource;

	}

	public static void main(String[] args) {
		Map<String, Object> keyMap;
		try {
			 keyMap = initKey();
			 String publicKey = getPublicKey(keyMap);
			 String privateKey = getPrivateKey(keyMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
