package lp.be;

import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileExamples {

    private static final String CREATED_OUTPUT_TEXT = "{} successful created";
    private static final String DELETED_OUTPUT_TEXT = "{} successful created";
    private static final String FILE_NAME = "TestingFile";
    private static final String DIRECTORY_NAME = "TestingDir";
    private static final String DIRECTORIES = "a/b/c";
    private static final String ROOT_DIRECTORY = "a";
    private static final String NIO_FILE_PATH = "alfa/beta/textFile";
    private static final String RENAMED_FILE = "newName";
    private static final LoggerService loggerService = LoggerServiceImpl.getInstance(FileExamples.class);
    private static final Logger log = loggerService.getLog();

    /**
     * - File
     * - createNewFile
     */
    public void createSimpleFile() {
        File file = new File(FILE_NAME + Suffixes.PROPERTIES.getSuffix());
        try {
            if (file.createNewFile()) {
                log.info(CREATED_OUTPUT_TEXT, file.getName());
            }
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
    }

    /**
     * - File (without suffix)
     * - mkdir
     */
    public void createDirectory() {
        File dir = new File(DIRECTORY_NAME);
        if (dir.mkdir()) {
            log.info(CREATED_OUTPUT_TEXT, dir.getName());
        }
    }

    /**
     * - File (without suffix and more directories separated by slash (/) )
     * - mkdirs
     * note: mkdir vs mkdirs means that mkdir is for one directory. mkdirs makes all previous directories if they are missing
     */
    public void createDirectories() {
        File dirs = new File(DIRECTORIES);
        if (dirs.mkdirs()) {
            log.info(CREATED_OUTPUT_TEXT, DIRECTORIES);
        }
    }

    /**
     * file.delete() could be used, but it's bad way (example for that is in {@link #deleteDirectory()})
     */
    public void deleteSimpleFile() {
        File file = new File(FILE_NAME + Suffixes.PROPERTIES.getSuffix());
        try {
            Files.delete(Paths.get(file.getPath()));
            log.info(DELETED_OUTPUT_TEXT, file.getName());
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
    }

    /**
     * file.delete() could be used, but it's bad way (better way how to remove file/directory is in method {@link #deleteSimpleFile()})
     */
    public void deleteDirectory() {
        File file = new File(DIRECTORY_NAME);
        if (file.delete()) {
            log.info(DELETED_OUTPUT_TEXT, file.getName());
        }
    }

    /**
     * Delete method removes only empty folder, not folder with content. Its needed to remove all content recursively.
     */
    public void deleteDirectories() {
        Path path = Paths.get(ROOT_DIRECTORY);
        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                    .forEach(path1 -> {
                        try {
                            Files.delete(path1);
                        } catch (IOException e) {
                            log.error(e);
                            for (StackTraceElement ste : e.getStackTrace()) {
                                log.error(ste);
                            }
                        }
                    });
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
    }

    /**
     * The main difference between this method {@link #createSimpleFile()} is that File can create file in directory.
     * Files.createDirectories(path) can create file with path
     */
    public void createFileByNIO() {
        Path textFilePath = Paths.get(NIO_FILE_PATH + Suffixes.PROPERTIES.getSuffix());
        try {
            if (!Files.exists(textFilePath)) {
                Files.createDirectories(textFilePath.getParent());
                Files.createFile(textFilePath);
            }
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
    }

    public void renameFile() {
        createSimpleFile();
        File file = new File(FILE_NAME + Suffixes.PROPERTIES.getSuffix());
        if (file.renameTo(new File(RENAMED_FILE + Suffixes.PROPERTIES.getSuffix()))) {
            log.info("{} successful renamed into {}", FILE_NAME, RENAMED_FILE);
        }
        try {
            Files.delete(Paths.get(RENAMED_FILE + Suffixes.PROPERTIES.getSuffix()));
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
    }

    public void renameFileByNIO() {
        createSimpleFile();
        try {
            Files.move(Paths.get(FILE_NAME + Suffixes.PROPERTIES.getSuffix()), Paths.get(RENAMED_FILE + Suffixes.PROPERTIES.getSuffix()));
            log.info("{} successful renamed into {}", FILE_NAME, RENAMED_FILE);
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
        try {
            Files.delete(Paths.get(RENAMED_FILE + Suffixes.PROPERTIES.getSuffix()));
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
    }

    public void loadTextFile() {
        log.info("LOADING TEXT FILE");
        log.info("============================");
    }

    public void loadPropertiesFile() {
        log.info("LOADING PROPERTIES FILE");
        log.info("============================");
    }

    public void loadCSVFile() {
        log.info("LOADING CSV FILE");
        log.info("============================");
    }

    public void loadXMLFile() {
        log.info("LOADING XML FILE");
        log.info("============================");
    }

    public void loadJSONFile() {
        log.info("LOADING JSON FILE");
        log.info("============================");
    }

    public void loadYAMLFile() {
        log.info("LOADING  FILE");
        log.info("============================");
    }

    public void loadPDFFile() {
        log.info("LOADING PDF FILE");
        log.info("============================");
    }

    public void useSQLFile() {
        log.info("USE SQL FILE");
        log.info("============================");
    }

    public void loadWordFile() {
        log.info("LOADING WORD FILE");
        log.info("============================");
    }

    public void loadExcelFile() {
        log.info("LOADING EXCEL FILE");
        log.info("============================");
    }

    public void loadJPGFile() {
        log.info("LOADING JPEG");
        log.info("============================");
    }

    public void loadPNGFile() {
        log.info("LOADING PNG");
        log.info("============================");
    }
}
