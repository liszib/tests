package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class LoginTest {
    static WebDriver driver;
    static LoginPage loginPage;
    static CitiesPage citisPage;
    static ProfilePage profilePage;
    static MainPage mainPage;

    @BeforeClass
    public static void before() {
        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        citisPage = new CitiesPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.citilink.ru");
    }

    @Test
    public void testLogin() throws Exception {
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим логин
        loginPage.inputLogin(Props.getProperty("login"));
        //вводим пароль
        loginPage.inputPasswd(Props.getProperty("password"));
        //у нас есть 30сек чтобы вбить капчу
        Thread.sleep(30000L);
        //нажимаем кнопку входа
        loginPage.clickAuthBtn();
        //получаем отображаемый логин
        String user = loginPage.getUserName();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(Props.getProperty("username"), user);
    }

    @Test
    public void locationTest() {
        //открываем список городов
        loginPage.locationCtrl();
        //меняем город нахождения
        citisPage.changeLocation();
        //заходим в профиль клиента
        loginPage.profileBtn();
        profilePage = new ProfilePage(driver);
        //jnkbx
        Assert.assertEquals(Props.getProperty("city"), profilePage.getCity());
    }

    @Test
    public void cartTest() throws Exception {
        driver.get("https://www.citilink.ru");
        mainPage = new MainPage(driver);
        mainPage.enterToothBrush();

        ToothBrushesPage toothBrushesPage = new ToothBrushesPage(driver);
        toothBrushesPage.setMinPriceTo("999");
        toothBrushesPage.setMaxPriceTo("1999");

        //ждем прогрузки страницы
        Thread.sleep(10000);

        final AtomicInteger counter = new AtomicInteger(0);
        toothBrushesPage.toothBrushes().forEach(
                webElement -> {
                    String priceStr = webElement.getText();
                    if (!priceStr.isEmpty()) {
                        int currentPrice = Integer.parseInt(webElement.getText());
                        System.out.println("currentPrice=" + currentPrice);
                        counter.incrementAndGet();
                        Assert.assertTrue(currentPrice >= 999 && currentPrice <= 1999);
                    }
                }
        );
        System.out.println("total toothbrushes (with a price): " + counter.get());
        //Assert.assertEquals(35, counter.get());

        toothBrushesPage.addLastElemToCart();

    }

    @AfterClass
    public static void clear() {
        //profilePage.entryMenu();
        //driver.quit();
    }
}
