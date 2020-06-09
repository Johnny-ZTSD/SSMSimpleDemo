package log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author: 千千寰宇
 * @date: 2020/6/3  22:59:05
 * @description: ...
 */

public class MyLogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyLogTest.class);

    @Test
    public void testOpenFile(){
//        BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境
        openFile("356326");
    }

    public static void openFile(String filePath) {
        File file = new File(filePath);
        try {
            InputStream in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            //e要放在{}参数之外,而且只能放在最后一个。如果放在中间也不会被打印错误信息:
            LOGGER.error("can found file [{}]", filePath, e);
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        LOGGER.debug("线程开始");
        Thread.sleep(1 * 1000);
        test1();
        LOGGER.debug("Slf4J - 2个占位符，可传2个参数{}----{}", 1, 2);
    }

    public static void test1() {
        LOGGER.debug("test1方法");
    }

}
