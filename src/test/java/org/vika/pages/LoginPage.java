package org.vika.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

    /*
    я б перемістив виклик PageFactory.initElements(driver, ClassName); в момент коли ця пейджа відкривається
    Наприклад - тобі потрібно з логін пейджі перейти на наступну, ти створюєш метод типу -
    public HomePage submitingLogin (){

    // тут описуєш дії які призводять до переходу на хом пейдж
    fillAllFieldsAndSubmit();

    // і повертаєш обєкт класу пейджі яка відкриється - тобто PageFactory.initElements(driver, ClassName) повертає по суті обєкт класу ClassName
    return PageFactory.initElements(driver, Homepage);
    }
        Також варто створити абстрактний клас який буде наслыдуватись усымома пейджами для того щоб там описати
        загальні методи які подібні для всіх, можеш там також переписати методи типу клік і т.д
     */
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // тут ти створюєш інстанс веб-драйверу а на вході в конструкторі створюєш інший.
    // суть в тому, що він має бути створений лише раз - в початковій точці - в тебе це login клас.
    // а далі просто передаєш його через конструктор між різними пейджами
    public WebDriver driver;

    // використовуй пошук за xpath чи сss це більш стабільно
    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "reg_email__")
    private WebElement phoneEmail;

    @FindBy(name = "reg_email_confirmation__")
    private WebElement phoneEmail2;

    @FindBy(name = "reg_passwd__")
    private WebElement newPassword;

    @FindBy(id = "u_0_b")
    private WebElement sex;

    @FindBy(id = "u_0_8")
    private WebElement sex2;

    @FindBy(name = "websubmit")
    private WebElement button;

    @FindBy(className = "_42ft _4jy0 _4jy4 _4jy1 selected _51sy")
    private WebElement button2;

    //inputtext _55r1
    //Methods
    // рекомендую називати метод відповыдно до того що він виконує - selectBirthDay чи якось так, але це вже як кому привичніше
    public void birthDay(String day) {
        // буде быльш читабельно якщо пошук 'birthday_day' винести до решти анотацій і передати сюди лише елемент
        Select stat = new Select(driver.findElement(By.name("birthday_day")));
        stat.selectByVisibleText(day);
    }

    public void birthMonth(String month) {
        Select stat = new Select(driver.findElement(By.name("birthday_month")));
        stat.selectByVisibleText(month);
    }

    public void birthYear(String year) {
        Select stat = new Select(driver.findElement(By.name("birthday_year")));
        stat.selectByVisibleText(year);
    }

    public void fillFirstname(String firstname) {
        firstName.sendKeys(firstname);
    }

    public void fillLastname(String lastname) {
        lastName.sendKeys(lastname);
    }

    public void fillEmailfield(String phoneemail) {
        phoneEmail.sendKeys(phoneemail);
    }

    public void fillEmailfield2(String phoneemail) {
        phoneEmail2.sendKeys(phoneemail);
    }

    public void fillPassword(String password) {
        newPassword.sendKeys(password);
    }

    public void selectSexRadioButton() {
        // на скыльки я зрозумыв ексепшин може впасти через те що виконання тесту проходить до того як елемент появиться на сторынці
        // тобто в catch можна пробувати ще раз викликати sex.click() і додати якийсь wait
        try {
            sex.click();
        } catch (ElementNotVisibleException e) {
            System.out.println("Sex radioButton with id=u_0_b is not visible.");
        }
        try {
            sex2.click();
        } catch (ElementNotVisibleException e) {
            System.out.println("Sex radioButton with id=u_0_8 is not visible.");
        }
    }

    public void submit() {
        button.click();
    }

}
