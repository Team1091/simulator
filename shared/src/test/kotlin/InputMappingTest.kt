import com.team1091.shared.math.squareACircle
import org.junit.Assert.assertEquals
import org.junit.Test

class InputMappingTest {

    @Test
    fun testInputSquaringForward() {
        val (x, y) = squareACircle(0.0, 1.0)
        assertEquals(0.0, x, 0.001)
        assertEquals(1.0, y, 0.001)
    }

    @Test
    fun testInputSquaringRight() {
        val (x, y) = squareACircle(1.0, 0.0)
        assertEquals(1.0, x, 0.001)
        assertEquals(0.0, y, 0.001)
    }

    // TODO: we need to make this work
//    @Test
//    fun testFortyFive() {
//        val (x, y) = squareACircle(cos(toRadians(45.0)), sin(toRadians(45.0)))
//        assertEquals(1.0, x, 0.001)
//        assertEquals(1.0, y, 0.001)
//    }

}