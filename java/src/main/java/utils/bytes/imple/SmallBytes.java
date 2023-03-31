package utils.bytes.imple;

import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import io.vavr.collection.List;
import utils.bytes.Bytes;

public class SmallBytes implements Bytes {

    private List<Byte> bytes;

    @Override
    public String toHex() {
        return bytes.map(b -> String.format("%02x", b)).mkString();
    }

    @Override
    public java.util.stream.Stream <Byte> stream() {
        return bytes.toJavaStream();
    }

    @Override
    public Collector <Byte, ?, Bytes> collector() {

        new Collector<Byte, List<Byte>, Bytes>() {

            @Override
            public BiConsumer<List<Byte>, Byte> accumulator() {
                return (list, b) -> list.prepend(b);
            }

            @Override
            public Set<Characteristics> characteristics() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'characteristics'");
            }

            @Override
            public BinaryOperator<List<Byte>> combiner() {
                return (list1, list2) -> list1.prependAll(list2);
            }

            @Override
            public Function<List<Byte>, Bytes> finisher() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'finisher'");
            }

            @Override
            public Supplier<List<Byte>> supplier() {
                return () -> List.empty();
            }
            
        };

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collector'");
    }

    @Override
    public Iterator<Byte> iterator() {
        return bytes.iterator();
    }
}
