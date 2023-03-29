package answers.set1.challenge3;

import java.util.stream.IntStream;

public class SingleByteXorCipher {

    private char key;
    private String decrypted;
    private double prob;

    public SingleByteXorCipher(String hex, char key) {
        this.key = key;
        this.decrypted = decrypt(hex, (byte) key);
        this.prob = EnglishLetterFrequency.probOfEnglish(this.decrypted);
    }

    public char getKey() {
        return key;
    }

    public double getProb() {
        return prob;
    }

    public String getDecrypted() {
        return decrypted;
    }

    public boolean isMeaningful() {
        return !this.decrypted.chars().anyMatch(c -> c < 32 || c > 126);
    }
    
    private static String decrypt(String hex, byte key) {
        byte[] bytes = utils.BytesUtil.convertHexToBytes(hex);
        byte[] decrypted = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            decrypted[i] = (byte) (bytes[i] ^ key);
        }
        // System.out.println("debug: key=" + key + ", decrypted=" + new String(decrypted) + ", d[0]=" + decrypted[0] + ", d[1]=" + decrypted[1]);
        // System.out.println("debug: key= " + key + ", char=" + (char) key);
        return new String(decrypted);
    }

    static class EnglishLetterFrequency {
        private static final double[] ENGLISH_LETTER_FREQUENCIES = {
            0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074
        };

        private static double getLetterFrequency(char letter) {
            if (letter >= 'A' && letter <= 'Z') {
                return ENGLISH_LETTER_FREQUENCIES[letter - 'A'];
            } else if (letter >= 'a' && letter <= 'z') {
                return ENGLISH_LETTER_FREQUENCIES[letter - 'a'];
            } else {
                return 0.02;
            }
        }

        private static double probInEnglish(String text) {
            double prob = 0;
            for (int i = 0; i < text.length(); i++) {
                prob += Math.log(getLetterFrequency(text.charAt(i)));
            }
            // return prob;
            return Math.exp(prob);
        }

        private static double probInRandom(String text) {
            double prob = 0;
            for (int i = 0; i < text.length(); i++) {
                prob += isLetter(text.charAt(i)) ? Math.log(1.0 / 26) : Math.log(0.02);
            }
            // return prob;
            return Math.exp(prob);
        }

        private static boolean isLetter(char c) {
            return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
        }

        public static double probOfEnglish(String text) {
            double pe = probInEnglish(text);
            double pr = probInRandom(text);
            // System.out.println("debug: text=" + text + ", pe=" + pe + ", pr=" + pr);
            return pe / (pe + pr);
            // return Math.exp(pe) / (Math.exp(pe) + Math.exp(pr));
        }
    }

    public static void main(String[] args) {
        String hex = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        IntStream.rangeClosed(32, 126)
            .mapToObj(i -> new SingleByteXorCipher(hex, (char) i))
            .filter(obj -> obj.isMeaningful())
            .sorted((a, b) -> Double.compare(b.getProb(), a.getProb()))
            .limit(5)
            .forEach(c -> System.out.println((int) c.getKey() + " | " + c.getProb() + " | " + c.getDecrypted()));
    }
}
