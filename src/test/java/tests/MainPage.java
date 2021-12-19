package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//*[@class='MainHeader__search']/div/div/form/div/div/div/label/input")
    WebElement searchProduct;

    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void enterToothBrush() {
        searchProduct.sendKeys("Электрические зубные щетки\r\n");
    }
}
