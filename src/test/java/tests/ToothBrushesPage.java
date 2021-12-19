package tests;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ToothBrushesPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public ToothBrushesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setMinPriceTo(String minPriceStr) {
        WebElement minPrice = driver.findElements(By.xpath("//*[@data-meta-name='FilterPriceGroup__input-min']")).get(1);
        minPrice.clear();
        minPrice.sendKeys(minPriceStr);
    }

    public void setMaxPriceTo(String maxPriceStr) {
        WebElement maxPrice = driver.findElements(By.xpath("//*[@data-meta-name='FilterPriceGroup__input-max']")).get(1);
        maxPrice.clear();
        //TODO перенести Enter в отдельный метод
        maxPrice.sendKeys(maxPriceStr + "\r\n");
    }

    public Stream<WebElement> toothBrushes() {
        return driver.findElements(By.xpath("//*[@class = 'ProductPrice ProductPrice_default ProductCardVerticalPrice__price-current']/span/span[1]")).stream();
    }

    public void addLastElemToCart() {
        List<WebElement> result = driver.findElements(
                By.xpath("//*[@class = 'ProductCardVerticalLayout__wrapper-cart']/div")
        );

        result.get(0).click();

        driver.get("https://www.citilink.ru/order/");

        WebElement totalPrice = driver.findElement(
                By.xpath("//*[@class='OrderFinalPrice__price-block']/div[1]/span[1]/span[1]")
        );

        Integer totalPriceInt = Integer.parseInt(totalPrice.getText());

        Assert.assertTrue(totalPriceInt < 2000);
    }


}
