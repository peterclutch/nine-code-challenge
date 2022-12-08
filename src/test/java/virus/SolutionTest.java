package virus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

class SolutionTest {

    private final Solution solution = new Solution();

    private static final List<List<Character>> SIMPLE_VIRUS_GRID = List.of(
            List.of('0', '0', '0'),
            List.of('0', 'V', '0'),
            List.of('0', '0', '0')
    );

    private static final List<List<Character>> SIMPLE_SURROUNDED_GRID = List.of(
            List.of('0', 'A', '0'),
            List.of('A', 'V', 'A'),
            List.of('0', 'A', '0')
    );

    private static final List<List<Character>> LARGER_GRID = List.of(
            List.of('0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
            List.of('0', '0', '0', 'A', 'A', '0', '0', '0', '0', 'A', '0'),
            List.of('0', '0', 'A', 'V', 'V', 'A', '0', '0', 'A', 'V', 'A'),
            List.of('0', 'A', 'V', 'V', 'V', 'A', '0', '0', 'A', 'V', 'V'),
            List.of('0', '0', 'A', 'A', 'A', '0', 'A', '0', 'A', 'V', 'A'),
            List.of('0', '0', '0', '0', 'A', 'V', 'V', 'A', 'A', 'V', 'A'),
            List.of('0', '0', '0', '0', '0', 'A', 'A', '0', '0', 'A', '0')
    );

    @ParameterizedTest(name = "2D-grid must return {2} for coordinate {1}")
    @MethodSource("inputProvider")
    @DisplayName("isInfected")
    void isInfectedExpectedResult(List<List<Character>> grid, Solution.Coordinate coordinate, boolean expected) {
        Assertions.assertEquals(expected, solution.isInfected(lav(grid), coordinate));
    }

    private static Stream<Arguments> inputProvider() {
        return Stream.of(
                // NB. Y-aksen starter oppe fra og ned. X-aksen er venstre til højre.
                Arguments.of(SIMPLE_VIRUS_GRID, Solution.Coordinate.of(0,0), false),
                Arguments.of(SIMPLE_VIRUS_GRID, Solution.Coordinate.of(1,1), true),
                Arguments.of(SIMPLE_SURROUNDED_GRID, Solution.Coordinate.of(1, 1), false),
                Arguments.of(LARGER_GRID, Solution.Coordinate.of(2, 3), false),
                Arguments.of(LARGER_GRID, Solution.Coordinate.of(5, 5), true),
                Arguments.of(LARGER_GRID, Solution.Coordinate.of(9, 3), true)
        );
    }

    static Solution.Grid lav(List<List<Character>> grid) {
        return new Solution.Grid(grid.stream()
                .map(list -> list.stream()
                        .map(symbol -> Arrays.stream(Solution.Entity.values())
                                .filter(entity -> entity.symbol == symbol)
                                .findFirst()
                                .orElseThrow(RuntimeException::new)
                        ).toList()
                ).toList(), new HashSet<>()
        );
    }
}
