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

        try {
            String findString = args[0];
            String pathLog = args[1];
            String pathResult = args[2];

            StringBuilder resultString = new StringBuilder();

            File f = new File(pathLog);
            List<File> logFiles = f.isDirectory() ?
                    Arrays.asList(Objects.requireNonNull(f.
                            listFiles((dir, name) -> name.
                                    matches(".+.log$")))) :
                    Collections.singletonList(f);

            for (File log : logFiles) {

                if (log.isFile()) {
                    Files.readAllLines(log.toPath()).
                            forEach(line -> resultString.
                                    append(line.matches(findString) ? line + "\n" : ""));
                }
            }

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathResult + ".log"), StandardCharsets.UTF_8);
            writer.write(resultString.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not found full parameters.\n" +
                    "Arguments of task 2a:\n" +
                    "\tregex find string\n" +
                    "\tpath to log file(s)\n" +
                    "\tname of result log file");
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect path to log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
