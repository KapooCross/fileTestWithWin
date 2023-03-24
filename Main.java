package my.file;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileChecked fileChecked = new FileChecked();
        fileChecked.findAllFiles();
    }

}
