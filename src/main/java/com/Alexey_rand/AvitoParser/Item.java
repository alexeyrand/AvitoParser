package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/** Класс, характеризующий конкретный товар.  */
public class Item {
    WebDriver driver;
    WebElement selector;
    private final String id;
    private final String name;
    private final String href;
    private String image;
    private final String date;
    private final String price;
    private final String description;
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
            Thread.sleep(10000);
            this.image = selector.findElement(By.cssSelector("img[itemprop='image']")).getAttribute("src");
            Thread.sleep(10000);
        } catch (Exception NSE) {
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

    /**
     * В методе формируется embed карточка для дискорда
     */
    public void createEmbed(){
        System.out.println(this.image);
        GregorianCalendar calendar = new GregorianCalendar();
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setColor(MyConfig.COLOR)
                .setTitle(this.name)
                .setDescription(this.description)
                .setUrl(this.href)
                .addField("**Цена**", this.price + " рублей", false)
                .addField("**Дата публикации**", "Сегодня в "
                        + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                        + calendar.get(Calendar.MINUTE), false);
        if (this.image != null)
            embed.setImage(this.image);
        else {
            embed.setImage("https://ibb.co/gjQCLR8");
        }
        webhook.setEmbed(embed);
        //System.out.println(href);


        //TODO: correct create images
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                '}';
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
