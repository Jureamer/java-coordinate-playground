package coordinate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

public class CoordinateTest {
    @Test
    void 좌표_정보는_괄호로_둘러쌓여있다() {
        String coordinateString = "(3,2)";
        assertThat(isValidCoordinateString(coordinateString)).isTrue();
    }

    @Test
    void 좌표_정보는_쉼표로_구분된다() {
        String coordinateString = "(3,2)";
        assertThat(isValidCoordinateString(coordinateString)).isTrue();
    }

    // X, Y좌표 모두 최대 24까지만 입력할 수 있다. (coordinate)

    @Test
    void 좌표값은_최대_24까지_입력할_수_있다() {
        String coordinateString1 = "(3,2)";
        isValidCoordinateString(coordinateString1);

        Coordinates coordinates = new Coordinates(coordinateString1);
        assertThat(coordinates).isInstanceOf(Coordinates.class);
    }

    @Test
    void 좌표값_범위를_초과하면_에러를_반환한다() {
        String coordinateString = "(25,26)";
        isValidCoordinateString(coordinateString);
        assertThatThrownBy(() -> new Coordinates(coordinateString)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 좌표값을_두_개_입력한_경우_두_점을_잇는_직선으로_가정한다() {
        String coordinateString = "(0,3)-(5,7)";

        isValidCoordinateString(coordinateString);

        Coordinates coordinates = new Coordinates(coordinateString);
        assertThat(coordinates.isStraight()).isTrue();
    }

    @Test
    void 직선인_경우_두_점_사이_거리를_계산해서_출력한다() {
        String coordinateString = "(0,3)-(5,7)";
        isValidCoordinateString(coordinateString);
        Coordinates coordinates = new Coordinates(coordinateString);
        assertThat(coordinates.getDistance()).isEqualTo(6.403, offset(0.00099));
    }

    @Test
    void 좌표값을_네_개_입력한_경우_사각형인지_검사한다() {
        String coordinateString = "(0,3)-(5,7)-(0,7)-(5,3)";

        isValidCoordinateString(coordinateString);
        Coordinates coordinates = new Coordinates(coordinateString);
        assertThat(coordinates.isRectangle()).isTrue();
    }

    @Test
    void 사각형_이라면_넓이를_출력한다() {
        String coordinateString = "(0,3)-(5,7)-(0,7)-(5,3)";

        isValidCoordinateString(coordinateString);
        Coordinates coordinates = new Coordinates(coordinateString);
        assertThat(coordinates.getRectangleArea()).isEqualTo(20);
    }


    private boolean isValidCoordinateString(String coordinateString) {
        if (coordinateString.contains("(") && coordinateString.contains(")") && coordinateString.contains(",")) {
            return true;
        }

        throw new IllegalArgumentException("유효하지 않은 좌표값입니다.");
    }

}
