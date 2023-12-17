package com.Alexey_rand.AvitoParser;

public interface Parser {
    void start() throws InterruptedException;
    void setup();
    void openBrowser(String URL);
    void update();

}
