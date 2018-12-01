import com.team1091.shared.math.squareACircle
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

class InputMappingTest {

    @Test
    fun testInputSquaringAtZero() {
        val (x, y) = squareACircle(0.0, 0.0)
        assertEquals(0.0, x, 0.001)
        assertEquals(0.0, y, 0.001)
    }

    @Test
    fun testInputSquaringForward() {
        val (x, y) = squareACircle(0.0, 1.0)
        assertEquals(0.0, x, 0.001)
        assertEquals(1.0, y, 0.001)
    }

    @Test
    fun testInputSquaringBackwards() {
        val (x, y) = squareACircle(0.0, -1.0)
        assertEquals(0.0, x, 0.001)
        assertEquals(-1.0, y, 0.001)
    }

    @Test
    fun testInputSquaringRight() {
        val (x, y) = squareACircle(1.0, 0.0)
        assertEquals(1.0, x, 0.001)
        assertEquals(0.0, y, 0.001)
    }

    @Test
    fun testInputSquaringLeft() {
        val (x, y) = squareACircle(-1.0, 0.0)
        assertEquals(-1.0, x, 0.001)
        assertEquals(0.0, y, 0.001)
    }


    @Test
    fun testFortyFive() {
        val (x, y) = squareACircle(cos(toRadians(45.0)), sin(toRadians(45.0)))
        assertEquals(1.0, x, 0.001)
        assertEquals(1.0, y, 0.001)
    }


    @Test
    fun testOneThirtyFive() {
        val (x, y) = squareACircle(cos(toRadians(135.0)), sin(toRadians(45.0)))
        assertEquals(1.0, x, 0.001)
        assertEquals(-1.0, y, 0.001)
    }


    @Test
    fun testTwoTwoFive() {
        val (x, y) = squareACircle(cos(toRadians(225.0)), sin(toRadians(225.0)))
        assertEquals(-1.0, x, 0.001)
        assertEquals(-1.0, y, 0.001)
    }

    @Test
    fun testThreeThreeFive() {
        val (x, y) = squareACircle(cos(toRadians(335.0)), sin(toRadians(335.0)))
        assertEquals(-1.0, x, 0.001)
        assertEquals(1.0, y, 0.001)
    }

}