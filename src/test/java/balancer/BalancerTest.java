package balancer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class BalancerTest {

    @ParameterizedTest(name = "Input <{0}> gives expected output <{1}>")
    @MethodSource("inputProvider")
    @DisplayName("balance")
    void isInfectedExpectedResult(String input, List<String> expected) {
        List<String> actual = Balancer.balance(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> inputProvider() {
        return Stream.of(
                Arguments.of(")(", List.of("")),
                Arguments.of("()", List.of("()")),
                Arguments.of("())", List.of("()")),
                Arguments.of("())()", List.of("()()")),
                Arguments.of("()())", List.of("()()", "(())"))
        );
    }
}
