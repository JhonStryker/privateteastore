package com.priavteTeaStore.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Thoughtworks on 16/6/14.
 */
public class MD5 {

    public static String encryptMD5(String strInput) {
        String strOutput = "";

        try {
            MessageDigest nsae = MessageDigest.getInstance("MD5");
            nsae.update(strInput.getBytes());
            byte[] b = nsae.digest();

            for (int i = 0; i < b.length; ++i) {
                char[] digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[]{digit[b[i] >>> 4 & 15], digit[b[i] & 15]};
                strOutput = strOutput + new String(ob);
            }

            return strOutput;
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException(var7);
        }
    }
}
