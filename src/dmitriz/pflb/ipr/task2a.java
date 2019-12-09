package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
                    try (BufferedReader reader = new BufferedReader(new FileReader(log))) {

                        String line;
                        while ((line = reader.readLine()) != null) {

                            resultString.append(line.contains(findString) ? line + "\n" : "");
                        }
                    }
                }
            }

            Files.write(Paths.get(pathResult), resultString.toString().getBytes(), StandardOpenOption.CREATE);

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
