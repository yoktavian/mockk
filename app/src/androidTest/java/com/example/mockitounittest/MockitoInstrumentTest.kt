package com.example.mockitounittest

import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(RobolectricTestRunner)
class MockitoInstrumentTest {

    lateinit var activity : MockitoActivity

    @Before
    fun setUp() {
//        activity = mockkClass(MockitoActivity::class, relaxed = true)
        activity = spyk(MockitoActivity())
    }

    @Test
    fun testSample() {
        every { activity.setSample(any()) } returns Unit
        every { activity.setText() } returns Unit
        activity.setSample("123")
//        verifySequence {
//            activity.setSample("123")
//            activity.setText()
//        }
        verify { activity.setSample("123") }
        verify { activity.setText() }
    }
}