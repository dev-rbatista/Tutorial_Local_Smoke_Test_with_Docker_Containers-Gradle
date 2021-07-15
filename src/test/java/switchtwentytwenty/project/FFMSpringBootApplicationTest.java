package switchtwentytwenty.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class FFMSpringBootApplicationTest {
// Test class added ONLY to cover main() invocation not covered by application tests.
    @Test
    void mainTest() {
        assertDoesNotThrow(() -> FFMSpringBootApplication.main(new String[]{}));
    }

}
