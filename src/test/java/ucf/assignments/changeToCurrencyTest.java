package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class changeToCurrencyTest {

    @Test
    void changeToCurrency() {
        changeToCurrency c = new changeToCurrency();
        String firstCurrency = "123.36";
        assertEquals("$123.36", c.changeToCurrency(firstCurrency));
    }
}