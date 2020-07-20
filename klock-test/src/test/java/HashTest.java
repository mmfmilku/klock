import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class HashTest {

    @Test
    public void sha1Test() {
        String sha1 = DigestUtils.sha1Hex("hello world");
        System.out.println(sha1);
    }

}
