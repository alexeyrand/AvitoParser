package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.WebDriver;

public interface Parser {
    void start() throws InterruptedException;
    void setup();
    void openBrowser(String URL);
    void update();


}
