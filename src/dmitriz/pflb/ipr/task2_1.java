package dmitriz.pflb.ipr;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task2_1 {

    public static void main(String[] args) {

        String findString = args[0];
        String logfiles = args[1];
        String resultFile = args[2];

        try {
            StringBuilder resultString = new StringBuilder("");

            File f = new File(logfiles);
            List<File> logFiles = f.isDirectory() ? Arrays.asList(f.listFiles()) : Collections.singletonList(f);
            for (File log : logFiles) {
                if (log.isFile()) {
                    Files.readAllLines(log.toPath()).forEach(line -> resultString.append(line.matches(findString) ? line + "\n" : ""));
                }
            }

            FileWriter writer = new FileWriter(resultFile);
            writer.write(resultString.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Указанные логи отсуствуют");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
