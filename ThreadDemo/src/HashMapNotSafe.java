import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * @program:untitled
 * @description:
 * @author:Juwenchao
 * @date:2020-07-02 19:09:28
 */
public class HashMapNotSafe {

    public static void main(String[] args) {

        final HashMap<Integer, String> map = new HashMap();
        final Integer targetKey = 0b1111_1111_1111_1111; //65535
        final String targetValue = "v";
        map.put(targetKey, targetValue);
        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> map.put(key, "some value"));
        }).start();
        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not safe!");
            }
        }
    }


}
