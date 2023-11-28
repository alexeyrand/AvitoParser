package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class AvitoParser {
    //@FindBy(xpath = "//*[contains(@type, 'sybmit')]")
    //WebElement loginField;
    WebDriver driver = new ChromeDriver();
    static String URL = "https://www.google.com/";

    void setup(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void find(){
    }

    void start(){
        driver.get(URL);
        By elementLocator = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]");
        WebElement element = driver.findElement(elementLocator);
        System.out.println(element.getAttribute("value"));
        element.click();
        //driver.close();
    }
}
