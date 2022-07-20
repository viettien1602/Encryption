package Hashing_Algorithm;

import java.util.concurrent.CountDownLatch;

public class MySymmetricEncrypter {
    private int TABLE_SIZE = 65536;
    int nPosition;
    public MySymmetricEncrypter(int nPosition) {
        this.nPosition = nPosition;
    }

    private char shift(char c, int nPos) {
        int charCode = (int)c + nPos;
        if (charCode < 0) charCode += TABLE_SIZE;
        return (char)(charCode % TABLE_SIZE);
    }

    public String encode(String src) {
        String result = "";
        for (int i = 0; i < src.length(); i++) {
            result += shift(src.charAt(i), nPosition);
        }
        return result;
    }

    public String decode(String encodedStr) {
        String src = "";
        for (int i = 0; i < encodedStr.length(); i++) {
            src += shift(encodedStr.charAt(i), -nPosition);
        } 
        return src;
    }

    public String complexEncode(String src) {
        String result = "";
        char curChar = src.charAt(0);
        result += shift(curChar, nPosition);
        int newShift;
        for (int i = 1; i < src.length(); i++) {
            newShift = (int) curChar;
            curChar = src.charAt(i);
            result += shift(curChar, newShift);
        }
        return result;
    }

    public String complexDecode(String encodedStr) {
        String src = "";
        char curChar = shift(encodedStr.charAt(0), -nPosition);
        src += curChar;
        int newShift;
        for (int i = 1; i < encodedStr.length(); i++) {
            newShift = (int)curChar;
            curChar = shift(encodedStr.charAt(i), -newShift);
            src += curChar;
        }
        return src;
    }

    public static void main(String[] args) {
        String src = "ABCXYZ nghịch ngợm";
        int nPosition = 3;
        System.out.println("Source: " + src);
        MySymmetricEncrypter shifter = new MySymmetricEncrypter(nPosition);
        //Test CEASAR symmetric encryption
        System.out.println("CEASER encode and decode: ");
        String encodedStr = shifter.encode(src);
        String decodedStr = shifter.decode(encodedStr);
        System.out.println("Encoded string: " + encodedStr);
        System.out.println("Decoded string: " + decodedStr);

        //Test complex symmetric encryption
        System.out.println("Complex encode and decode: ");
        String complexEncodedStr = shifter.complexEncode(src);
        String complexDecodedStr = shifter.complexDecode(complexEncodedStr);
        System.out.println("Encoded string: " + complexEncodedStr);
        System.out.println("Decoded string: " + complexDecodedStr);
        

    }
}
