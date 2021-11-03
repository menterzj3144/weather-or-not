package com.example.myapplication

import org.junit.Test


/**
 * Tests for the API wrapper class
 */
class APITests {
    @Test
    fun test_api() {
        val api = APIWrapper()
        val weather = api.getWeatherInfo(lat, long)
    }
}