
//import hometest.singlethreaded.SortBugFile;
import hometest.multithreading.SortBugFile;


public class Test {
    
    public static void main(String[] args) {
        SortBugFile por = new SortBugFile("Test1.csv", 500000);
        por.mapBigFile();
        por.reduceInNewBigFile();
    }
}
