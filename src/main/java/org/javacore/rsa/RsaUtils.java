package org.javacore.rsa;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
public class RsaUtils {
 
	    static String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvAaU7SQcqr6U0B9s/pLa6ZinST2pos0tvxmNWkAPbpvlu3JCM/eyGqT5L2dSvpZxxWmllasKDlm+RsJdfZ68kyI9q94ctUfu1XI4O+1Sx6EeXzoFB7YbR1Rb9TtS3f56B1jfZDtSFkc2npzhzSKrb0jsc5l7UVpf6fAZnjdsgaumAOrOvGlnihyBeKSozmfdOC1aCTEgOt7dMF/K+gAnyDmP7YDTNxnnzBFMDso93Dh9B4HSsTdxAMuNzHE3QlniGiukRgLD/zhNNX0h21e843gXs3Ce7h/B9YoJ15PFda2C4hTLfaUNFZsOfj0NdXvJmASjU+XNqx+td80pOebcZwIDAQAB";
        static String privateKey="bIM6FUOyljmcAR7p6ztna7y+EfrLgmioz+WG2ukK2whqFYcSg1Ncy0gl9dgSiEnmRX4c5hh3wH7nZeuZRe5Vp3eabf0\\/+u0KUucxpoFGD6PMnyh5hICXTEPJWSZL45CUJNozVpqx9UQHhttz7GuAPDn8Ym2wblnPTJsptclxgElAadyoQMrDqY4PE3jx25BRTkT8l1kVIRdC1EbPatqMEeAZURh686iwX8tRxs7TXm6\\/GnD3pg4JjwP3iM87pR1gASleOt7V99CVTXhGFUhK4YRg7I6UD2VI1ZWxB2IhmXV2peTqTmb\\/BSCwCeA\\/tBXQ6FOpreR0curth6S5vqsTzw==";
	    /** *//**
	     * 加密算法RSA
	     */
	    public static final String KEY_ALGORITHM = "RSA";


	    /** *//**
	     * RSA最大加密明文大小
	     */
	    private static final int MAX_ENCRYPT_BLOCK = 117;
	    private static final int MAX_DECRYPT_BLOCK =256;



	    /** *//**
	     * <p>
	     * 公钥加密
	     * </p>
	     *
	     * @param data 源数据
	     * @param publicKey 公钥(BASE64编码)
	     * @return
	     * @throws Exception
	     */
	    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
	            throws Exception {
	        byte[] keyBytes = Base64.decodeBase64(publicKey);
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key publicK = keyFactory.generatePublic(x509KeySpec);
	        // 对数据加密
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	        cipher.init(Cipher.ENCRYPT_MODE, publicK);
	        int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段加密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_ENCRYPT_BLOCK;
	        }
	        byte[] encryptedData = out.toByteArray();
	        out.close();
	        return encryptedData;
	    }
	    
	    public static byte[] decryptByPublicKey(byte[] data, String privateKey)
	            throws Exception {
	        /*byte[] keyBytes = Base64.decode(privateKey);
			PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key privatek = keyFactory.generatePrivate(keySpec);*/
	    	 byte[] keyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey);
		        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		        Key privatek = keyFactory.generatePublic(x509KeySpec);
	        // 对数据加密
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 
	        cipher.init(Cipher.DECRYPT_MODE, privatek);
	        int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段加密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] encryptedData = out.toByteArray();
	        out.close();
	        return encryptedData;
	    }
	    
	    public static byte[] decrypt(RSAPublicKey privateKey, byte[] cipherData)
	            throws Exception {
	        if (privateKey == null) {
	            throw new Exception("解密私钥为空, 请设置");
	        }
	        Cipher cipher = null;
	        try {
	            // , new BouncyCastleProvider()
	            cipher = Cipher.getInstance("RSA");
	            cipher.init(Cipher.DECRYPT_MODE, privateKey);
	            byte[] output = cipher.doFinal(cipherData);
	            return output;
	        } catch (NoSuchAlgorithmException e) {
	            throw new Exception("无此解密算法");
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	            return null;
	        } catch (InvalidKeyException e) {
	            throw new Exception("解密私钥非法,请检查");
	        } catch (IllegalBlockSizeException e) {
	            throw new Exception("密文长度非法");
	        } catch (BadPaddingException e) {
	            throw new Exception("密文数据已损坏");
	        }
	    }

	    public static void main(String[] args) throws Exception {
	        String source = "bIM6FUOyljmcAR7p6ztna7y+EfrLgmioz+WG2ukK2whqFYcSg1Ncy0gl9dgSiEnmRX4c5hh3wH7nZeuZRe5Vp3eabf0\\/+u0KUucxpoFGD6PMnyh5hICXTEPJWSZL45CUJNozVpqx9UQHhttz7GuAPDn8Ym2wblnPTJsptclxgElAadyoQMrDqY4PE3jx25BRTkT8l1kVIRdC1EbPatqMEeAZURh686iwX8tRxs7TXm6\\/GnD3pg4JjwP3iM87pR1gASleOt7V99CVTXhGFUhK4YRg7I6UD2VI1ZWxB2IhmXV2peTqTmb\\/BSCwCeA\\/tBXQ6FOpreR0curth6S5vqsTzw==";
	        byte[] keyBytes =org.apache.commons.codec.binary.Base64.decodeBase64(source);
	        //byte[] keyBytes = Base64.decode("bIM6FUOyljmcAR7p6ztna7y+EfrLgmioz+WG2ukK2whqFYcSg1Ncy0gl9dgSiEnmRX4c5hh3wH7nZeuZRe5Vp3eabf0\\/+u0KUucxpoFGD6PMnyh5hICXTEPJWSZL45CUJNozVpqx9UQHhttz7GuAPDn8Ym2wblnPTJsptclxgElAadyoQMrDqY4PE3jx25BRTkT8l1kVIRdC1EbPatqMEeAZURh686iwX8tRxs7TXm6\\/GnD3pg4JjwP3iM87pR1gASleOt7V99CVTXhGFUhK4YRg7I6UD2VI1ZWxB2IhmXV2peTqTmb\\/BSCwCeA\\/tBXQ6FOpreR0curth6S5vqsTzw==");
	        //byte[] keyBytes = source.getBytes("UTF-8");
			BASE64Decoder base64Decoder= new BASE64Decoder();
			byte[] buffer= base64Decoder.decodeBuffer(source);
	        System.out.println("原文字：\r\n" + source);
	        byte[] encodedData = RsaUtils.decryptByPublicKey(keyBytes, publicKey);
	        String encode=new String(encodedData,"UTF-8");
	        System.out.println("解密后：\r\n" +encode);

	    	// 原文  
	    	/*String str = "abcd";
	    	byte[] bs = str.getBytes();
	    	String s = new String(bs);
	    	byte[] keyBytes = Base64.decode(publicKey);
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key publicK = keyFactory.generatePublic(x509KeySpec);
	        byte[] plainText = "abcd".getBytes("UTF-8"); 
	        //加密工具  
	        Cipher c1 =Cipher.getInstance("RSA/ECB/NoPadding");  
	        c1.init(Cipher.ENCRYPT_MODE, publicK);  
	        byte[] cipherText = c1.doFinal(plainText);
	        System.out.println("加密前"+Base64.encode(cipherText));
	        Cipher c2 =Cipher.getInstance("RSA/ECB/NoPadding");  
	        c2.init(Cipher.DECRYPT_MODE, publicK);  
	        byte[] output = c2.doFinal(cipherText); 
	        System.out.println("加密前"+Base64.encode(output));*/
	    }
	

}
