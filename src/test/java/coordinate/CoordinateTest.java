package coordinate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoordinateTest {
    @Test
    void 좌표_정보는_괄호로_둘러쌓여있다() {
        String coordinateString = "(3, 2)";
        assertThat(isValidCoordinateString(coordinateString)).isTrue();
    }

    @Test
    void 좌표_정보는_쉼표로_구분된다() {
        String coordinateString = "(3, 2)";
        assertThat(isValidCoordinateString(coordinateString)).isTrue();
    }

    // X, Y좌표 모두 최대 24까지만 입력할 수 있다. (coordinate)

    @Test
    void 좌표값은_최대_24까지_입력할_수_있다() {
        String coordinateString1 = "(3,2)";
        isValidCoordinateString(coordinateString1);

        coordinateString1 = removeAllBrackets(coordinateString1);
        String[] coordinates = coordinateString1.split(",");

        for (String coordinate : coordinates) {
            int coordinateNum = Integer.parseInt(coordinate);
            assertThat(isValidRange(coordinateNum)).isTrue();
        }
    }

    @Test
    void 좌표값_범위를_초과하면_에러를_반환한다() {
        String coordinateString = "(25,26)";
        isValidCoordinateString(coordinateString);

        coordinateString = removeAllBrackets(coordinateString);
        String[] coordinates = coordinateString.split(",");

        for (String coordinate : coordinates) {
            int coordinateNum = Integer.parseInt(coordinate);
            assertThatThrownBy(() -> isValidRange(coordinateNum)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 좌표값을_두_개_입력한_경우_두_점을_잇는_직선으로_가정한다() {
        String coordinateString = "(0,3)-(5,7)";

        isValidCoordinateString(coordinateString);

        coordinateString = removeAllBrackets(coordinateString);
        Coordinates coordinates = new Coordinates(coordinateString);
        assertThat(coordinates.isStraight()).isTrue();
    }

    @Test
    void 직선인_경우_두_점_사이_거리를_계산해서_출력한다() {
        String coordinateString = "(0,3)-(5,7)";
        isValidCoordinateString(coordinateString);

        coordinateString = removeAllBrackets(coordinateString);
        Coordinates coordinates = new Coordinates(coordinateString);
        assertThat(coordinates.getDistance()).isEqualTo(41);
    }

    private boolean isValidRange(int coordinate) {
        if (coordinate >= 0 && coordinate <= 24) {
            return true;
        }
        throw new IllegalArgumentException("좌표값은 0 ~ 24 사이의 값이어야 합니다.");

    }

    private String removeAllBrackets(String string) {
        return string.replaceAll("[(]", "").replaceAll("[)]", "");
    }

    private boolean isValidCoordinateString(String coordinateString) {
        if (coordinateString.contains("(") && coordinateString.contains(")") && coordinateString.contains(",")) {
            return true;
        }

        throw new IllegalArgumentException("유효하지 않은 좌표값입니다.");
    }

}
