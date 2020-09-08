package fi.metropolia.week1d4lab1thread

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isNetworkAvailable()) {
            Log.d("THREADLAB", "Network available")
            val myRunnable = Conn(mHandler)
            val myThread = Thread(myRunnable)
            myThread.start()
        } else {
            Log.d("THREADLAB", "Network not available")
        }
    }

    private val mHandler: Handler = object :
            Handler(Looper.getMainLooper()) {
        override fun handleMessage(inputMessage: Message) {
            if (inputMessage.what == 0) {
                txt_network.text = inputMessage.obj.toString()
            }
        }
    }

    private fun isNetworkAvailable(): Boolean = (this.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager).isDefaultNetworkActive
}