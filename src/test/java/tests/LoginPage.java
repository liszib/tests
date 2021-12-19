package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    /**
     * определение локатора кнопки входа в аккаунт
     */
    @FindBy(xpath = "//*[contains(text(), 'Войти')]/..")
    private WebElement loginBtn;

    /**
     * определение локатора поля ввода логина
     */
    @FindBy(xpath = "//*[contains(@class, 'InputBox__input js--InputBox__input  js--SignIn__login__container-input')]")
    private WebElement loginField;

    /**
     * определение локатора поля ввода пароля
     */
    @FindBy(xpath = "//*[contains(@class, 'InputBox__input js--InputBox__input  js--SignIn__password js--InputPassword InputPassword__container-input')]")
    private WebElement passwdField;

    /**
     *
     * @param login
     */
    @FindBy(xpath = "//*[contains(@class, 'SignIn__button js--SignIn__action_sign-in  Button  jsButton Button_theme_primary Button_size_m Button_full-width')]/..")
    private WebElement authBtn;
    /**
     * определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[contains(@class, 'HeaderUserName__name')]")
    private WebElement userMenu;

    @FindBy(xpath = "//*[contains(@class, 'js--CitiesSearch-trigger MainHeader__open-text TextWithIcon')]")
    private WebElement locationCtrl;


    /**
     * определение локатора кнопки входа в аккаунт
     */
    @FindBy(partialLinkText = "Мой профиль")
    private WebElement profileBtn;

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

    public void clickAuthBtn() {
        authBtn.click();
    }

    /**
     * метод для получения имени пользователя из меню пользователя
     */
    public String getUserName() {
        return userMenu.getText();
    }
    /**
     * метод для нажатия кнопки меню смены локации
     */
    public void locationCtrl() {
        locationCtrl.click();
    }

    /**
     * Переходим в профиль
     */
    public void profileBtn() {
        driver.get("https://www.citilink.ru/profile/");
    }

}
