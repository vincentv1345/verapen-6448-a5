package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckErrorsTest {
    CheckErrors c = new CheckErrors();
    @Test
    void checkIf() {

        boolean f = c.checkIf("$%#^");
        assertEquals(true, f);
    }

    @Test
    void checkSerialLen() {
        String x = "1234567";
        String y = "1234567890";
        String z = "12345678901";
        boolean f = c.checkSerialLen(x);
        boolean g= c.checkSerialLen(y);
        boolean h = c.checkSerialLen(z);
        assertEquals(true, f);
        assertEquals(false, g);
        assertEquals(true, h);
    }

    @Test
    void checkNameLen() {
    }
}