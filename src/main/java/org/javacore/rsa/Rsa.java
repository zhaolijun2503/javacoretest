package org.javacore.rsa;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class Rsa {
	
    private static final String RSA_PUBLICE =
    		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvAaU7SQcqr6U0B9s/pLa" + 
					"6ZinST2pos0tvxmNWkAPbpvlu3JCM/eyGqT5L2dSvpZxxWmllasKDlm+RsJdfZ68" + 
					"kyI9q94ctUfu1XI4O+1Sx6EeXzoFB7YbR1Rb9TtS3f56B1jfZDtSFkc2npzhzSKr" + 
					"b0jsc5l7UVpf6fAZnjdsgaumAOrOvGlnihyBeKSozmfdOC1aCTEgOt7dMF/K+gAn" + 
					"yDmP7YDTNxnnzBFMDso93Dh9B4HSsTdxAMuNzHE3QlniGiukRgLD/zhNNX0h21e8" + 
					"43gXs3Ce7h/B9YoJ15PFda2C4hTLfaUNFZsOfj0NdXvJmASjU+XNqx+td80pOebc" + 
					"ZwIDAQAB"; 
    private static final String ALGORITHM = "RSA";
 
    /**
     * 得到公钥
     * @param algorithm
     * @param bysKey
     * @return
     */
    private static PublicKey getPublicKeyFromX509(String algorithm,
            String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(bysKey);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }
 
    /**
     * 使用公钥加密
     * @param content
     * @param key
     * @return
     */
    public static String encryptByPublic(String content) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
 
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);
 
            byte plaintext[] = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);
 
            String s = new String(output);
 
            return s;
 
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        String source = "bIM6FUOyljmcAR7p6ztna7y+EfrLgmioz+WG2ukK2whqFYcSg1Ncy0gl9dgSiEnmRX4c5hh3wH7nZeuZRe5Vp3eabf0\\/+u0KUucxpoFGD6PMnyh5hICXTEPJWSZL45CUJNozVpqx9UQHhttz7GuAPDn8Ym2wblnPTJsptclxgElAadyoQMrDqY4PE3jx25BRTkT8l1kVIRdC1EbPatqMEeAZURh686iwX8tRxs7TXm6\\/GnD3pg4JjwP3iM87pR1gASleOt7V99CVTXhGFUhK4YRg7I6UD2VI1ZWxB2IhmXV2peTqTmb\\/BSCwCeA\\/tBXQ6FOpreR0curth6S5vqsTzw==";
    	System.out.println(Rsa.decryptByPublic(source));
    	String txt="%3Cp%3Evvvvvvvvvvvvvvvvvvvdssssssssssssssssssssssdddddddddddddddddddddddddssssssss%3Cbr%2F%3E%3C%2Fp%3E";
    	System.out.println(URLDecoder.decode(txt));
	}
 
    /**
    * 使用公钥解密
    * @param content 密文
    * @param key 商户私钥
    * @return 解密后的字符串
    */
    public static String decryptByPublic(String content) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, pubkey);
            byte[] cipherText = cipher.doFinal(content.getBytes()); 
    		return new String(cipherText);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

}
