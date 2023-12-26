package com.Alexey_rand.AvitoParser;

import java.util.concurrent.Callable;

public class StartThread implements Runnable {

    String Url;

    StartThread(String Url) {
        this.Url = Url;
    }

    @Override
    public void run() {

        ParserFactory factory = new ParserFactory();
        Parser parser = factory.getParser(TypeParser.AVITO);

        System.out.println("check1");
        parser.setup();                         // Предварительные настройки драйвера
        parser.openBrowser(Url);       // Открытие браузера
        while (true) {
            System.out.println("check2");
            try {
                System.out.println("check3");
                parser.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Update");
            parser.update();                    // Обновление страницы
        }

    }
}
