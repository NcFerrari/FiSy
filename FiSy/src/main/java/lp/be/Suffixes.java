package lp.be;

import lombok.Getter;

@Getter
public enum Suffixes {

    PROPERTIES(".properties"),
    TXT(".txt"),
    CSV(".csv"),
    JAVA(".java"),
    JSON(".json"),
    XLSX(".xlsx"),
    PDF(".pdf"),
    PNG(".png"),
    JPEG(".jpeg"),
    SQL(".sql"),
    DOCX(".docx"),
    XML(".xml"),
    YAML(".yaml"),
    HTML(".html");

    private final String suffix;

    Suffixes(String suffix) {
        this.suffix = suffix;
    }

}
