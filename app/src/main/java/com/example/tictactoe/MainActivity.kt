package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    var isGameOn: Boolean = true
    fun buClick(view: View) {
        val myButton: Button = view as Button
        var cellId: Int = 0
        when (myButton.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            else -> cellId = 9
        }
        if (isGameOn) playGame(myButton, cellId)
    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(myButton: Button, cellId: Int) {
        var moveTexts: TextView = findViewById(R.id.moveTexts)
        if (activePlayer == 1) {
            moveTexts.setText(R.string.playerOneMove)
            myButton.text = "X"
            myButton.backgroundTintList = getColorStateList(android.R.color.holo_purple)
            player1.add(cellId)
            activePlayer = 2
            myButton.isEnabled = false

            if (winner(player1)) {
                val congrats: TextView = won()
                congrats.setText(R.string.winPlayer1)
                congrats.visibility = View.VISIBLE
                isGameOn = false
            }
        } else if (activePlayer == 2) {
            moveTexts.setText(R.string.playerTwoMove)
            myButton.text = "O"
            myButton.backgroundTintList = getColorStateList(android.R.color.holo_orange_light)
            activePlayer = 1
            player2.add(cellId)
            myButton.isEnabled = false
            if (winner(player2)) {
                val congrats: TextView = won()
                congrats.setText(R.string.winPlayer2)
                congrats.visibility = View.VISIBLE
                isGameOn = false
            }
        }

    }

    fun won(): TextView {
        val congrats: TextView = findViewById(R.id.congratsText)
        congrats.visibility = View.VISIBLE

        val replayButton: Button = findViewById(R.id.replay)
        replayButton.visibility = View.VISIBLE
        replayButton.setOnClickListener() {
            recreate()
        }
        return congrats
    }


    fun winner(player: ArrayList<Int>): Boolean {
        if (player.contains(1) && player.contains(2) && player.contains(3)) return true
        else if (player.contains(4) && player.contains(5) && player.contains(6)) return true
        else if (player.contains(7) && player.contains(8) && player.contains(9)) return true
        else if (player.contains(1) && player.contains(4) && player.contains(7)) return true
        else if (player.contains(2) && player.contains(5) && player.contains(8)) return true
        else if (player.contains(3) && player.contains(6) && player.contains(9)) return true
        else if (player.contains(3) && player.contains(5) && player.contains(7)) return true
        else if (player.contains(1) && player.contains(5) && player.contains(9)) return true

        else return false
    }

}