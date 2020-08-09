package com.example.tiktaktoy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.tiktaktoy.R
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.materialButton1.apply {
            startupState(0)
            setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_singlePlayerFragment)
            }
        }

        view.materialButton3.apply {
            startupState(300)
            setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_localMultiPlayerFragment)
            }
        }

        view.ivHomeGameLogo.apply {
            translationX = -300f
            alpha = 0f
            animate().translationX(0f).alpha(1f).duration = 300
        }

    }

    private fun MaterialButton.startupState(i: Long) {
        scaleX = 0.0f
        scaleY = 0.0f
        alpha = 0F
        animate().scaleX(1.0f).scaleY(1.0f).alpha(1F).setDuration(300).setStartDelay(i).start()
    }

}