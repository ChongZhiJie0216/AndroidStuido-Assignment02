package com.example.grouptesting1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemName: String = "Miku FUFU"
        var itemDescription: String = "FUFU YYDS"
        var itemPrice: Double = 200.00

        val itemNameTextView = findViewById<TextView>(R.id.itemN)
        val itemDescriptionTextView = findViewById<TextView>(R.id.itemD)
        val itemPriceTextView = findViewById<TextView>(R.id.itemPrice)

        // 在Kotlin代码中找到RadioButton的实例
        val radioButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radioButton2 = findViewById<RadioButton>(R.id.radioButton2)

        val valuesTextView = findViewById<TextView>(R.id.values)
        val plusButton = findViewById<Button>(R.id.plus)
        val negatifButton = findViewById<Button>(R.id.negatif)

        itemNameTextView.text = itemName
        itemDescriptionTextView.text = itemDescription
        val formattedPrice = String.format("MYR %.2f", itemPrice)
        itemPriceTextView.text = formattedPrice
        radioButton1.text = "50 x 50 cm"
        radioButton2.text = "80 x 60 cm"

        var values = 0
        valuesTextView.text = values.toString()

        plusButton.setOnClickListener {
            values++
            // 更新值视图
            valuesTextView.text = values.toString()
        }

        // 减号按钮点击事件
        negatifButton.setOnClickListener {
            if (values > 0) {
                values--
                // 更新值视图
                valuesTextView.text = values.toString()
            }

        }
        val buyButton = findViewById<Button>(R.id.buy)

        buyButton.setOnClickListener {
            // 这里放置启动下一页的代码

            val intent = Intent(this, MainActivity2forPrint::class.java)
            intent.putExtra("quantity_key", values)
            intent.putExtra("price_key", itemPrice)
            intent.putExtra("item_name_key", itemName)
            startActivity(intent)
        }
}}