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

            if (Files.exists(Paths.get(pathResult))) {
                Files.delete(Paths.get(pathResult));
                Files.createFile(Paths.get(pathResult));
            }

            StringBuilder resultString = new StringBuilder();

            File f = new File(pathLog);
            List<File> logFiles = f.isDirectory() ?
                    Arrays.asList(Objects.requireNonNull(f.
                            listFiles((dir, name) -> name.
                                    matches(".+.log$")))) :
                    Collections.singletonList(f);

            for (File log : logFiles) {
                int i = 0;

                if (log.isFile()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(log))) {

                        String line;
                        while ((line = reader.readLine()) != null) {

                            if (line.contains(findString)) {

                                resultString
                                        .append(line)
                                        .append("\n");
                                if (i >= 20) {

                                    Files.write(Paths.get(pathResult),
                                            resultString.toString().getBytes(),
                                            StandardOpenOption.APPEND);
                                    resultString = new StringBuilder();
                                    i = 0;
                                } else {
                                    i++;
                                }
                            }

                        }
                    }
                }
            }

            Files.write(Paths.get(pathResult),
                    resultString.toString().getBytes(),
                    StandardOpenOption.APPEND);

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
