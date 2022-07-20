package Hashing_Algorithm;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class My_DES_AES {
    public static final int AES_128 = 128;
    public static final int AES_192 = 192;
    public static final int AES_256 = 256;
    String key; //user defined key
    byte[] DES_key = null;
    int AES_keyLen;
    byte[] AES_key = null;


    public My_DES_AES(String akey, int AES_keyLen) {
        key = akey;
        if (AES_keyLen == AES_192) this.AES_keyLen = AES_192;
        else if (AES_keyLen == AES_256) this.AES_keyLen = AES_256;
        else this.AES_keyLen = AES_128;
        DES_key = DES_createKey();
        AES_key = AES_createKey();
    }

    //Create DES key from string key
    public byte[] DES_createKey() {
        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            DES_key = Arrays.copyOf(keyBytes, 8);
            return DES_key;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Create AES key from string key
    public byte[] AES_createKey() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] keyBytes = key.getBytes("UTF-8");
            keyBytes = sha.digest(keyBytes);
            AES_key = Arrays.copyOf(keyBytes, AES_keyLen / 8);
            return AES_key;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Encrypt a source string using DES
    public String DES_encrypt(String source) {
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(DES_key, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            byte[] bytes = cipher.doFinal(source.getBytes());
            String encryptedStr = Base64.getEncoder().encodeToString(bytes);
            return encryptedStr;
        }
        catch(Exception e) {
            System.out.println("\n DES Encrypt: " + e.toString());
        }
        return null;
    }

    //Decrypt a DES base64 string
    public String DES_decrypt(String encryptedStr) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(DES_key, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] bytes = Base64.getDecoder().decode(encryptedStr);
            byte[] result = cipher.doFinal(bytes);
            return new String(result);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    //Encrypt a source string using AES
    public String AES_encrypt(String source) {
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(AES_key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            byte[] bytes = cipher.doFinal(source.getBytes("UTF-8"));
            String encryptedStr = Base64.getEncoder().encodeToString(bytes);
            return encryptedStr;
        }
        catch(Exception e) {
            System.out.println("\n AES Encrypt: " + e.toString());
        }
        return null;
    }

    //Decrypt a AES base64 string
    public String AES_decrypt(String encryptedString) {
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(AES_key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
            byte[] bytes = Base64.getDecoder().decode(encryptedString);
            byte[] result = cipher.doFinal(bytes);
            return new String(result);
        }
        catch(Exception e) {
            System.out.println("\n AES Decrypt: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        //Test DES
        String myKey = "My honey! 12";
        My_DES_AES myCipher = new My_DES_AES(myKey, 0);
        String src = "I love you more than I can say";
        String DES_encryptedStr = myCipher.DES_encrypt(src);
        String DES_decryptedStr = myCipher.DES_decrypt(DES_encryptedStr);
        System.out.println("DES Encrypted String: " + DES_encryptedStr);
        System.out.println("DES Decrypted String: " + DES_decryptedStr);

        //Test AES 192 bit
        My_DES_AES myCipher2 = new My_DES_AES(myKey, AES_128);
        String AES_encryptedStr = myCipher2.AES_encrypt(src);
        String AES_decryptedStr = myCipher2.AES_decrypt(AES_encryptedStr);
        System.out.println("AES Encrypted String: " + AES_encryptedStr);
        System.out.println("AES Decrypted String: " + AES_decryptedStr);
    }
}
