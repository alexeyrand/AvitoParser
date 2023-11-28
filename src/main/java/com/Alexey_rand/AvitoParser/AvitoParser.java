package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AvitoParser {
    //@FindBy(xpath = "//*[contains(@type, 'sybmit')]")
    //WebElement loginField;
    WebDriver driver = new ChromeDriver();
    static String URL = "https://www.avito.ru/moskva/telefony/mobilnye_telefony/apple-ASgBAgICAkS0wA3OqzmwwQ2I_Dc?cd=1&s=104&user=1";

    void setup(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void find(){
    }

    void start(){
        driver.get(URL);
        List<WebElement> items = driver.findElements(By.className("items-items-kAJAg"));
        System.out.println(items.size());
        System.out.println(items.get(0));


        for (WebElement e : items){
            //System.out.println(e.getText());

            System.out.println(e.findElement(By.tagName("p")).getText());
        }
        ///element.click();
        driver.close();
    }
}
