package fr.isen.lan.androiderestaurant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.lan.androiderestaurant.databinding.FragmentSignUpBinding
import org.json.JSONObject

class SignUpFragment : Fragment() {
    private lateinit var binding : FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupToLogin.setOnClickListener {
            (activity as? LoginActivity)?.signupToLogin()
        }

        binding.signupButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val url = "http://test.api.catering.bluecodegames.com/user/register"

        val params = HashMap<String, Any>()
        params["id_shop"] = 1
        params["firstname"] = binding.signupFirstnameEditText.text.toString()
        params["lastname"] = binding.signupLastnameEditText.text.toString()
        params["address"] = binding.signupAddressEditText.text.toString()
        params["email"] = binding.signupEmailEditText.text.toString()
        params["password"] = binding.signupPasswordEditText.text.toString()
        val jsonObject = JSONObject(params as Map<*, *>)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                //val json = Gson().fromJson(it.toString(), RequestResult::class.java)
                Log.d("response", it.toString())
            }, {
                Log.e("API", it.toString())
             // Toast.makeText(context, "API request failed", Toast.LENGTH_SHORT).show()
            }
        )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)

        Volley.newRequestQueue(context).add(request)
    }
}