package dmitriz.pflb.ipr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class task1 {

    private static final int MAX_SIZE = 10485760;

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {

            String prefix = args[1];

            int size = 0;
            int index = 0;
            StringBuilder resultString = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {

                size += line.length();
                if (size >= MAX_SIZE) {

                    System.out.println("Check max size of file - next new file.");

                    Files.write(Paths.get(prefix + index + ".log"),
                            resultString.toString().getBytes(),
                            StandardOpenOption.CREATE);

                    index++;
                    resultString = new StringBuilder();
                    size = line.length();
                }
                resultString
                        .append(line)
                        .append("\n");
            }

            Files.write(Paths.get(prefix + index + ".log"),
                    resultString.toString().getBytes(),
                    StandardOpenOption.CREATE);

            System.out.println("Spliting is finished.");
        } catch (FileNotFoundException e) {
            System.out.println("File main.log.2014-11-17 is not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
