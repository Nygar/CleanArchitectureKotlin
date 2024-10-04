package com.nygar.common

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilCryptMD5UnitTest {
    @Test
    fun testGenerateHash() {
        val time: Long = 300
        val publicKey = "123456"
        val privateKey = "123456"

        assertEquals("c158d787f65cfe2f32900b7f56935252", UtilCryptMD5.generateHash(time, privateKey, publicKey))
    }
}
