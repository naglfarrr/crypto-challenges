package answers.set1.challenge2;

import utils.BytesUtil;

public class FixedXor {

    public static void main(String[] args) {
        String hex1 = "1c0111001f010100061a024b53535009181c";
        String hex2 = "686974207468652062756c6c277320657965";
        String expected = "746865206b696420646f6e277420706c6179";
        System.out.println("Expected: " + expected);

        byte[] bytes1 = BytesUtil.convertHexToBytes(hex1);
        byte[] bytes2 = BytesUtil.convertHexToBytes(hex2);
        byte[] result = xor(bytes1, bytes2);
        String actual = BytesUtil.convertBytesToHex(result);
        System.out.println("Actual:   " + actual);
    }

    private static byte[] xor(byte[] a, byte[] b) {
        if (a == null || b == null || a.length != b.length) {
            throw new IllegalArgumentException("Both arrays must be non-null and of equal length");
        }
        int len = a.length;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = (byte) (a[i] ^ b[i]);
        }
        return result;
    }
}
