package answers.set1.challenge4;

import java.io.InputStream;
import java.nio.file.Paths;

import cn.hutool.http.HttpUtil;
import utils.BytesUtil;

public class DetectSingleCharacterXor {

    // the challenge data
    private String hex;
    private int cur = 0;

    public DetectSingleCharacterXor() {
        this.hex = HttpUtil.get("https://cryptopals.com/static/challenge-data/4.txt");
    }

    private byte[] nextNBytes(int n) {
        int len = n * 2;
        StringBuilder sb = new StringBuilder();
        while (cur < hex.length() && sb.length() < len) {
            char c = hex.charAt(cur);
            if (BytesUtil.isHexChar(c)) {
                sb.append(c);
            }
            cur++;
        }
        return BytesUtil.convertHexToBytes(sb.toString());
    }

    public static void main(String[] args) {
        
        DetectSingleCharacterXor challenge = new DetectSingleCharacterXor();
    }
}