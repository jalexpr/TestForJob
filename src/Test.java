
import porechnyhometest.PorechnyHomeTest;


public class Test {
    
    public static void main(String[] args) {
        PorechnyHomeTest por = new PorechnyHomeTest("Test1.csv", 20);
        por.mapBigFile();
        por.reduceInNewBigFile();
    }
}
