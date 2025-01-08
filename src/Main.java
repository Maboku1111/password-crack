import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] password = "123456".getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(password);

        byte[] digest = md.digest();

        System.out.println("digest: " + bytesToHex(digest));
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

    }
}