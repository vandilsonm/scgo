/*
 * To change this template, choose Tools | Templates
 * and open the template in the eitor.
 */

package br.una.sgco.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Dirceu
 * 
 */
public class Criptografia {

    public static String md5(String valor){
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));
        sen = hash.toString(16);
        System.out.println(sen);
        return sen;
    }

}
