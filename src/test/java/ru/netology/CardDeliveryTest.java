package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeAll
    static void setupAll(){
        Configuration.browser = "chrome";
        Configuration.browserBinary = "/Applications/Google Chrome.app/Contents/Windows/Google Chrome";
    }
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void shouldTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Казань");
        String planingDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planingDate);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $("button").click();
        $(".notification__content").shouldBe(Condition.visible,
                Duration.ofSeconds(15)).shouldHave(Condition.exactText("Встреча успешно забронирована на"));

    }

}
