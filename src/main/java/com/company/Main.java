package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    static final String BaseURI = "https://google-gruyere.appspot.com/606173267513807308367751952985584048412/";
    static final String username = "haidar";
    static final String password = "password";
    static final String messageUserSudahAda = "User already exists.";
    static final String messageUserUnik = "";
    static final String messageLoginGagal = "Invalid user name or password.";
    static String message;
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","E:\\Document\\vvpl\\chromedriver.exe");
        ChromeDriver driver=new ChromeDriver();

        //action membuka halaman
        driver.get(BaseURI);

        //action 1 mendaftar
        //sub action membuka laman daftar
        WebElement element=driver.findElement(By.xpath("//*[@id=\"menu-right\"]/a[2]"));
        element.click();
        //sub action memasukan username dan password
        driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[1]/td[2]/input")).sendKeys(username); //kolom username
        driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[2]/td[2]/input")).sendKeys(password); //kolom password
        driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[3]/td[2]/input")).click(); //tombol create account
        //sub action mengecek apakah user yg diinput sudah terdaftar atau belum
        message = driver.findElement(By.className("message")).getText();
        System.out.println(message);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //action 2 mendaftar dengan acount yang sudah pernah dibuat
        //sub action membuka laman daftar lagi
        element=driver.findElement(By.xpath("//*[@id=\"menu-right\"]/a[2]"));
        element.click();
        driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[1]/td[2]/input")).sendKeys(username); //kolom username
        driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[2]/td[2]/input")).sendKeys(password); //kolom password
        driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[3]/td[2]/input")).click(); //tombol create account
        //sub action mengecek apakah user yg diinput sudah terdaftar atau belum
        message = driver.findElement(By.className("message")).getText();
        //mengecheck apakah error message sesuai dengan yang ditampilkan
        System.out.println("Username sudah ada? "+ message.equals(messageUserSudahAda));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //action 3 sign in dengan password salah
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("pw")).sendKeys("passwordSalah");
        driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[3]/td[2]/input")).click();
        message = driver.findElement(By.className("message")).getText();
        System.out.println("Apakah login gagal? "+message.equals(messageLoginGagal));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //action 4 signin dengan credential yang valid
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("pw")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[3]/td[2]/input")).click();

        message = driver.findElement(By.className("menu-user")).getText();
        System.out.println("Berhasil login dengan akun "+message);

        //action 5 membuat snippet
        driver.findElement(By.linkText("New Snippet")).click();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        driver.findElement(By.name("snippet")).sendKeys("Snippet dikirim pada: "+format.format(date));
        driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr/td[2]/input")).click();




    }
}
