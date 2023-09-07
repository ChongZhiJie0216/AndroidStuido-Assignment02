package com.example.grouptesting1
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import android.widget.Spinner
import android.widget.ArrayAdapter

class MainActivity2forPrint : AppCompatActivity() {

    private val channelID = "payment_channel"

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2for_print)

        // 从Intent中获取传递的数据
        val quantity = intent.getIntExtra("quantity_key", 0) // 默认值为0
        val price = intent.getDoubleExtra("price_key", 0.0)
        val itemName = intent.getStringExtra("item_name_key")

        val totalprice = quantity * price

        val nameTextView = findViewById<TextView>(R.id.name)
        val valueTextView = findViewById<TextView>(R.id.vvalue)
        val priceTextView = findViewById<TextView>(R.id.price)
        val totalTextView = findViewById<TextView>(R.id.total)

        nameTextView.text = "Item Name: $itemName"
        valueTextView.text = "Quantity: $quantity"
        priceTextView.text = "Price: MYR %.2f".format(price) // 修正字符串格式化
        totalTextView.text = "Total: MYR %.2f".format(totalprice) // 修正字符串格式化

        // 获取firstpage按钮
        val firstPageButton = findViewById<Button>(R.id.firstpage)

        firstPageButton.setOnClickListener {
            // 创建通知渠道
            createNotificationChannel()

            // 创建通知
            val notification = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Payment Done")
                .setContentText("Thank you for your payment.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true) // 单击通知后自动取消
                .build()

            // 显示通知
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notification)

            // 返回到MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 获取Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)

        // 创建一个适配器并设置数据
        val data = listOf("Option 1", "Option 2", "Option 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // 设置适配器到Spinner
        spinner.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        // 创建通知渠道
        val name = "Payment Channel"
        val descriptionText = "Channel for payment notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = descriptionText
            enableLights(true)
            lightColor = Color.RED
        }

        // 注册通知渠道
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
