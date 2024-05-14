package lp.be;

import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

public class ExampleClass {

    private static final LoggerService loggerService = LoggerServiceImpl.getInstance(ExampleClass.class);
    private static final Logger log = loggerService.getLog();

    private String textInString = "nějaký text, klidně i s diakritikou\na dalším řádkem :)";
    private File file = new File("sourceFiles/textFile.txt");
    private URI uri = new URI("https://cs.wikipedia.org/wiki/Text");
    private URL url = uri.toURL();
    private File pikachu = new File("sourceFiles/pikachu.png");
    private InputStream inputStream;

    public ExampleClass() throws URISyntaxException, IOException {
        log.info(textInString);
        log.info(file);
        //a je problém, když budeme mít 2 rozdílné vstupy

        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(textInString.getBytes())));
        log.info(br.lines().collect(Collectors.joining(System.lineSeparator())));
//        jiný zápis
//        BufferedReader br2 = new BufferedReader(new StringReader(textInString));
//        log.info(br2.lines().collect(Collectors.joining(System.lineSeparator())));
        br = new BufferedReader(new FileReader(file));
        log.info(br.lines().collect(Collectors.joining(System.lineSeparator())));

        FileInputStream fis = new FileInputStream(pikachu);

//        ImageIcon imageIcon = new ImageIcon(fis.readAllBytes());
//        JFrame frame = new JFrame();
//        frame.setSize(300, 300);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        JPanel panel = new JPanel();
//        frame.add(panel);
//        frame.setIconImage(imageIcon.getImage());

//        br = new BufferedReader(new InputStreamReader(url.openStream()));
//        String inputLine;
//        while ((inputLine = br.readLine()) != null)
//            System.out.println(inputLine);
//        br.close();
    }
}
