package com.Alexey_rand.AvitoParser;

//import club.minnced.discord.webhook.send.WebhookEmbed;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        AvitoParser parser = new AvitoParser();

        parser.setup();             // Предварительные настройки драйвера
        parser.openBrowser();       // Открытие браузера
        while (true) {
            parser.start();
            System.out.println("Update");
        }
        //Runtime.getRuntime().exit(0);
    }
}
