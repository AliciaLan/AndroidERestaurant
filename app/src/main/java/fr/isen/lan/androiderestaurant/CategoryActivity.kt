package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.lan.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.lan.androiderestaurant.model.Dish
import fr.isen.lan.androiderestaurant.model.RequestResult
import org.json.JSONObject

const val DISH = "dish"

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryButtonBack.setOnClickListener {
            finish()
        }

        val category = intent.getStringExtra(TITLE_CATEGORY)
        binding.categoryTitle.text = category


        val url = "http://test.api.catering.bluecodegames.com/menu"

        val params = HashMap<String, Number>()
        params["id_shop"] = 1
        val jsonObject = JSONObject(params as Map<*, *>)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                val json = Gson().fromJson(it.toString(), RequestResult::class.java)
                display(json.data.firstOrNull{dish-> dish.name_fr == category}?.items ?: listOf())
            }, {
                Log.e("API", it.toString())
                Toast.makeText(this, "API request failed", Toast.LENGTH_SHORT).show()
                finish()
            })

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)

        Volley.newRequestQueue(this).add(request)
    }

    private fun display(dishesList : List<Dish>) {
        binding.categoryList.layoutManager = LinearLayoutManager(this)

        binding.categoryList.adapter = DishAdapter(dishesList) {
            val intent = Intent(this, DetailsDishActivity::class.java).apply {
                putExtra(DISH, it)
            }
            startActivity(intent)
        }
    }
}
