package com.raduh.flowerapp.framework.service

import androidx.annotation.StringRes

sealed class LoadingState {
    object Loading : LoadingState()
    object Loaded : LoadingState()
    data class Failed(val msg: String? = null, @StringRes val msgResId: Int? = null) :
        LoadingState()
}

