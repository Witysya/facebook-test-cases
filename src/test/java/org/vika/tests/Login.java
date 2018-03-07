package org.vika.tests;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vika.pages.LoginPage;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

public class Login {

    public static WebDriver driver;
    public static LoginPage loginPage;
    // private static String URL = " bla bla bla";
    @BeforeClass
    // треба додати файл драйвера до проекту і вписати нижче шлях до нього, бо щоб комусь заранити це йому потрібно
    // скачати драйвер з нету і прописати шлях
    // просто створи drivers папку в проекті кинь шлях до нього замість цього - src/test/java/drivers/chromedriver.exe
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Victory\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Before
    public void beforeEachTest() {
        // краще винисти урлу в окремий інстанс і передавти його в сигнатуру
        // driver.get(URL);
        driver.get("https://www.facebook.com");
    }

    @Test
    public void testFacebookLogin_validData() {
        /*
        1. Кожен тест має закінчуватись асертом
        2. набагато краще було б створити метод в loginPage класі який виконує повністю процес заповнення і т.д
        а сюди передавати лиши його виклик, тобто замість всіх цих викликів в тебе буде один - fillRegistrationFields();
        і можеш передавати в сигнатуру виклику параметрри які хочеш вписати в поля
         */
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("witysya@i.ua");
        loginPage.fillEmailfield2("witysya@i.ua");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();
        loginPage.submit();
    }

    @Test
    public void testFacebookLoginPage_emptyLastName() {
        loginPage.fillFirstname("Vika");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // це теж краще описати на loginPage і в окремий метод
        WebElement lastNameValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = lastNameValidationAlert.getText();

        // всі меседжі краще теж помістити наверх і передавати їх як стрінгу
        Assert.assertEquals("What’s your name?", expectedAlert);
    }

    @Test
    public void testFacebookLoginPage_emptyFirstName() {
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement firstNameValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = firstNameValidationAlert.getText();

        Assert.assertEquals("What’s your name?", expectedAlert);
    }

    @Test
    public void testFacebookLoginPage_invalidConfirmationEmail() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalodx@ugimail.net");

        loginPage.submit();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement confirmEmailValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = confirmEmailValidationAlert.getText();

        Assert.assertEquals("Your emails do not match. Please try again.", expectedAlert);
    }

    @Test
    public void testFacebookLoginPage_emptyEmail() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();
        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement emptyEmailValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = emptyEmailValidationAlert.getText();

        Assert.assertEquals("You'll use this when you log in and if you ever need to reset your password.", expectedAlert);
    }

    @Test
    public void testFacebookLoginPage_emptyPassword() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement passwordValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = passwordValidationAlert.getText();

        Assert.assertEquals("Enter a combination of at least six numbers, letters and punctuation marks (like ! and &).", expectedAlert);
    }

    @Test
    // не впевнений що це коректно спрацює, окільки ти обираєш селектои елемент за видимістю тексту, то на функції
    // selectByVisibleText() воно кине ексепсин
    public void testFacebookLogin_invalidDayInBirthday() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("Day");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement birthDayValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = birthDayValidationAlert.getText();

        Assert.assertEquals("Select your birthday. You can change who can see this later.", expectedAlert);
    }

    @Test
    public void testFacebookLogin_invalidMonthInBirthday() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("15");
        loginPage.birthMonth("Month");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement birthMonthValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = birthMonthValidationAlert.getText();

        Assert.assertEquals("Select your birthday. You can change who can see this later.", expectedAlert);
    }

    @Test
    public void testFacebookLogin_invalidYearInBirthday() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("15");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("Year");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement birthYearValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = birthYearValidationAlert.getText();

        Assert.assertEquals("Select your birthday. You can change who can see this later.", expectedAlert);
    }

    @Test
    public void testFacebookLogin_emptyRadioButton() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("15");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement radioButtonValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = radioButtonValidationAlert.getText();

        Assert.assertEquals("Please choose a gender. You can change who can see this later.", expectedAlert);
    }

    @Test
    public void testFacebookLogin_invalidNames() {
        loginPage.fillFirstname("1234");
        loginPage.fillLastname("1234");
        loginPage.fillEmailfield("nalox@ugimail.net");
        loginPage.fillEmailfield2("nalox@ugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement namesValidationAlert = driver.findElement(By.id ("reg_error_inner"));
        String expectedAlert = namesValidationAlert.getText();

        Assert.assertTrue("You have entered an invalid email. Please check your email address and try again.", expectedAlert.contains(expectedAlert));
    }

    @Test
    public void testFacebookLogin_invalidEmail() {
        loginPage.fillFirstname("Vika");
        loginPage.fillLastname("Horditsa");
        loginPage.fillEmailfield("naloxugimail.net");
        loginPage.fillPassword("15111995");
        loginPage.birthDay("28");
        loginPage.birthMonth("Nov");
        loginPage.birthYear("1995");
        loginPage.selectSexRadioButton();

        loginPage.submit();
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement emailValidationAlert = driver.findElement(By.cssSelector("._5633._5634._53ij"));
        String expectedAlert = emailValidationAlert.getText();

        Assert.assertEquals("Please enter a valid mobile number or email address.", expectedAlert);
    }

}