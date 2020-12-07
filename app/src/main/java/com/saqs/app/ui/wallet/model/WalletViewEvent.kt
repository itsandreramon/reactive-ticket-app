/*
 * Copyright 2020 - André Thiele
 *
 * Department of Computer Science and Media
 * University of Applied Sciences Brandenburg
 */

package com.saqs.app.ui.wallet.model

import com.saqs.app.ui.wallet.model.WalletViewEventType.Init
import com.saqs.app.util.ViewEvent

interface WalletViewEvent : ViewEvent {
    fun init(event: Init)
}

sealed class WalletViewEventType {
    object Init : WalletViewEventType()
}
