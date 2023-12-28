package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.Callable;

public class StartThread implements Runnable {

    String Url;
    Parser parser;
    WebDriver driver;
    StartThread(String Url, Parser parser) {
        this.Url = Url;
        this.parser = parser;
    }

    @Override
    public void run() {
        AvitoParser avitoParser = (AvitoParser) parser;
        driver = avitoParser.getDriver();
        avitoParser.setup();
        avitoParser.openBrowser(Url);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, 3500)");
        while (true) {
            try {
                avitoParser.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            avitoParser.update();

        }
    }
}
