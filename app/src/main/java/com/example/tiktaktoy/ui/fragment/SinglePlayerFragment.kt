package com.example.tiktaktoy.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.tiktaktoy.R
import kotlinx.android.synthetic.main.fragment_loacl_multi_player.*
import kotlinx.android.synthetic.main.fragment_single_player.*
import kotlinx.android.synthetic.main.fragment_single_player.btn1
import kotlinx.android.synthetic.main.fragment_single_player.btn2
import kotlinx.android.synthetic.main.fragment_single_player.btn3
import kotlinx.android.synthetic.main.fragment_single_player.btn4
import kotlinx.android.synthetic.main.fragment_single_player.btn5
import kotlinx.android.synthetic.main.fragment_single_player.btn6
import kotlinx.android.synthetic.main.fragment_single_player.btn7
import kotlinx.android.synthetic.main.fragment_single_player.btn8
import kotlinx.android.synthetic.main.fragment_single_player.btn9
import kotlinx.android.synthetic.main.fragment_single_player.btnPlayAgain
import kotlinx.android.synthetic.main.fragment_single_player.ivWinner
import kotlinx.android.synthetic.main.fragment_single_player.ivWinnerLogo
import kotlinx.android.synthetic.main.fragment_single_player.tvWinner
import kotlinx.android.synthetic.main.fragment_single_player.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlinx.coroutines.coroutineScope as coroutineScope1

class SinglePlayerFragment : Fragment(R.layout.fragment_single_player) {

    private var i = 0
    private var activePlayer = 0
    private var gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var winningPositions = arrayOf( // array of Arrays of the positions that make player win
        arrayOf(0, 1, 2), // index = 0
        arrayOf(3, 4, 5), // index = 1
        arrayOf(6, 7, 8), // index = 2
        arrayOf(0, 3, 6), // index = 3
        arrayOf(1, 4, 7), // index = 4
        arrayOf(2, 5, 8), // index = 5
        arrayOf(0, 4, 8), // index = 6
        arrayOf(2, 4, 6) // index = 7
    )
    private var gameActive = true
    private lateinit var textViewWinner: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            btn1.setOnClickListener {
                xo(btn1)
                // ------------
                AutoPlay(btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9)
            }
            btn2.setOnClickListener {
                xo(btn2)
                AutoPlay(btn1,btn3,btn4,btn5,btn6,btn7,btn8,btn9)
            }
            btn3.setOnClickListener {
                xo(btn3)
                AutoPlay(btn2,btn1,btn4,btn5,btn6,btn7,btn8,btn9)
            }
            btn4.setOnClickListener {
                xo(btn4)
                AutoPlay(btn2,btn3,btn1,btn5,btn6,btn7,btn8,btn9)
            }
            btn5.setOnClickListener {
                xo(btn5)
                AutoPlay(btn2,btn3,btn4,btn1,btn6,btn7,btn8,btn9)
            }
            btn6.setOnClickListener {
                xo(btn6)
                AutoPlay(btn2,btn3,btn4,btn5,btn1,btn7,btn8,btn9)
            }
            btn7.setOnClickListener {
                xo(btn7)
                AutoPlay(btn2,btn3,btn4,btn5,btn6,btn1,btn8,btn9)
            }
            btn8.setOnClickListener {
                xo(btn8)
                AutoPlay(btn2,btn3,btn4,btn5,btn6,btn7,btn1,btn9)
            }
            btn9.setOnClickListener {
                xo(btn9)
                AutoPlay(btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn1)
            }
        }

        view.btnPlayAgain.setOnClickListener {

            if (textViewWinner == "Player Two Winner!") {
                AutoPlay(btn1,btn2.btn3,btn3,btn4,btn6,btn7,btn8,btn9)
            }

            playAgain()

        }

    }

    private fun AutoPlay(a:ImageView, b:ImageView, c:ImageView, d:ImageView, e:ImageView, f:ImageView, g:ImageView, h:ImageView) {
        val rand = Random.nextInt(9)

        if (rand == a.tag.toString().toInt() && a.isClickable) xo(a)
        else if (rand == b.tag.toString().toInt() && b.isClickable) xo(b)
        else if (rand == c.tag.toString().toInt() && c.isClickable) xo(c)
        else if (rand == d.tag.toString().toInt() && d.isClickable) xo(d)
        else if (rand == e.tag.toString().toInt() && e.isClickable) xo(e)
        else if (rand == f.tag.toString().toInt() && f.isClickable) xo(f)
        else if (rand == g.tag.toString().toInt() && g.isClickable) xo(g)
        else if (rand == h.tag.toString().toInt() && h.isClickable) xo(h)
        else { AutoPlay(a,b,c,d,e,f,g,h) }

    }

    private fun xo(btn: ImageView) {

        i++
        val tappedButton = btn.tag.toString().toInt()
        gameState[tappedButton] = activePlayer

        btn.apply {
            scaleX = 0.0f
            scaleY = 0.0f
            alpha = 0f
        }

        if (activePlayer == 0) {

            btn.setImageResource(R.drawable.x)
            activePlayer = 1

        } else {

            btn.setImageResource(R.drawable.o)
            activePlayer = 0

        }

        btn.animate().scaleX(0.85f).scaleY(0.85f).setDuration(200).alpha(1f).start()

        for (winningPosition in winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                if (activePlayer == 1) {

                    // winner = "x is winner"
                    textViewWinner = "Player One Winner!"
                    tvWinner.setTextColor(resources.getColor(R.color.x_color))
                    btnPlayAgain.rippleColor = resources.getColor(R.color.x_color)
                    gameActive = false

                } else {

                    // winner = "o is winner"
                    textViewWinner = "Player Two Winner!"
                    tvWinner.setTextColor(resources.getColor(R.color.o_color))
                    btnPlayAgain.rippleColor = resources.getColor(R.color.o_color)
                    gameActive = false

                }

                ivWinner.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()
                tvWinner.text = textViewWinner
                tvWinner.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()
                ivWinnerLogo.setImageResource(R.drawable.winner_logo)
                ivWinnerLogo.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()
                btnPlayAgain.visibility = View.VISIBLE
                btnPlayAgain.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()

            } else {

                if (this.i >= 9 && gameActive) {

                    tvWinner.setTextColor(resources.getColor(R.color.skyblue))
                    btnPlayAgain.rippleColor = resources.getColor(R.color.skyblue)
                    textViewWinner = "there is no winner!"
                    ivWinner.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()
                    tvWinner.text = textViewWinner
                    tvWinner.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()
                    ivWinnerLogo.setImageResource(R.drawable.loose)
                    ivWinnerLogo.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()
                    btnPlayAgain.visibility = View.VISIBLE
                    btnPlayAgain.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).start()

                }
            }

        }

        if (!gameActive) {

            view.apply {
                btn1.isClickable = false
                btn2.isClickable = false
                btn3.isClickable = false
                btn4.isClickable = false
                btn5.isClickable = false
                btn6.isClickable = false
                btn7.isClickable = false
                btn8.isClickable = false
                btn9.isClickable = false
            }

        }

        btn.isClickable = false

    }


    private fun restartGame(view: View) {
        view.animate().scaleY(0f).scaleX(0f).setDuration(200).alpha(0f)
        view.isClickable = true
    }

    private fun playAgain() {

        btnPlayAgain.animate().scaleY(0f).scaleX(0f).setDuration(200).alpha(0f)
        ivWinner.animate().scaleY(0f).scaleX(0f).setDuration(200).alpha(0f)
        tvWinner.animate().scaleY(0f).scaleX(0f).setDuration(200).alpha(0f)
        ivWinnerLogo.animate().scaleY(0f).scaleX(0f).setDuration(200).alpha(0f)
        restartGame(btn1)
        restartGame(btn2)
        restartGame(btn3)
        restartGame(btn4)
        restartGame(btn5)
        restartGame(btn6)
        restartGame(btn7)
        restartGame(btn8)
        restartGame(btn9)
        btnPlayAgain.visibility = View.GONE
        i = 0
        gameActive = true
        activePlayer = when (textViewWinner) {
            "Player One Winner!" -> 0
            "Player Two Winner!" -> 1
            else -> {
                val rand = Random.nextInt(2)
                rand
            }
        }
        gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
        winningPositions = arrayOf(
            arrayOf(0, 1, 2), // index = 0
            arrayOf(3, 4, 5), // index = 1
            arrayOf(6, 7, 8), // index = 2
            arrayOf(0, 3, 6), // index = 3
            arrayOf(1, 4, 7), // index = 4
            arrayOf(2, 5, 8), // index = 5
            arrayOf(0, 4, 8), // index = 6
            arrayOf(2, 4, 6) // index = 7
        )

    }

}