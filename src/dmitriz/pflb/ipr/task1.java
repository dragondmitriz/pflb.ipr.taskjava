package dmitriz.pflb.ipr;

import java.io.*;

public class task1 {

    private static final int MAX_SIZE = 10485760;

    public static void main(String[] args){

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./main.log.2014-11-17"));

            int size = 0;
            int indFile = 0;
            FileWriter writer = new FileWriter(indFile++ + ".log");

            String line;
            while ((line=reader.readLine()) != null){
                size += line.length();
                if (size >= MAX_SIZE){
                    writer.close();
                    writer = new FileWriter(indFile++ + ".log");
                    size = 0;
                }
                writer.write(line+"\n");
            }

            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл main.log.2014-11-17 отсутсвует");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
