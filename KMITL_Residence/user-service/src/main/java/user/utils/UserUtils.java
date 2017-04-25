package user.utils;

import java.security.MessageDigest;

/**
 * Created by Adisorn on 2/4/2560.
 */
public class UserUtils {

    public static String hash(String txt) {
        String textToHash = txt;
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(textToHash.getBytes("UTF-8"));
            byte[] digest = md.digest();
            hash = String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hash;
    }

    public static String getRole(int role_id) {
        switch (role_id) {
            case 1: return "Admin";
            case 2: return "Staff";
            default: break;
        }
        return null;
    }

}
