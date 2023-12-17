package com.Alexey_rand.AvitoParser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.openqa.selenium.By.*;

/**
 * Класс, описывающий основную логику работы парсера. Вебдрайвер сканирует страницу,
 * достает список товаров. В методе start() каждому товару сопоставляется java объект класса Item,
 * после чего, информация отправляется в Дискорд.
 */
public class AvitoParser implements Parser {

    private static ChromeOptions options = new ChromeOptions();
    private static WebDriver driver = new ChromeDriver(options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir")));
    static HashSet<Item> items = new HashSet<>();
    DiscordWebhook webhook = new DiscordWebhook(MyConfig.webhook);

    /**
     * Метод, отвечающий за настройку вебдрайвера
     */
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    public void update() {
        driver.navigate().refresh();}

    public void openBrowser(String URL) {
        driver.get(URL);
    }

    public  void start() throws InterruptedException {
        // Массив, содержащий рекламные объявления
        Integer[] cc = new Integer[] {5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        ArrayList<Integer> ccc = new ArrayList<>(List.of(cc));
        int skip = 0;
        List<WebElement> selectors = driver.findElements(xpath("//div[@data-marker='item']"));
        for (WebElement e : selectors) {
            skip++;
            if (ccc.contains(skip)){
                continue;
            }
            Item item = new Item(e, webhook);
            Predicate<Item> evenNumbers = x -> items.contains(x);
            if (!evenNumbers.test(item) && Arrays.asList(MyConfig.DATE_LIST).contains(item.getDate())) {
                items.add(item);                //TODO: Реализовать выгрузку стека
                item.createEmbed();
                TimeUnit.SECONDS.sleep(2);
                try {
                    webhook.execute();
                }   catch (IOException IOE) {
                    throw new RuntimeException(IOE);

                }
                System.out.println("Отправлен в дискорд");
            }
            else{
                System.out.println("NO");
            }
            if (!Arrays.asList(MyConfig.DATE_LIST).contains(item.getDate()))
                break;
        }
        System.out.println(items.size());
        for (Item i : items){
            i.getInfo();
        }

        //driver.quit();
    }
}
