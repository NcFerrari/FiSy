package lp.be;

import lombok.Getter;

@Getter
public enum Suffixes {

    PROPERTIES(".properties"),
    TXT(".txt");

    private final String suffix;

    Suffixes(String suffix) {
        this.suffix = suffix;
    }

}
