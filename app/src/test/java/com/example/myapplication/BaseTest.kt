package com.example.myapplication

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

open class BaseTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun before() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        clearAllMocks()
    }

    /**
     * Below is an example of test for the method that using launch with some dispatcher
     */
//    @Test
//    fun testSomeUI() = runBlocking { // or runBlockingTest
//        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
//            // ...
//        }
//    }

}