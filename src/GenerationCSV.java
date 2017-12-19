
import java.io.BufferedWriter;
import porechnyhometest.WorkingWithStream;


public class GenerationCSV {
    
    public static void GenerationCSV(int count) {
        BufferedWriter writer = WorkingWithStream.openBufferedWriterStream("Test1.csv");
        for(int i = 0; i < count; i++) {
            WorkingWithStream.addLineInFile(writer, String.format("%d;%d%s;%d", (int) (Math.random() * 100000), (int) (Math.random() * 100000), "ffff", (int) (Math.random() * 100000)));
        }
        WorkingWithStream.closeFile(writer);
    }
    
    public static void main(String[] args) {
        GenerationCSV.GenerationCSV(15000);
    }
}
