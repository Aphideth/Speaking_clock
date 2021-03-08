package com.example.horlogeparlante

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*



class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    @RequiresApi(Build.VERSION_CODES.O)
    val hour = current.format(formatter)


    lateinit var hourVocal: TextToSpeech



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editHour = findViewById<TextView>(R.id.txtHour2)
        editHour.setText(hour)




        hourVocal = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
               if (status != TextToSpeech.ERROR) {
               hourVocal.language = Locale.FRANCE
          }
        })

        btnSpeak.setOnClickListener {
            val toSpeak = editHour.text.toString()
            hourVocal.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}

