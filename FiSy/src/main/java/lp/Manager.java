package lp;

import lp.be.ExampleClass;
import lp.be.FileExamples;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Manager {
    private final FileExamples fileExamples = new FileExamples();
    private static final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private static final Logger log = loggerService.getLog();

    public Manager() {
        log.info("STARTED");
        try {
//            crudFile();
            loadingFiles();
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
//        try {
//            new ExampleClass();
//        } catch (URISyntaxException | IOException e) {
//            log.error(e);
//            for (StackTraceElement ste : e.getStackTrace()) {
//                log.error(ste);
//            }
//        }
    }

    private void loadingFiles() throws IOException {
//        fileExamples.loadTextFile();
//        fileExamples.loadPropertiesFile();
        fileExamples.loadCSVFile();
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
