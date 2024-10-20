package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTestNegative {
    public static String setLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }
    @Test
    void negativeTestV1() {
        String date = setLocalDate(5);
        open("http://localhost:9999");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Новиков Сергей");
        $("[data-test-id=phone] input").setValue("+79993455445");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }
    @Test
    void negativeTestV2() {
        String date = setLocalDate(4);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Токио");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Новиков Сергей");
        $("[data-test-id=phone] input").setValue("+79993456565");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }
    @Test
    void negativeTestV3() {
        String date = setLocalDate(4);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Sergei");
        $("[data-test-id=phone] input").setValue("+79993456565");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);
    }
    @Test
    void negativeTestV4() {
        String date = setLocalDate(1);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Новиков Сергей");
        $("[data-test-id=phone] input").setValue("+79993456565");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }
    @Test
    void negativeTestV5() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Новиков Сергей");
        $("[data-test-id=phone] input").setValue("+798989");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible);
    }






}