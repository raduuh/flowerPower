package com.raduh.flowerapp.presentaion

import android.view.MenuItem

interface OptionsMenuClickListener {
    fun onOptionsMenuClicked(position: Int, item: MenuItem)
}