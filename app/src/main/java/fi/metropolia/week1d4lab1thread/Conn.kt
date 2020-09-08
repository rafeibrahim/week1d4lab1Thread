package fi.metropolia.week1d4lab1thread

import android.os.Handler
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStream

class Conn(mHand: Handler): Runnable {

    private val myHandler = mHand
    override fun run() {
        try {
            val myUrl = URL("https://www.randomtext.me/api/")
            val myConn = myUrl.openConnection()
                    as HttpURLConnection
            myConn.requestMethod = "GET"
            Log.d("THREADLAB", "LINE18CONN.KT")
            val istream: InputStream = myConn.inputStream
            Log.d("THREADLAB", "LINE21CONN.KT")
            val allText = istream.bufferedReader().use {
                it.readText()
            }
            Log.d("THREADLAB", "LINE24CONN.KT")
            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()
            Log.d("THREADLAB", str)
            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)
        } catch (e: Exception) {
            Log.d("THREADLAB", e.message!!)
        }
    }
}
