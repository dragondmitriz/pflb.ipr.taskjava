package dmitriz.pflb.ipr;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class task1 {

    private static final int MAX_SIZE = 10485760;

    public static void main(String[] args){

        String filename = args[0];
        String prefix = args[1];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            int size = 0;
            int indFile = 0;
            FileOutputStream foutstream = new FileOutputStream(prefix + (indFile++) + ".log");
            OutputStreamWriter writer = new OutputStreamWriter(foutstream, StandardCharsets.UTF_8);

            String line;
            while ((line=reader.readLine()) != null){
                size += line.length();
                if (size >= MAX_SIZE){
                    System.out.println("Check max size of file - next new file.");

                    writer.close();
                    writer = new FileWriter(prefix + (indFile++) + ".log");
                    size = 0;
                }
                writer.write(line+"\n");
            }
            System.out.println("Spliting is finished.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл main.log.2014-11-17 отсутсвует");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
