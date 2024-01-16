package com.Alexey_rand.AvitoParser;

//import club.minnced.discord.webhook.send.WebhookEmbed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ParserFactory factory = new ParserFactory();

        StartThread startThread1 = new StartThread(MyConfig.URL, factory.getParser(TypeParser.AVITO));
        StartThread startThread2 = new StartThread(MyConfig.URL2, factory.getParser(TypeParser.AVITO));

        Thread th1 = new Thread(startThread1);
        Thread th2 = new Thread(startThread2);
        th1.start();
        th2.start();

        //Runtime.getRuntime().exit(0);
    }
}
