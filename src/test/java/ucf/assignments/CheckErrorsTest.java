package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckErrorsTest {

    @Test
    void checkIf(String x) {
        CheckErrors c = new CheckErrors();
        boolean f = c.checkIf("$%#^");
        assertEquals(true, f);
    }
}