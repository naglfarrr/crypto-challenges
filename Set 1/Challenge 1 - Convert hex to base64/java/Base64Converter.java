import java.util.Scanner;

public class Base64Converter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
            System.out.println("sample input: " + hex);
            System.out.println("sample output: " + convertHexToBase64(hex));
            System.out.println("Now try your own input: ");
            hex = scanner.nextLine();
            while (!hex.equals("exit") && !hex.equals("quit") && !hex.equals("q")) {
                System.out.println("base64: " + convertHexToBase64(hex));
                System.out.println("The input has " + hex.length() / 2 + " bytes");
                hex = scanner.nextLine();
            }
        } finally {
            scanner.close();
        }
    }

    public static String convertHexToBase64(String hex) {
        return convertBytesToBase64(convertHexToBytes(hex));
    }

    /**
     * Converts a hex string to a byte array
     * The hex string must have an even number of characters
     * @param hex The hex string to convert
     * @return byte array
     */
    public static byte[] convertHexToBytes(String hex) {
        if (hex == null || hex.length() == 0) {
            return new byte[0];
        }
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even number of characters");
        }
        int len = hex.length();
        byte[] bytes = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

    public static String convertBytesToBase64(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        int len = bytes.length;
        int padding = 0;
        String[] dict = Base64Dict.INSTANCE.getDict();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i += 3) {
            int b1 = bytes[i] & 0xFF;
            int b2 = i + 1 < len ? bytes[i + 1] & 0xFF : 0;
            int b3 = i + 2 < len ? bytes[i + 2] & 0xFF : 0;

            int c1 = b1 >> 2;
            int c2 = ((b1 & 0x3) << 4) | (b2 >> 4);
            int c3 = ((b2 & 0xF) << 2) | (b3 >> 6);
            int c4 = b3 & 0x3F;

            sb.append(dict[c1]);
            sb.append(dict[c2]);
            if (i + 1 < len) {
                sb.append(dict[c3]);
            } else {
                padding++;
            }
            if (i + 2 < len) {
                sb.append(dict[c4]);
            } else {
                padding++;
            }
        }

        if (padding > 0) {
            for (int i = 0; i < padding; i++) {
                sb.append("=");
            }
        }
        return sb.toString();
    }
}

enum Base64Dict {
    INSTANCE;

    private final String[] dict = new String[] {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "/"
    };

    public String[] getDict() {
        return dict;
    }
}