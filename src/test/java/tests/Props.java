package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Props {
    protected static Properties PROPERTIES;
    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            try (InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
                PROPERTIES = new Properties();
                PROPERTIES.load(reader);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
