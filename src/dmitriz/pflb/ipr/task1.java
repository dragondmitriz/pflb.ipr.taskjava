package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class task1 {

    private static final int MAX_SIZE = 10485760;

    public static void main(String[] args) {

        try {
            String fileName = args[0];
            String prefix = args[1];

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            int size = 0;
            int index = 0;
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(prefix + index + ".log"),
                    StandardCharsets.UTF_8);

            String line;
            while ((line = reader.readLine()) != null) {
                size += line.length();
                if (size >= MAX_SIZE) {
                    System.out.println("Check max size of file - next new file.");

                    index++;
                    writer = new OutputStreamWriter(new FileOutputStream(prefix + index + ".log"),
                            StandardCharsets.UTF_8);
                    size = 0;
                }
                writer.write(line + "\n");
            }

            System.out.println("Spliting is finished.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not found full parameters.\n" +
                    "Arguments of task 1:\n" +
                    "\tlog file name\n" +
                    "\tprefix of log file name after splitting");
        } catch (FileNotFoundException e) {
            System.out.println("File main.log.2014-11-17 is not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
