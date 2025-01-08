import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("digest: " + hashFunction());
    }

    public static String hashFunction() throws NoSuchAlgorithmException {
        byte[] password = "123456".getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password);
        byte[] digest = md.digest();
        return bytesToHex(digest);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder out = new StringBuilder();
        for (byte b : bytes) {
            out.append(String.format("%02X", b));
        }
        return out.toString();
    }

    public static void bruteForceMD5(String targetHash, String charset, int maxLength) {
        for (int length = 1; length <= maxLength; length++) {
            System.out.println("Trying passwords of length: " + length);
            generatePermutations(charset, length, targetHash);
        }
    }

    public static void generatePermutations(String charset, int length, String targetHash) {
        char[] current = charset.toCharArray();

        heapPermute(length, current, targetHash);
    }

    public static void heapPermute(int n, char[] current, String targetHash) {
        if (n == 1) {
            Arrays.toString(current);
        }
    }
}