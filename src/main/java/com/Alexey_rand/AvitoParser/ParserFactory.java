package com.Alexey_rand.AvitoParser;

public class ParserFactory {

    public Parser getParser(TypeParser type) {
        return switch (type) {
            case AVITO -> new AvitoParser();
        };
    }

}
