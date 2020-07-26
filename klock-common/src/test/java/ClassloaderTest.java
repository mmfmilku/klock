import org.junit.Test;
import org.mmfmilku.klock.file.FileUtil;

import java.io.InputStream;
import java.net.URL;

public class ClassloaderTest {

    @Test
    public void fileTest() {
        byte[] fileData = FileUtil.getFileData("F:\\dev\\project\\klock-parent\\klock-common\\src\\test\\java\\MainTest.java");
        System.out.println(new String(fileData));
    }

    @Test
    public void test0() {
        URL resource = getClass().getClassLoader().getResource("");
        System.out.println(resource);
    }

}
