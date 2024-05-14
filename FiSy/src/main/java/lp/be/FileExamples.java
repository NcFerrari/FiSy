package lp.be;

import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExamples {

    private static final String SEPARATOR = "============================";
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

    public void possibleLoadings() throws IOException {
        File file = new File("sourceFiles/textFile.txt");
        log.info(getFileContentByFileReader(file));
        log.info(getFileContentByBufferedReaderCharByChar(file));
        log.info(getFileContentByBufferedReaderLineByLine(file));
        log.info(getFileContentByBufferedReaderLineByLineBetterSyntax(file));
        log.info(getFileContentByBufferedReaderLineByLineTheBestSyntax(file));
        log.info(getStringContentByInputStream("nějaký náhodný text, který\nje teď odřádkován"));
        log.info(getStringContentByInputStream(new FileInputStream(file)));
    }

    public void loadTextFile() throws IOException {
        log.info("LOADING TEXT FILE");
        String textFromFile;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sourceFiles/textFile.txt"))) {
            textFromFile = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        log.info(textFromFile);
        log.info(SEPARATOR);
    }

    public void loadPropertiesFile() {
        log.info("LOADING PROPERTIES FILE");
        log.info(SEPARATOR);
    }

    public void loadCSVFile() {
        log.info("LOADING CSV FILE");
        log.info(SEPARATOR);
    }

    public void loadXMLFile() {
        log.info("LOADING XML FILE");
        log.info(SEPARATOR);
    }

    public void loadJSONFile() {
        log.info("LOADING JSON FILE");
        log.info(SEPARATOR);
    }

    public void loadYAMLFile() {
        log.info("LOADING  FILE");
        log.info(SEPARATOR);
    }

    public void loadPDFFile() {
        log.info("LOADING PDF FILE");
        log.info(SEPARATOR);
    }

    public void useSQLFile() {
        log.info("USE SQL FILE");
        log.info(SEPARATOR);
    }

    public void loadWordFile() {
        log.info("LOADING WORD FILE");
        log.info(SEPARATOR);
    }

    public void loadExcelFile() {
        log.info("LOADING EXCEL FILE");
        log.info(SEPARATOR);
    }

    public void loadJPGFile() {
        log.info("LOADING JPEG");
        log.info(SEPARATOR);
    }

    public void loadPNGFile() {
        log.info("LOADING PNG");
        log.info(SEPARATOR);
    }

    /**
     * ZNAK PO ZNAKU
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private String getFileContentByFileReader(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Reader fileReader = new FileReader(file);
        int character;
        while ((character = fileReader.read()) > -1) {
            stringBuilder.append((char) character);
        }
        fileReader.close();
        return stringBuilder.toString();
    }

    private String getFileContentByBufferedReaderCharByChar(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int character;
        while ((character = bufferedReader.read()) > -1) {
            stringBuilder.append((char) character);
        }
        bufferedReader.close();
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * Řádek po řádku (znakově)
     *
     * @param file
     * @return
     */
    private String getFileContentByBufferedReaderLineByLine(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String getFileContentByBufferedReaderLineByLineBetterSyntax(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.lines().forEach(line -> stringBuilder.append(line).append("\n"));
        bufferedReader.close();
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * By source try-catch block the method close() is automatically called
     *
     * @param file
     * @return
     * @throws IOException
     */
    private String getFileContentByBufferedReaderLineByLineTheBestSyntax(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private String getStringContentByInputStream(String text) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes())))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private String getStringContentByInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
