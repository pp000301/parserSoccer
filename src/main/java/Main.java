import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.opera.OperaDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
//        try {
//            Document document= Jsoup.connect("https://www.livescore.com").get();
//            String title= document.title();
//            Elements element= document.getElementsByTag("body");
//            System.out.println(document);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.setProperty("webdriver.gecko.driver", "f:\\Idea Progects\\parserSoccer\\driver\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "f:\\Idea Progects\\parserSoccer\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.livescore.com/soccer/2020-03-12/");
//        String page= driver.getPageSource();
//        System.out.println(page);
//        WebElement webElement= driver.findElement(By.className("even"));
//        System.out.println(webElement.getAttribute("href"));

        List<WebElement> webElement = driver.findElements(By.cssSelector("a.scorelink"));
        List<String> links = new ArrayList<>();
        webElement.stream().forEach(webElement1 -> {
            links.add(webElement1.getAttribute("href"));
        });// Получаем ссылки для парсинга 2 последних пустые!!!!


        driver.get(links.get(4));// переход по ссылке для парсинга


        List<String> minutes = new ArrayList<>();
        List<WebElement> minutesEvent = driver.findElements(By.className("min"));
        minutesEvent.forEach(event -> {
            if (!event.getText().equals(""))
                minutes.add(event.getText());
        });
        System.out.println("Количество событий "+ minutes.size());

        minutes.forEach(System.out::println);// Результат парсинга столбца с минутами!


        //Парсинг событий хозяев
        List<WebElement> matchEvents = driver.findElements(By.xpath("//div[@data-type='home']"));
      //  System.out.println(matchEvents.size());

        //Парсинг среднего столбца
        List<WebElement> matchEvents1 = driver.findElements(By.xpath("//div[@data-type='middle']"));
     //   System.out.println(matchEvents1.size());

        //Парсинг событий гостей
        List<WebElement> matchEvents2 = driver.findElements(By.xpath("//div[@data-type='away']"));
      //  System.out.println(matchEvents2.size());


        System.out.println("События по столбцу хозяев :");
        matchEvents.forEach(matchEvent -> {
            if (matchEvent.getText().equals("")|matchEvent.getText().equals(" "))
                System.out.println("...");
            else
                System.out.println(matchEvent.getText());

        });

        System.out.println("События по среднему столбцу :");
        matchEvents1.forEach(matchEvent -> {
            if (matchEvent.getText().equals(" ")|matchEvent.getText().equals(""))
                System.out.println("...");
            else
                System.out.println(matchEvent.getText());

        });

        System.out.println("События по  столбцу гостей :");
        matchEvents2.forEach(matchEvent -> {
            if (matchEvent.getText().equals(" ")|matchEvent.getText().equals(""))
                System.out.println("...");
            else
                System.out.println(matchEvent.getText());

        });

//
//        if (minutes.get(1).equals(""))
//            System.out.println("true");

        driver.quit();
    }
}
