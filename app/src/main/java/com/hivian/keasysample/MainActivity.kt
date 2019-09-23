package com.hivian.keasysample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.ajalt.timberkt.d
import org.threeten.bp.LocalDate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toto : LocalDate = LocalDate.now()
        d { "Start: ${toto.atStartOfDay()}" }
    }
}
