package com.jophasl.hacker_news

import android.util.Log
import com.jophasl.hacker_news.data.remote.HackerNewsApi
import com.jophasl.hacker_news.domain.api.RetrofitClient
import com.jophasl.hacker_news.data.remote.Story
import com.jophasl.hacker_news.data.remote.getBestStories
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testBestStories() {
        val retrofit = RetrofitClient.getClient()
        val hnApi = retrofit.create(HackerNewsApi::class.java)
        runBlocking {
            val tag = "UNIT TEST BEST STORIES"
            println("Gonna make the call")
            val stories = hnApi.getBestStories()
            println("Made the call")
            println(stories)
        }
    }

    @Test
    fun testRetrofit() {
        val retrofit = RetrofitClient.getClient()
        val hnApi = retrofit.create(HackerNewsApi::class.java)
        runBlocking {
            val story = hnApi.getStory(8863)
            assertEquals(
                story.body()?.toSubtype(),
                Story(
                    "dhouston",
                    71,
                    8863,
                    listOf(
                        9224,
                        8917,
                        8952,
                        8958,
                        8884,
                        8887,
                        8869,
                        8873,
                        8940,
                        8908,
                        9005,
                        9671,
                        9067,
                        9055,
                        8865,
                        8881,
                        8872,
                        8955,
                        10403,
                        8903,
                        8928,
                        9125,
                        8998,
                        8901,
                        8902,
                        8907,
                        8894,
                        8870,
                        8878,
                        8980,
                        8934,
                        8943,
                        8876
                    ),
                    104,
                    1175714200,
                    "My YC app: Dropbox - Throw away your USB drive",
                    "story",
                    "http://www.getdropbox.com/u/2/screencast.html"
                )
            )
        }
    }
}