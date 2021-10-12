package cleancode.ui.util

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilMethodsTest {

    @Test
    fun getResponse() {
        val classToTest: UtilMethods = UtilMethods()
        assertEquals(true,classToTest.getResponse())
        //assertEquals(false,classToTest.getResponse())
    }
}