import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("digest: " + hashFunction());
        System.out.println(bruteForceMD5("7a95bf926a0333f57705aeac07a362a2", "1234", 4));
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

    public static String bruteForceMD5(String targetHash, String charset, int maxLength) throws NoSuchAlgorithmException {
        for (int length = 1; length <= maxLength; length++) {
            System.out.println("Trying passwords of length: " + length);
            generatePermutations(charset, length, targetHash);
        }
        return targetHash;
    }

    public static void generatePermutations(String charset, int length, String targetHash) throws NoSuchAlgorithmException {
        char[] current = charset.toCharArray();

        heapPermute(length, current, targetHash);
    }

    public static void heapPermute(int n, char[] current, String targetHash) throws NoSuchAlgorithmException {
        if (n == 1) {
            String string = Arrays.toString(current);
            System.out.println(string);

            String md5Hash = hashFunction();

            if (md5Hash.equals(targetHash)) {
                System.out.println("Password found: " + string);
            }
        }

        for (int i = 0; i < n; i++) {
            heapPermute(n - 1, current, targetHash);

            if (n % 2 == 0) {
                swap(Arrays.toString(current), i, n);
            } else {
                swap(Arrays.toString(current), n, i);
            }
        }
    }

    public static String swap(String str, int i, int j) {
        char temp;
        char[] charArray = str.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;

        return String.valueOf(charArray);
    };
}