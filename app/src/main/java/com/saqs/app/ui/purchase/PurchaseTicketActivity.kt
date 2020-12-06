/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.purchase

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.saqs.app.databinding.ActivityPurchaseTicketBinding
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEvent
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.BuyTicket
import com.saqs.app.ui.purchase.model.PurchaseTicketViewEventType.InitState
import com.saqs.app.ui.purchase.viewmodel.PurchaseTicketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PurchaseTicketActivity : AppCompatActivity() {

    private val viewModel by viewModels<PurchaseTicketViewModel>()
    private lateinit var viewEvent: PurchaseTicketViewEvent

    private val args: PurchaseTicketActivityArgs by navArgs()
    private lateinit var binding: ActivityPurchaseTicketBinding

    fun attachViewEvents(viewEvent: PurchaseTicketViewEvent) {
        this.viewEvent = viewEvent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.attachEvents(this)

        binding = ActivityPurchaseTicketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewEvent.initState(InitState(args.eventId))
        initViewStates()
        initViewEffects()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        binding.btnPurchase.setOnClickListener {
            viewEvent.buyTicket(BuyTicket(binding.numberPickerAmount.value))
        }
    }

    private fun initViewEffects() {
        viewModel.effect.setPurchaseButtonState.onEach { effect ->
            binding.btnPurchase.isEnabled = effect.enabled
        }.launchIn(lifecycleScope)

        viewModel.effect.navigateExplore.onEach { effect ->
            onBackPressed()
        }.launchIn(lifecycleScope)

        viewModel.effect.showErrorDialog.onEach { effect ->
            MaterialAlertDialogBuilder(this)
                .setTitle("Error")
                .setMessage("An error occurred while purchasing this ticket.")
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
        }.launchIn(lifecycleScope)
    }

    private fun initViewStates() {
        viewModel.state.selectedEvent.filterNotNull().onEach { state ->
            binding.tvAmountAvailable.text = "${state.available} of ${state.amount}"
            binding.collapsingToolbar.title = state.name

            binding.numberPickerAmount.apply {
                maxValue = state.available
                minValue = 1
            }
        }.launchIn(lifecycleScope)
    }
}
