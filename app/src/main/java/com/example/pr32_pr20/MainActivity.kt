package com.example.pr32_pr20
import android.content.res.Resources.Theme
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var coordLayout: CoordinatorLayout? = null
    private var buttonDefault: Button? = null
    private var buttonCallAction: Button? = null
    private var buttonCustom: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(this)



        coordLayout = findViewById<View>(R.id.coordLayout) as CoordinatorLayout



        val contentMainLayout = findViewById<View>(R.id.content_main_layout)

        buttonDefault = contentMainLayout.findViewById<View>(R.id.button_default) as Button
        buttonCallAction = contentMainLayout.findViewById<View>(R.id.button_call_action) as Button
        buttonCustom = contentMainLayout.findViewById<View>(R.id.button_custom) as Button



        buttonDefault!!.setOnClickListener(this)
        buttonCallAction!!.setOnClickListener(this)
        buttonCustom!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val rootView = findViewById<View>(R.id.content_main_layout)
        val id = view.id
        when (id) {
            R.id.button_default -> {

                Snackbar.make(
                    coordLayout!!,
                    "Вот так вот",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            R.id.button_call_action -> {
                val snackbar = Snackbar
                    .make(
                        coordLayout!!,
                        "Вы изменили что-то",
                        Snackbar.LENGTH_LONG
                    )
                    .setAction("Вернуть как было?") {
                        Snackbar.make(
                            coordLayout!!,
                            "Все вернулось на свои места!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                snackbar.show()
            }

            R.id.button_custom -> {
                val snackbar = Snackbar
                    .make(
                        coordLayout!!,
                        "Повторите еще раз!",
                        Snackbar.LENGTH_LONG
                    )
                    .setAction("Повторить") {
                        val randomColor = getRandomColor()
                        rootView.setBackgroundColor(randomColor)
                    }

                snackbar.setActionTextColor(Color.CYAN)

                val textView = snackbar.view
                    .findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView

                textView.setTextColor(Color.LTGRAY)

                snackbar.show()
            }

            R.id.fab -> {
                Snackbar.make(
                    view,
                    "Вы нажали на FloatingActionButton",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
    fun getRandomColor(): Int {
        val rnd = java.util.Random()
        return android.graphics.Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}