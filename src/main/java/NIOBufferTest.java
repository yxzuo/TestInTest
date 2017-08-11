import java.nio.IntBuffer;

/**
 * Created by yxzuo on 2017/8/10.
 */
public class NIOBufferTest {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(2);
        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.position());
        intBuffer.put(12345678);
        System.out.println(intBuffer.position());
        intBuffer.put(2);
        intBuffer.flip();
        System.err.println(intBuffer.get());
        System.err.println(intBuffer.get());
    }

}
