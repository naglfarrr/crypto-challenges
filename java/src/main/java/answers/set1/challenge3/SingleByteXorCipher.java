package answers.set1.challenge3;

public class SingleByteXorCipher {
    
    private static String decrypt(String hex, byte key) {
        byte[] bytes = utils.BytesUtil.convertHexToBytes(hex);
        byte[] decrypted = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            decrypted[i] = (byte) (bytes[i] ^ key);
        }
        return new String(decrypted);
    }
}
