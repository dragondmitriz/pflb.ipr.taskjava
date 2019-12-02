package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class task2a {

    public static void main(String[] args) {

        String findString = args[0];
        String pathLog = args[1];
        String pathResult = args[2];

        try {
            StringBuilder resultString = new StringBuilder();

            File f = new File(pathLog);
            List<File> logFiles = f.isDirectory() ?
                    Arrays.asList(Objects.requireNonNull(f.
                            listFiles((dir, name) -> name.
                                    matches(".+.log$")))) :
                    Collections.singletonList(f);

            for (File log : logFiles) {

                if (log.isFile()) {
//                    try {
                    Files.readAllLines(log.toPath()).
                            forEach(line -> resultString.
                                    append(line.matches(findString) ? line + "\n" : ""));

//                    } catch (MalformedInputException e) {
//                        Files.readAllLines(log.toPath(), StandardCharsets.ISO_8859_1).
//                                forEach(line -> resultString.
//                                        append(line.matches(findString) ? line + "\n" : ""));
//                    }
                }
            }

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathResult + ".log"), StandardCharsets.UTF_8);
            writer.write(resultString.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Incorrect path to log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
