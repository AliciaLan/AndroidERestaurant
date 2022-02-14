package fr.isen.lan.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

/**
 * Manage the actionBar.
 * Activity extended by all activities of the application.
 */
open class MenuActivity : AppCompatActivity() {
    private var menu : Menu? = null

    override fun onCreateOptionsMenu(myMenu: Menu): Boolean {
        myMenu.also {
            menu = it
            menu.apply {
                if(this != null) {
                    menuInflater.inflate(R.menu.menu, this)

                    this.findItem(R.id.menuQuantityBasket).actionView.findViewById<TextView>(R.id.menuTextQuantity).text =
                        (this@MenuActivity.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE)
                            .getInt(getString(R.string.spTotalQuantity), 0)).toString()
                }
                return super.onCreateOptionsMenu(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuButtonBasket -> {
                startActivity(Intent(this, BasketActivity::class.java))
                true
            }
            R.id.menuButtonUser -> {
                val userId = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getInt(getString(R.string.spUserId), 0)

                if (userId == 0) {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    startActivity(Intent(this, UserActivity::class.java))
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        menu.apply {
            if(this != null) {
                this.findItem(R.id.menuQuantityBasket).actionView.findViewById<TextView>(R.id.menuTextQuantity).text = (this@MenuActivity.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getInt(getString(R.string.spTotalQuantity), 0)).toString()
            }
        }
        super.onResume()
    }
}