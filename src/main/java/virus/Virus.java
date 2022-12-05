package virus;

import java.util.List;

public class Virus {

    public boolean isInfected(List<List<Character>> grid, Coordinate coordinate) {
        // TODO implement
        return false;
    }

    public record Coordinate(int x, int y) {
        static Coordinate of(int x, int y) {
            return new Coordinate(x, y);
        }
    }
}

