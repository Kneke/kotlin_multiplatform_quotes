package de.cknetsc.multiapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import sample.Sample
import sample.hello

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text).text = hello() + " " + Sample().checkMe()
    }
}
