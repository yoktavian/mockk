package com.example.mockitounittest

import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class SpyUnitTesting {
    lateinit var activity : MockitoActivity

    @Before
    fun init() {
        activity = spyk(MockitoActivity())
    }

    @Test
    fun testingMethodCalled() {
        activity.setSample("123") // when this method called, setText method must been called.
        verify { activity.setText() } // verifiy method called at least once
    }

    @Test
    fun testingProperties() {
        activity.setSample("123") // method setSample called, and will set data from method param
        assert( activity.data == "123" ) // assert to verify that data is equals with param
    }

    /**
     * Testing sync method, called based on sequance of logic.
     */
    @Test
    fun testingSquence() {
        activity.setSample("123")
        /**
         * Sequence must be completed method.
         */
        verifySequence {
            activity.setSample(any())
            activity.setText()
            activity.setOther()
        }
    }

    @Test
    fun testingOrder() {
        activity.setSample("123")
        /**
         * Order allowing gaps in the sequence of calls.
         * Make sure that setOther method called after
         * setSample method called.
         */
        verifyOrder {
            activity.setSample(any())
            // activity.setText()
            activity.setOther()
        }
    }

    @After
    fun afterTest() {
        unmockkAll()
    }
}