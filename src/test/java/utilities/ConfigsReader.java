package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigsReader {

    private static Properties configs;

    //static block runs first always(when make an object from this class)
    static {
        try {
            //path where actual configs is stored
            String path = "configs/configs.properties";
            //initializing configs(Properties)
            configs = new Properties();

            //taking all data from actual configs in - path
            FileInputStream input = new FileInputStream(path);
            //loading data to configs(Properties) - (making a copy of actual configs)
            configs.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //returns value from configs
    public static String getProperty(String keyName) {
        return configs.getProperty(keyName);
    }

}
