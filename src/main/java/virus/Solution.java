package virus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public boolean isInfected(Grid grid, Coordinate coordinate) {
        return grid.at(coordinate) == Entity.VIRUS && !coordinateSurroundedByAntivirus(grid, coordinate);
    }

    private boolean coordinateSurroundedByAntivirus(Grid grid, Coordinate coordinate) {
        if (grid.alreadyChecked(coordinate)) {
            return true;
        }
        grid.check(coordinate);

        return switch (grid.at(coordinate)) {
            case VIRUS -> neighborsSurroundedByAntivirus(grid, coordinate);
            case ANTIVIRUS -> true;
            case EMPTY -> false;
        };
    }

    private boolean neighborsSurroundedByAntivirus(Grid grid, Coordinate coordinate) {
        Set<Coordinate> neighbors = grid.getNeighbors(coordinate);
        return neighbors.size() == 4 && neighbors.stream().allMatch(neighbor -> coordinateSurroundedByAntivirus(grid, neighbor));
    }

    public record Grid(
            List<List<Entity>> grid,
            Set<Coordinate> checkedCoordinates
    ) {
        Entity at(Coordinate coordinate) {
            return grid.get(coordinate.y).get(coordinate.x);
        }

        boolean alreadyChecked(Coordinate coordinate) {
            return checkedCoordinates.contains(coordinate);
        }

        Set<Coordinate> getNeighbors(Coordinate coordinate) {
            return coordinate.getNeighbors().stream().filter(this::inbound).collect(Collectors.toSet());
        }

        void check(Coordinate coordinate) {
            checkedCoordinates.add(coordinate);
        }

        private boolean inbound(Coordinate coordinate) {
            return coordinate.y >= 0 && coordinate.y < grid.size()
                    && coordinate.x >= 0 && coordinate.x < grid.get(0).size();
        }
    }

    public record Coordinate(int x, int y) {
        static Coordinate of(int x, int y) {
            return new Coordinate(x, y);
        }

        Set<Coordinate> getNeighbors() {
            return Set.of(
                    Coordinate.of(x - 1, y),
                    Coordinate.of(x + 1, y),
                    Coordinate.of(x, y - 1),
                    Coordinate.of(x, y + 1)
            );
        }
    }

    public enum Entity {
        VIRUS('V'),
        ANTIVIRUS('A'),
        EMPTY('0');

        char symbol;

        Entity(char symbol) {
            this.symbol = symbol;
        }
    }
}

