package com.Alexey_rand.AvitoParser;

public class ParserFactory {
    public Parser getParser(TypeParser type) {
        Parser parser = null;

        switch (type) {
            case AVITO:
                parser = new AvitoParser();
                break;

        }
        return parser;
    }

}
