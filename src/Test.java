
//import hometest.singlethreaded.SortBigFile;
import hometest.multithreading.SortBigFile;


public class Test {
    
    public static void main(String[] args) {
        SortBigFile por = new SortBigFile("Test1.csv", 500000);
        por.mapBigFile();
        por.reduceInNewBigFile();
    }
}
