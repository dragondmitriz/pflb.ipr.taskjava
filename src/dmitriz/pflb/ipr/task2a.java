package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class task2a {

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
                    try{
                        Files.readAllLines(log.toPath()).forEach(line -> resultString.append(line.matches(findString) ? line + "\n" : ""));
                    } catch (MalformedInputException e){
                        Files.readAllLines(log.toPath(), StandardCharsets.ISO_8859_1).forEach(line -> resultString.append(line.matches(findString) ? line + "\n" : ""));
                    }
                }
            }

            FileOutputStream foutstream = new FileOutputStream(resultFile + ".log");
            OutputStreamWriter writer = new OutputStreamWriter(foutstream, StandardCharsets.UTF_8);
            writer.write(resultString.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Указанные логи отсуствуют");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
