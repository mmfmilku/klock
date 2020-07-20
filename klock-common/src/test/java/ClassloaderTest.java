import org.junit.Test;
import org.mmfmilku.klock.file.FileUtil;

public class ClassloaderTest {

    @Test
    public void fileTest() {
        byte[] fileData = FileUtil.getFileData("F:\\dev\\project\\klock-parent\\klock-common\\src\\test\\java\\MainTest.java");
        System.out.println(new String(fileData));
    }

}
