import java.io.*;

public class DataManager {

    public static void save(String text) {
        try {
            FileWriter fw = new FileWriter("trainlog.txt", true);
            fw.write(text + "\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}