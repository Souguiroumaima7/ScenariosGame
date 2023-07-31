package com.example.scenariosgames.CreationGame

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scenariosgames.R

import java.util.*
import java.util.concurrent.TimeUnit
class Timer(): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}
class HomeViewModel : ViewModel() {
    private var timeCountInMilliSeconds = 1 * 60000.toLong()
    val tokenMaxTimer = 1 * 60 * 1000.toLong()

    //  private var email: String
    private enum class TimerStatus {
        STARTED, STOPPED
    }

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _currentTimeString = MutableLiveData<String>()
    val currentTimeString: LiveData<String>
        get() = _currentTimeString


    private val _tokenString = MutableLiveData<String>()
    val tokenString: LiveData<String>
        get() = _tokenString


    private var timerStatus = TimerStatus.STOPPED

    private var countDownTimer: CountDownTimer? = null


    /**
     * method to start count down timer
     */
    private fun startCountDownTimer() {
        countDownTimer = object : CountDownTimer(timeCountInMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTimeString.value = hmsTimeFormatter(millisUntilFinished)
                _currentTime.value = millisUntilFinished
            }

            override fun onFinish() {
                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED
                // doGetToken()
                generateRand()
            }
        }.start()
    }


    fun startStop() {
        if (timerStatus === TimerStatus.STOPPED) {
            // call to initialize the timer values
            setTimerValues()
            // call to initialize the progress bar values
            // showing the reset icon
            // changing the timer status to started
            timerStatus = TimerStatus.STARTED
            // call to start the count down timer
            startCountDownTimer()
        } else {
            // changing the timer status to stopped
            timerStatus = TimerStatus.STOPPED
            stopCountDownTimer()
        }
    }

    private fun setTimerValues() {
        val time = 1   //Time time in minutes
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000.toLong()
    }


    private fun stopCountDownTimer() {
        countDownTimer!!.cancel()
    }

    init {
        //email = PreferencesUtils().getPrefEmail(context).toString()
        //val deviceID = getDeviceId(context)
        generateRand()
    }

    private fun hmsTimeFormatter(milliSeconds: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(milliSeconds),
            TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    milliSeconds
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    milliSeconds
                )
            )
        )
    }

    private fun generateRand(){
        val min = 100000
        val max = 900000
        val rnd = Random(System.nanoTime())
        _tokenString.value=( min + rnd.nextInt(max)).toString()
        Timber.e("TAG generateRand:  %s", _tokenString.value!!)
    }

    fun refreshToken(){
        // changing the timer status to stopped
        timerStatus = TimerStatus.STOPPED
        stopCountDownTimer()
        //doGetToken()
        generateRand()
    }
}

class Timber {
    companion object {
        fun e(s: String, value: String) {

        }
    }

}
