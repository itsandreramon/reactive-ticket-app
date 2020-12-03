/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.saqs.app.databinding.ActivityPurchaseTicketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseTicketActivity : AppCompatActivity() {

    val args: PurchaseTicketActivityArgs by navArgs()
    private lateinit var binding: ActivityPurchaseTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseTicketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        binding.tvTest.text = args.eventId.toString()
    }
}
