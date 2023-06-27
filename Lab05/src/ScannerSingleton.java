import java.util.Scanner;

public class ScannerSingleton {
    private ScannerSingleton instance;
    public Scanner scanner;
    private ScannerSingleton() {
        this.scanner = new Scanner(System.in);
    }

    public ScannerSingleton getInstance() {
        if (this.instance == null) {
            this.instance = new ScannerSingleton();
        }
        return instance;
    }
}
//ScannerSingleton s = ScannerSingleton.getInstance();