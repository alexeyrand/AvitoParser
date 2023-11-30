package com.Alexey_rand.AvitoParser;

import resources.MyConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.*;

public class AvitoParser {

    static ChromeOptions options = new ChromeOptions();
    static WebDriver driver = new ChromeDriver(options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir")));
    String URL = MyConfig.URL;
    static HashSet<Item> items = new HashSet<>();
    DiscordWebhook webhook = new DiscordWebhook(MyConfig.webhook);

    void setup(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    void start(){
        driver.get(URL);
        //String[] ids = new String[60];

        int i_ids = 0;
        //String st = driver.getPageSource();
        List<WebElement> selectors = driver.findElements(xpath("//div[@data-marker='item']"));
        for (WebElement e : selectors) {
            Item item = new Item(e, webhook);
            if (items.add(item) && Arrays.asList(MyConfig.dateList).contains(item.getDate())) {
                //ids[i_ids] = item.getId();
                //i_ids++;
                if (items.add(item))
                    System.out.println("Отправлен в дискорд");
            }
            else{
                System.out.println("NO");
            }
            if (!Arrays.asList(MyConfig.dateList).contains(item.getDate()))
                break;
            //System.out.println(Arrays.toString(ids));
        }
        System.out.println(items.size());
        for (Item i : items){
            i.getInfo();
        }



        ///element.click();
        //driver.close();
        driver.quit();
    }
}
