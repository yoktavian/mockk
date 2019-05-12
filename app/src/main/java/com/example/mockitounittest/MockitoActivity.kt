package com.example.mockitounittest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MockitoActivity : AppCompatActivity() {

    var data: String = ""
    var myFirstData = "first Data"
    var mySecondData = "second Data"
    private val TAG = "<= Mockk Tag =>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setSample(sample: String) {
        data = sample
        setText()
    }

    fun setText() {
        setOther()
    }

    fun setOther() {
    }

    fun getFirstData() : String {
        return myFirstData
    }

    fun getSecondData() : String {
        return myFirstData
    }
}