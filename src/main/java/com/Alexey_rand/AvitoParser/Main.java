package com.Alexey_rand.AvitoParser;

//import club.minnced.discord.webhook.send.WebhookEmbed;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ParserFactory factory = new ParserFactory();


        Parser parser = factory.getParser(TypeParser.AVITO);
        StartThread startThread = new StartThread(MyConfig.URL2);
        Thread thread1 = new Thread(startThread);
        thread1.start();


        parser.setup();                         // Предварительные настройки драйвера
        parser.openBrowser(MyConfig.URL);       // Открытие браузера
        while (true) {
            parser.start();
            System.out.println("Update");
            parser.update();                    // Обновление страницы
        }
        //Runtime.getRuntime().exit(0);
    }
}
