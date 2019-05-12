package com.example.mockitounittest

import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class MockkUnitTesting {
    lateinit var activity: MockitoActivity
    lateinit var user: User
    lateinit var car: Car
//    var secondCar = mockk<Car> {
//        every { direction } returns Direction.NORTH
//    }

    /**
     * dont do like this. because before will excecute multiple time
     * when annotation test is called. so it will create and mock
     * multiple time too, it's need a time and it's so expensive.
     * just mock in properties is more than enough.
     */
    @Before
    fun init() {
        activity = mockk(relaxed = true)
        user = mockk()
        car = mockk(relaxed = true)
    }

    @Test
    fun testingMethodCalled() {
        every { activity.setSample("123") } returns Unit
        activity.setSample("123")
        verify { activity.setSample("123") }
    }

    @Test
    fun testingReturnValue() {
        val dumyUser = User(1, "Yuda")
        every { user.id } returns dumyUser.id
        every { user.name } returns dumyUser.name
        // calling
        user.name
        user.id
        // set assert
        assert(user.id == 1)
        assert(user.name == "Yuda")
        // verify
        verifyAll {
            user.name
            user.id
        }
        /**
         * to confirm that all about mocked user
         * have been verified.
         */
        confirmVerified(user)
    }

    @Test
    fun testingReturnValueReal() {
        val first = activity.getFirstData()
        val second = activity.getSecondData()
        every { activity.getFirstData() } returns first
        every { activity.getSecondData() } returns second
        activity.getFirstData()
        activity.getSecondData()

        assert(activity.myFirstData == activity.getFirstData())
        assert(activity.mySecondData == activity.getSecondData())
    }

    @Test
    fun testDrive() {
        every { car.drive(Direction.NORTH) } just Runs
        every { car.toSouth(Direction.SOUTH) } just Runs
        car.drive(Direction.NORTH)
        car.toSouth(Direction.SOUTH)
//        verify { car.drive(Direction.NORTH) }
//        verify { car.toSouth(Direction.SOUTH) }
        verifyAll {
            car.run {
                drive(Direction.NORTH)
                toSouth(Direction.SOUTH)
            }
        }
        /**
         * if toSouth inside verifyAll commented, so it will caused error.
         * because needs to verify all method called.
         */
        confirmVerified(car)
    }

    @Test
    fun testView() {
        every { activity.setText() } just Runs
        activity.setText()
        verify { activity.setText() }
    }

    @After
    fun afterTest() {
        unmockkAll()
    }
}