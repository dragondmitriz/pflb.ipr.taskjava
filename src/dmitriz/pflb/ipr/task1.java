package dmitriz.pflb.ipr;

import java.io.*;

public class task1 {

    private static final int MAX_SIZE = 10485760;

    public static void main(String[] args){

        String filename = args[0];
        String prefix = args[1];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            int size = 0;
            int indFile = 0;
            FileWriter writer = new FileWriter(prefix + (indFile++) + ".log");

            String line;
            while ((line=reader.readLine()) != null){
                size += line.length();
                if (size >= MAX_SIZE){
                    System.out.println("Предел размера файла достигнут. Создание нового файла.");

                    writer.close();
                    writer = new FileWriter(indFile++ + ".log");
                    size = 0;
                }
                writer.write(line+"\n");
            }
            System.out.println("Разделение на файлы завершено.");

            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл main.log.2014-11-17 отсутсвует");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
