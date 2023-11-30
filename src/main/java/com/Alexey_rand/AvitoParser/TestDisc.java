package com.Alexey_rand.AvitoParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import resources.MyConfig;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class TestDisc {
    private static final  String WEBHOOK = "https://discord.com/api/webhooks/1131477469426360461/d82r_oF54wbZMxxbZ1vgoa1qoSPrkeNqTukM0P_FShHi3VgAQR1z-dRecXlNGTmX3Bus";


    public static void main(String[] args) throws IOException {

        DiscordWebhook webhook = new DiscordWebhook(WEBHOOK);
        webhook.setContent("Hello");
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(new Color(125, 50, 60))
                .setTitle("he")
                .setDescription("NewDescriptiontest"));



        //try {
        //    webhook.execute();
        //}   catch (IOException e) {
        //    throw new RuntimeException(e);
        //}


        ChromeOptions options = new ChromeOptions();
         WebDriver driver = new ChromeDriver(options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir")));
        String URL = MyConfig.URL;
         HashSet<Item> items = new HashSet<>();

        driver.get(URL);
        String[] ids = new String[60];

        int i_ids = 0;
        //String st = driver.getPageSource().;
        //System.out.println(st);
        List<WebElement> selectors = driver.findElements(xpath("//div[@data-marker='item']"));
        //driver.close();
        for (WebElement e : selectors) {
            Item item = new Item(e, webhook);
            if (!Arrays.asList(ids).contains(item.getId()) && Arrays.asList(MyConfig.dateList).contains(item.getDate())) {
                ids[i_ids] = item.getId();
                i_ids++;
                if (items.add(item))
                    System.out.println("Отправлен в дискорд");
            }
            else{
                System.out.println("NO");
            }
            System.out.println(Arrays.toString(ids));
        }



    }
}
