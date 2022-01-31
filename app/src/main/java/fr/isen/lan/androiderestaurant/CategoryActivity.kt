package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.lan.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.lan.androiderestaurant.model.DishModel
import org.json.JSONObject

const val TITLE_DISH = "titleDish"

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryButtonBack.setOnClickListener {
            finish()
        }

        binding.categoryTitle.text = intent.getStringExtra(TITLE_CATEGORY)

        binding.categoryList.layoutManager = LinearLayoutManager(this)

        val dishes = ArrayList<DishModel>()
        for (i in 1..10) {
            dishes.add(DishModel(R.drawable.logo, "Item $i", "desc item $i", "$i"))
        }

        postVolley()

        binding.categoryList.adapter = DishAdapter(dishes) {
            val intent = Intent(this, DetailsDishActivity::class.java).apply {
                putExtra(TITLE_DISH, it)
            }
            startActivity(intent)
        }
    }

    private fun postVolley() {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val params = HashMap<String,Number>()
        params["id_shop"] = 1
        val jsonObject = JSONObject(params as Map<*, *>)

        val request = JsonObjectRequest(
            Request.Method.POST,url,jsonObject,
            {
                Log.d("API", it.toString())
            }, {
                Log.e("API", it.toString())
            })

        Volley.newRequestQueue(this).add(request)
    }
}
