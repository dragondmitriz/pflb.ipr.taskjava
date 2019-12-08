package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class task1 {

    private static final int MAX_SIZE = 10485760;

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {

            String prefix = args[1];

            int size = 0;
            int index = 0;

            File newLogFile = new File(prefix + index + ".log");
            if (newLogFile.exists()) newLogFile.delete();


            String line;
            while ((line = reader.readLine()) != null) {

                try (OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(prefix + index + ".log", true),
                        StandardCharsets.UTF_8)) {

                    size += line.length();
                    if (size >= MAX_SIZE) {

                        System.out.println("Check max size of file - next new file.");

                        index++;

                        newLogFile = new File(prefix + index + ".log");
                        if (newLogFile.exists()) newLogFile.delete();

                        size = 0;
                    }
                    writer.write(line + "\n");
                }
            }

            System.out.println("Spliting is finished.");
        } catch (FileNotFoundException e) {
            System.out.println("File main.log.2014-11-17 is not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
