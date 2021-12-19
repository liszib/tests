package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CitiesPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public CitiesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * метод для смены локации
     */
    public void changeLocation() {
        driver.get("https://www.citilink.ru/?action=changeCity&space=srt_cl:srtkhvalyn");
    }
}
