package com.Alexey_rand.AvitoParser;

import com.sun.jdi.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.MyConfig;

import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/** Класс, характеризующий конкретный товар. В классе формируется embed карточка для дискорда */
public class Item {
    WebDriver driver;
    WebElement selector;
    private String id;
    private String name;
    private String href;
    private String image;
    private String date;
    private String price;
    private String description;
    DiscordWebhook.EmbedObject embed;
    DiscordWebhook webhook;

    Item(WebElement selector, DiscordWebhook webhook) {
        this.selector = selector;
        this.name = selector.findElement(By.cssSelector("h3[itemprop ='name']")).getText();
        this.id = selector.getAttribute("id").substring(1);
        this.date = selector.findElement(By.cssSelector("p[data-marker='item-date']")).getText();
        this.href = selector.findElement(By.cssSelector("a[itemprop ='url']")).getAttribute("href");
        this.price = selector.findElement(By.cssSelector("meta[itemprop ='price']")).getAttribute("content");
        this.description = selector.findElement(By.cssSelector("div[class*=item-descriptionStep]")).getText();
        try {
            this.image = selector.findElement(By.cssSelector("img[itemprop='image']")).getAttribute("src");
        } catch (NoSuchElementException NSE) {
            this.image = null;
        }
        this.webhook = webhook;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void getInfo() {
        System.out.println("id: " + id + "\ndate: " + date + "\nprice: " + price + "\nname: " + name);
    }

    public void createEmbed(){
        System.out.println(this.image);
        GregorianCalendar calendar = new GregorianCalendar();
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setColor(MyConfig.color)
                .setTitle(this.name)
                .setDescription(this.description)
                .setUrl(this.href)
                .addField("**Цена**", this.price + " рублей", false)
                .addField("**Дата публикации**", "Сегодня в "
                        + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                        + calendar.get(Calendar.MINUTE), false);
        if (this.image != null)
            embed.setImage(this.image);
        webhook.setEmbed(embed);
        //System.out.println(href);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(this.id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
