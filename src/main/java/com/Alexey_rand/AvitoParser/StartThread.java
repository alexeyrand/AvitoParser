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
        System.out.println(Thread.currentThread().getName());
        parser.setup();
        parser.openBrowser(Url);

        try {
            parser.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        while (true) {
//            System.out.println("check2");
//            try {
//                System.out.println("check3");
//                parser.start();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Update");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            parser.update();                    // Обновление страницы
//        }

    }
}
