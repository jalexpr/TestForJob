package hometest.singlethreaded;

import hometest.WorkingWithStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortBigFile {

    private int amountMikroFiles = -1;
    private final int amountLineInMikroFiles;
    private final String nameMikroFile = "temp//mikro_file_";
    private final String pathBigFile;

    public SortBugFile(String pathBigFile, int amountLineInMikroFiles) {
        this.amountLineInMikroFiles = amountLineInMikroFiles;
        this.pathBigFile = pathBigFile;
        addNewDir();
    }

    private void addNewDir() {
        File myPath = new File("temp/");
        myPath.mkdirs();
    }

    //Разделяем файл на множество маленьких файлов
    public void mapBigFile() {

        BufferedReader reader = WorkingWithStream.openBufferedReaderStream(pathBigFile);

        try {

            List<String> lineList;
            while (reader.ready()) {
                amountMikroFiles++;
                lineList = readNLines(reader, amountLineInMikroFiles);
                sortList(lineList);
                saveListInFile(String.format("%s%d", nameMikroFile, amountMikroFiles), lineList);
            }

        } catch (IOException ex) {
            Logger.getLogger(SortBugFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        WorkingWithStream.closeFile(reader);
    }

    //Читаем N строк с большого файла
    private ArrayList<String> readNLines(BufferedReader reader, int amountLines) throws IOException {
        ArrayList<String> arrLine = new ArrayList<>();
        for (int i = 0; i < amountLines && reader.ready(); i++) {
            arrLine.add(reader.readLine());
        }
        return arrLine;
    }

    private void sortList(List<String> lineList) {
        Collections.sort(lineList, (str1, str2) -> {
            return str1.split(";")[0].compareTo(str2.split(";")[0]);
        });
    }

    private void saveListInFile(String pathFile, List<String> lineList) {

        BufferedWriter writer = WorkingWithStream.openBufferedWriterStream(pathFile);

        lineList.forEach((line) -> {
            try {
                writer.write(String.format("%s\n", line));
            } catch (IOException ex) {
                Logger.getLogger(SortBugFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        WorkingWithStream.closeFile(writer);
    }

    public void reduceInNewBigFile() {

        BufferedWriter writerBigFile = WorkingWithStream.openBufferedWriterStream("sort" + pathBigFile);
        ArrayList<BufferedReader> mikroFileList = new ArrayList<>();
        ArrayList<String> lineMikroFileList = readFirstLines(mikroFileList);
        
        saveInSortOrder(writerBigFile, mikroFileList, lineMikroFileList);
        
        WorkingWithStream.closeFile(writerBigFile);
    }

    private ArrayList<String> readFirstLines(ArrayList<BufferedReader> mikroFileList) {
        
        ArrayList<String> lineMikroFileList = new ArrayList<>();
        
        for (int i = 0; i < amountMikroFiles + 1; i++) {
            BufferedReader reader = WorkingWithStream.openBufferedReaderStream(String.format("%s%d", nameMikroFile, i));
            mikroFileList.add(reader);
            lineMikroFileList.add(getLine(reader));
        }
        
        return lineMikroFileList;
    }

    private void saveInSortOrder(BufferedWriter writerBigFile, ArrayList<BufferedReader> mikroFileList, ArrayList<String> lineMikroFileList) {
         while (mikroFileList.size() > 0) {
            int index = searchIndexForSmallestString(lineMikroFileList);
            WorkingWithStream.addLineInFile(writerBigFile, lineMikroFileList.get(index));
            String newLine = getLine(mikroFileList.get(index));
            if (newLine == null || newLine.equals("")) {
                lineMikroFileList.remove(index);
                WorkingWithStream.closeFile(mikroFileList.remove(index));
            } else {
                lineMikroFileList.set(index, newLine);
            }
        }
    }
    
    private int searchIndexForSmallestString(ArrayList<String> lineList) {
        int index = 0;
        for (int i = 1; i < lineList.size(); i++) {
            if (stringCompareTo(lineList.get(index), lineList.get(i)) > 0) {
                index = i;
            }
        }
        return index;
    }

    private static int stringCompareTo(String str1, String str2) {
        return str1.split(";")[0].compareTo(str2.split(";")[0]);
    }

    private String getLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SortBugFile.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
