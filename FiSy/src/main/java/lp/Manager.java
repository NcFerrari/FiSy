package lp;

import lp.be.FileExamples;

public class Manager {

    public Manager() {
        FileExamples fileExamples = new FileExamples();

        fileExamples.createSimpleFile();
        fileExamples.createDirectory();
        fileExamples.createDirectories();

        fileExamples.deleteSimpleFile();
        fileExamples.deleteDirectory();
        fileExamples.deleteDirectories();

        fileExamples.createFileByNIO();

        fileExamples.renameFile();
        fileExamples.renameFileByNIO();
    }

    public static void main(String[] args) {
        new Manager();
    }

}
