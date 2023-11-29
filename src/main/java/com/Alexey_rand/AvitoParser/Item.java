package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class Item {
    WebDriver driver;
    WebElement selector;
    private String id;
    private String name;
    private String href;
    private String image;
    private String date;
    private String price;


    Item(WebElement selector) {
        this.selector = selector;
        this.name = selector.findElement(By.cssSelector("h3[itemprop ='name']")).getText();
        this.id = selector.getAttribute("id").substring(1);
        this.date = selector.findElement(By.cssSelector("p[data-marker='item-date']")).getText();
        this.href = selector.findElement(By.cssSelector("a[itemprop ='url']")).getAttribute("baseURL");
        this.price = selector.findElement(By.cssSelector("meta[itemprop ='price']")).getAttribute("content");
        this.image = selector.findElement(By.cssSelector("img[itemprop='image']")).getAttribute("src");
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
