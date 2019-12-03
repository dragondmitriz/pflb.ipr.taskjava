package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
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

        try {
            String spliter = args[0];
            String logPath = args[1];
            String resultFile = args[2];

            StringBuilder resultString = new StringBuilder("");


            File file = new File(logPath);
            List<File> logFiles = file.isDirectory() ?
                    Arrays.asList(Objects.requireNonNull(file.
                            listFiles((dir, name) -> name.
                                    matches(".+.log$")))) :
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

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not found full parameters.\n" +
                    "Arguments of task 2b:\n" +
                    "\tseparator\n" +
                    "\tpath to log file(s)\n" +
                    "\tname of result csv file");
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect path to log");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
