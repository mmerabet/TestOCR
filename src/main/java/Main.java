import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("/Users/mhmd.merabet/Workstation/TestOCR/src/main/resources/config.properties");
        properties.load(inputStream);
        inputStream.close();

        System.out.println(properties.getProperty("name"));

    }
}
