package com.github.Chestaci.pages.sql;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Объект главной страницы SQL
 */
public class SQLMainPage extends Page {

    /**
     * Определение локатора поля ввода Login
     */
    @FindBy(css = "input[type=text][name=login]")
    public WebElement loginField;

    /**
     * Определение локатора поля ввода Password
     */
    @FindBy(css = "input[type=password][name=psw]")
    private WebElement passwordField;

    /**
     * Определение локатора поля кнопки авторизации
     */
    @FindBy(css = "input[type=submit][name=subm1]")
    private WebElement loginButton;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public SQLMainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для ввода
     *
     * @param login Логин
     */
    private void inputLogin(String login) {
        wait.until(ExpectedConditions.visibilityOfAllElements(loginField));
        loginField.sendKeys(login);
    }

    /**
     * Метод для ввода
     *
     * @param password Пароль
     */
    private void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfAllElements(passwordField));
        passwordField.sendKeys(password);
    }

    /**
     * Метод для осуществления действий по заполнению полей формы параметрами
     *
     * @param login    Login
     * @param password Пароль
     */
    private void fillFields(String login, String password) {
        inputLogin(login);
        inputPassword(password);
    }

    /**
     * Метод для осуществления нажатия на кнопку авторизации
     *
     * @return домашнюю страницу
     * @see SQLHomePage
     */
    private SQLHomePage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new SQLHomePage(this.driver);
    }

    /**
     * Метод для авторизации с помощью логина и пароля
     *
     * @param login    Логин
     * @param password Пароль
     * @return домашнюю страницу пользователя
     * @see SQLHomePage
     */
    @Step("Авторизация пользователя с использованием для входа логина {login} и пароля {password}")
    public SQLHomePage authWithLoginAndPassword(String login, String password) {
        fillFields(login, password);
        SQLHomePage sqlHomePage = clickLoginButton();
        Path file = Paths.get("Cookies.data");
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file)) {
            // loop for getting the cookie information
            for (Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write((cookie.getName() + ";"
                        + cookie.getValue() + ";"
                        + cookie.getDomain() + ";"
                        + cookie.getPath() + ";"
                        + cookie.getExpiry() + ";"
                        + cookie.isSecure()));
                bufferedWriter.newLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sqlHomePage;
    }

    /**
     * Метод для авторизации с помощью cookies
     *
     * @return домашнюю страницу пользователя
     * @see SQLHomePage
     */
    @Step("Авторизация пользователя с использованием сохранённых cookies для входа")
    public SQLHomePage authWithCookies() {
        driver.manage().deleteCookieNamed("PHPSESSID");
        Path file = Paths.get("Cookies.data");
        try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
            String strline;
            while ((strline = bufferedReader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(strline, ";");
                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if (!(val = token.nextToken()).equals("null")) {
                        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                        expiry = formatter.parse(val);
                    }
                    boolean isSecure = Boolean.parseBoolean(token.nextToken());
                    Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                    driver.manage().addCookie(cookie); // This will add the stored cookie to your current session
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        driver.navigate().refresh();
        return new SQLHomePage(driver);
    }

    /**
     * Метод перевода фокуса с элемента
     *
     * @param element Элемент
     */
    @Step("Перевод фокуса с эелемента")
    public void blur(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", element);
    }

    /**
     * Метод проверки наличия горизонтального скролла на странице
     *
     * @return true, если горизонтальный скролл есть на странице,
     * иначе - false
     */
    @Step("Проверка наличия горизонтального скролла на странице")
    public boolean checkHorizontalScrollStatus() {
        return (boolean) ((JavascriptExecutor) driver)
                .executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
    }

    /**
     * Метод проверки наличия вертикального скролла на странице
     *
     * @return true, если вертикальный скролл есть на странице,
     * иначе - false
     */
    @Step("Проверка наличия вертикального скролла на странице")
    public boolean checkVerticalScrollStatus() {
        return (boolean) ((JavascriptExecutor) driver)
                .executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
    }
}

