import com.team1091.shared.math.squareACircle
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.toRadians

class InputMappingTest {

    @Test
    fun testInputSquaringForward() {
        val (x, y) = squareACircle(0.0, 1.0)
        assertEquals(0.0, x)
        assertEquals(1.0, y)
    }

    @Test
    fun testInputSquaringRight() {
        val (x, y) = squareACircle(1.0, 0.0)
        assertEquals(1.0, x)
        assertEquals(0.0, y)
    }

    @Test
    fun testFortyFive() {
        val (x, y) = squareACircle(cos(toRadians(45.0)), sin(toRadians(45.0)))
        assertEquals(1.0, x)
        assertEquals(1.0, y)
    }

}