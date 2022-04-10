package com.raduh.flowerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raduh.flowerapp.presentaion.OrdersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.orders_toolbar))
        this.supportFragmentManager.beginTransaction().add(
            R.id.container_view_fragment,
            OrdersFragment::class.java,
            null,
            OrdersFragment::class.java.name
        ).commit()
    }
}