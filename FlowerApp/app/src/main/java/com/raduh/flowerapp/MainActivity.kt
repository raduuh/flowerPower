package com.raduh.flowerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raduh.flowerapp.presentaion.OrdersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportFragmentManager.beginTransaction().add(
            R.id.widgets_fragment,
            OrdersFragment::class.java,
            null,
            OrdersFragment::class.java.name
        ).commit()
    }
}