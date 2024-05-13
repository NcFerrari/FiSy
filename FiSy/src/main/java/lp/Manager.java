package lp;

import lp.be.FileExamples;

public class Manager {
    private final FileExamples fileExamples = new FileExamples();

    public Manager() {
//        crudFile();
        loadingFiles();
    }

    private void loadingFiles() {
        fileExamples.loadTextFile();
//        fileExamples.loadPropertiesFile();
//        fileExamples.loadCSVFile();
//        fileExamples.loadXMLFile();
//        fileExamples.loadJSONFile();
//        fileExamples.loadYAMLFile();
//        fileExamples.loadPDFFile();
//        fileExamples.useSQLFile();
//        fileExamples.loadWordFile();
//        fileExamples.loadExcelFile();
//        fileExamples.loadJPGFile();
//        fileExamples.loadPNGFile();
    }

    private void crudFile() {
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
