package utils.bytes;

import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * 一段字节序列
 */
public interface Bytes {
    
    /**
     * 输出字节序列的 16 进制表示
     * @return 16 进制字符串
     */
    String toHex();

    /**
     * 返回一个流，流中的每个元素都是字节序列中的一个字节
     * @return 字节流
     */
    Stream <Byte> stream();

    /**
     * 返回一个收集器，用于收集字节流
     * @return 收集器
     */
    Collector <Byte, ?, Bytes> collector();

    /**
     * 返回一个迭代器，用于遍历字节序列
     * @return 迭代器
     */
    Iterator <Byte> iterator();
}
