package virus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Virus {

    public boolean isInfected(Grid grid, Coordinate coordinate) {
        // først og fremmest skal det relevante felt være en virus før det er relevant at tjekke naboer
        if (grid.at(coordinate) != Entity.VIRUS) {
            return false;
        }

        Set<Coordinate> checkedCoordinates = new HashSet<>();
        return isInfected(grid, coordinate, checkedCoordinates);
    }

    private boolean isInfected(Grid grid, Coordinate coordinate, Set<Coordinate> checkedCoordinates) {
        if (checkedCoordinates.contains(coordinate)) {
            // allerede tjekket uden at fejle
            return false;
        } else if (!grid.inbound(coordinate)) {
            // hvis virus er nabo til grænsen af koordinatsystemet må der være infektion
            return true;
        }

        checkedCoordinates.add(coordinate);

        return switch (grid.at(coordinate)) {
            case VIRUS -> coordinate.getNeighbors()
                        .stream()
                        .anyMatch(neighbor -> isInfected(grid, neighbor, checkedCoordinates));
            case ANTIVIRUS -> false;
            case EMPTY -> true;
        };
    }

    public enum Entity {
        VIRUS,
        ANTIVIRUS,
        EMPTY
    }

    public record Grid(List<List<Entity>> grid) {
        Entity at(Coordinate coordinate) {
            return grid.get(coordinate.y).get(coordinate.x);
        }

        boolean inbound(Coordinate coordinate) {
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
}

