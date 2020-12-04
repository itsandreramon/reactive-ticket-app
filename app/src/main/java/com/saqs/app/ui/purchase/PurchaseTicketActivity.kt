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
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEvent
import com.saqs.app.ui.purchase.viewmodel.PurchaseTicketViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PurchaseTicketActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: PurchaseTicketViewModel
    private lateinit var viewEvent: PurchaseTicketViewEvent

    val args: PurchaseTicketActivityArgs by navArgs()
    private lateinit var binding: ActivityPurchaseTicketBinding

    fun attachViewEvents(viewEvent: PurchaseTicketViewEvent) {
        this.viewEvent = viewEvent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseTicketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // prepare toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        // setup number picker
        binding.numberPickerAmount.apply {
            maxValue = 10
            minValue = 1
        }
    }
}
