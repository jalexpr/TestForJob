
package hometest;

import hometest.singlethreaded.SortBugFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

//класс для работы с потоком
public class WorkingWithStream {
            
    public static BufferedReader openBufferedReaderStream(String pathFile) {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile)));
        } catch (FileNotFoundException ex) {
            String messages = String.format("Ошибка при чтении файла.\r\nПроверте наличие %s\r\n", pathFile);
            Logger.getLogger(WorkingWithStream.class.getName()).log(Level.SEVERE, messages, ex);
        }

        return bufferedReader;
    }
    
    public static BufferedWriter openBufferedWriterStream(String pathFile) {

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathFile)));
        } catch (FileNotFoundException ex) {
            String messages = String.format("Ошибка при чтении файла.\r\nПроверте наличие %s\r\n", pathFile);
            Logger.getLogger(WorkingWithStream.class.getName()).log(Level.SEVERE, messages, ex);
        }

        return bufferedWriter;
    }
    
    public static void addLineInFile(BufferedWriter writer, String line) {
        try {
            writer.write(String.format("%s\n", line));
        } catch (IOException ex) {
            Logger.getLogger(SortBugFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeFile(Reader reader) {
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(WorkingWithStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeFile(Writer writer) {
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(WorkingWithStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
