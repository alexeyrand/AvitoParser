package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.Callable;

public class StartThread implements Runnable {

    String Url;
    Parser parser;
    StartThread(String Url, Parser parser) {
        this.Url = Url;
        this.parser = parser;
    }

    @Override
    public void run() {

        parser.setup();
        parser.openBrowser(Url);
        while (true) {
            try {
                parser.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            parser.update();

        }
    }
}
