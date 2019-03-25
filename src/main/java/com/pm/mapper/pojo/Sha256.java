package com.pm.mapper.pojo;

import java.security.MessageDigest;

public class Sha256 {

    public static String encrypt(String text) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            byte[] data = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < data.length; i++) {
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < data.length; i++) {
                String hex = Integer.toHexString(0xff & data[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException();

        }
    }
}
