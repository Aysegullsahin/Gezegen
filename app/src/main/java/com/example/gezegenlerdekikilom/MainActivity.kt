package com.example.gezegenlerdekikilom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*
import java.net.PortUnreachableException

class MainActivity : AppCompatActivity(), View.OnClickListener {


    val KILO_TO_POUND = 2.2045
    val MARS = 0.38
    val VENUS = 0.91
    val JUPITER = 2.34
    val POUND_TO_KILO = 0.45359237


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cbVenus.setOnClickListener(this)
        cbJupiter.setOnClickListener(this)
        cbMars.setOnClickListener(this)
        /*btnHesapla.setOnClickListener {

            var kullaniciAgirlikPound = kiloToPound(kullaniciKilo.toString().toDouble())
            var marstakiAgirlikPound = kullaniciAgirlikPound * MARS
            var marstakiAgirlikKilo = poundToKilo(marstakiAgirlikPound)

            tvSonuc.text = marstakiAgirlikKilo.formatla(2).toString()
        }*/
    }

    fun kiloToPound(kilo: Double): Double {

        return kilo * KILO_TO_POUND
    }

    fun poundToKilo(pound: Double): Double {

        return pound * POUND_TO_KILO
    }

    override fun onClick(v: View?) {

        v as CheckBox
        var isChecked: Boolean = v.isChecked


        if(!TextUtils.isEmpty(etKilo.text.toString())){
            var kullaniciKilo = etKilo.text.toString().toDouble()
            var kullaniciPound = kiloToPound(kullaniciKilo)

            when (v.id) {

                R.id.cbMars -> if (isChecked) {
                    cbJupiter.isChecked = false
                    cbVenus.isChecked = false
                    hesaplaAgirlikPound(kullaniciPound, v)
                }

                R.id.cbVenus -> if (isChecked) {
                    cbJupiter.isChecked = false
                    cbMars.isChecked = false
                    hesaplaAgirlikPound(kullaniciPound, v)
                }

                R.id.cbJupiter -> if (isChecked) {
                    cbMars.isChecked = false
                    cbVenus.isChecked = false
                    hesaplaAgirlikPound(kullaniciPound, v)
                }
            }
        }

    }

    fun hesaplaAgirlikPound(pound: Double, checkBox: CheckBox) {

        var sonuc: Double = 0.0

        when (checkBox.id) {

            R.id.cbMars -> sonuc = pound * MARS
            R.id.cbVenus -> sonuc = pound * VENUS
            R.id.cbJupiter -> sonuc = pound * JUPITER
            else -> sonuc = 0.0
        }

        var sonucToKilo = poundToKilo(sonuc)
        tvSonuc.text = sonucToKilo.formatla(2)

    }

    fun Double.formatla(kacTaneRakam: Int) = java.lang.String.format("%.${kacTaneRakam}f", this)
    }
