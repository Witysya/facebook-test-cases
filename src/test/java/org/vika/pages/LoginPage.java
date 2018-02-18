package org.vika.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

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

    public void birthDay(String day) {
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
