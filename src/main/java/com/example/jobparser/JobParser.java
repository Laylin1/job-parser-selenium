package com.example.jobparser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class JobParser {

    public void parseJobs() {
        System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("E:\\ChromeDriver\\chrome-win64\\chrome.exe");

        WebDriver driver = new ChromeDriver(options);

        try {
            System.out.println("🚀 Открываем rabota.md...");
            driver.get("https://www.rabota.md/ru/jobs-chisinau-Java");

            Thread.sleep(5000); // ждём загрузку страницы

            // Ищем карточки вакансий
            List<WebElement> vacancies = driver.findElements(By.cssSelector("div.vacancyCardItem"));

            System.out.println("🔍 Найдено вакансий: " + vacancies.size());

            for (WebElement vacancy : vacancies) {
                try {
                    WebElement linkElement = vacancy.findElement(By.cssSelector("a.vacancyShowPopup"));
                    String title = linkElement.getText().trim();
                    String link = linkElement.getAttribute("href");

                    System.out.println("💼 " + title);
                    TelegramSender.sendToTelegram("📌 " + title + "\n🔗 " + link);

                } catch (Exception e) {
                    System.out.println("⚠️ Проблема с вакансией: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка во время парсинга:");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
