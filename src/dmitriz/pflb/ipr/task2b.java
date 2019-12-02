package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task2b {

    private static final String REGEX = "^(\\d{2}.\\d{2}.\\d{4}) (\\d{2}:\\d{2}:\\d{2}.\\d{3}) (\\w{3}[\\w ]{2}): (((.+)\\t(.*))|(.*))$";

    private static List getAllRegex(String target){
        List result = new ArrayList();

        Matcher matcher = Pattern.compile(REGEX).matcher(target);
        while(matcher.find()){
            result.add(matcher.group(1));
            result.add(matcher.group(2));
            result.add(matcher.group(3));
            if (matcher.group(8) == null) {
                result.add(matcher.group(6));
                result.add(matcher.group(7));
            } else {
                result.add(matcher.group(8));
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String spliter = args[0];
        String logPath = args[1];
        String resultFile = args[2];

        try {
            StringBuilder resultString = new StringBuilder("");


            File file = new File(logPath);
            List<File> logFiles = file.isDirectory() ?
                    Arrays.asList(file.listFiles()) :
                    Collections.singletonList(file);
            for (File log : logFiles) {
                if (log.isFile()) {
                    Files.readAllLines(log.toPath())
                            .forEach(line -> resultString
                                    .append(String.join(spliter, getAllRegex(line)))
                                    .append("\n"));
                }
            }

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(resultFile + ".csv"),
                    StandardCharsets.UTF_8);
            writer.write(resultString.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Указанные логи отсуствуют");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
