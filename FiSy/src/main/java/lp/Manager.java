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
        fileExamples.loadExcelFile();
        fileExamples.loadJPGFile();
        fileExamples.loadJSONFile();
        fileExamples.loadPDFFile();
        fileExamples.loadPNGFile();
        fileExamples.loadPropertiesFile();
        fileExamples.useSQLFile();
        fileExamples.loadCSVFile();
        fileExamples.loadWordFile();
        fileExamples.loadXMLFile();
        fileExamples.loadYAMLFile();
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
