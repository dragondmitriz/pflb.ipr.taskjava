package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task2b {

    private static final String REGEX =
            "^(\\d{2}.\\d{2}.\\d{4}) (\\d{2}:\\d{2}:\\d{2}.\\d{3}) (\\w{3}[\\w ]{2}): (((.+)\\t(.*))|(.*))$";

    private static List<String> getAllRegex(String target) {
        List<String> result = new ArrayList<>();

        Matcher matcher = Pattern.compile(REGEX).matcher(target);
        while (matcher.find()) {
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

            StringBuilder resultString = new StringBuilder();

            File file = new File(logPath);
            List<File> logFiles = file.isDirectory() ?
                    Arrays.asList(Objects.requireNonNull(file.
                            listFiles((dir, name) -> name.
                                    matches(".+.log$")))) :
                    Collections.singletonList(file);
            for (File log : logFiles) {

                if (log.isFile()) {

                    try (BufferedReader reader = new BufferedReader(new FileReader(log))) {

                        String line;
                        while ((line = reader.readLine()) != null) {

                            resultString
                                    .append(String.join(spliter, getAllRegex(line)))
                                    .append("\n");
                        }
                    }
                }
            }

            Files.write(Paths.get(resultFile + ".csv"),
                    resultString.toString().getBytes(),
                    StandardOpenOption.CREATE);

        } catch (ArrayIndexOutOfBoundsException e) {
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
