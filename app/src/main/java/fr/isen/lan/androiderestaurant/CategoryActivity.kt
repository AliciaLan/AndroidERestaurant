package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val message = intent.getStringExtra(TITLE_CATEGORY)
        findViewById<TextView>(R.id.text_category).apply {
            text = message
        }
    }

    fun back(v : View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}