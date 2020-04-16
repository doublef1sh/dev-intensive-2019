package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage : ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : Button
    lateinit var benderObj : Bender
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = bt_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
        et_message.setOnEditorActionListener { tv_text, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
                messageEt.setText("")
                val (r, g, b) = color
                benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
                textTxt.text = phrase
                hideKeyboard()
                true
            } else { false }

        }

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.bt_send) {
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
            hideKeyboard()
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("MainActivity", "onSaveInstanceState ${benderObj.status.name}")
        Log.d("MainActivity", "onSaveInstanceState ${benderObj.question.name}")
    }


}
