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

        // Retrieve data passed from the Intent.
            val quantity = intent.getIntExtra("quantity_key", 0) // Default value is 0.
        val price = intent.getDoubleExtra("price_key", 0.0)
        val itemName = intent.getStringExtra("item_name_key")

        val totalprice = quantity * price

        val nameTextView = findViewById<TextView>(R.id.name)
        val valueTextView = findViewById<TextView>(R.id.vvalue)
        val priceTextView = findViewById<TextView>(R.id.price)
        val totalTextView = findViewById<TextView>(R.id.total)

        nameTextView.text = "Item Name: $itemName"
        valueTextView.text = "Quantity: $quantity"
        priceTextView.text = "Price: MYR %.2f".format(price) // Correct string formatting.
        totalTextView.text = "Total: MYR %.2f".format(totalprice) // Correct string formatting.
        // Get the firstpage button.
        val firstPageButton = findViewById<Button>(R.id.firstpage)

        firstPageButton.setOnClickListener {
            // Create a notification channel.

            createNotificationChannel()

            // Create a notification.
            val notification = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Payment Done")
                .setContentText("Thank you for your payment.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true) // Automatically dismiss on notification click.
                .build()

            // Display the notification.
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notification)

            // Return to MainActivity.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Get the Spinner.
        val spinner = findViewById<Spinner>(R.id.spinner)

        // Create an adapter and set the data.
        val data = listOf("Option 1", "Option 2", "Option 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter to the Spinner.
        spinner.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        // Create a notification channel.
        val name = "Payment Channel"
        val descriptionText = "Channel for payment notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = descriptionText
            enableLights(true)
            lightColor = Color.RED
        }

        // Register the notification channel.
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
