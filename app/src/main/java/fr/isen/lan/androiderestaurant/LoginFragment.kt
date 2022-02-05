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
import fr.isen.lan.androiderestaurant.databinding.FragmentLoginBinding
import fr.isen.lan.androiderestaurant.model.LoginData
import org.json.JSONObject

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginToSignUp.setOnClickListener {
            (activity as? LoginActivity)?.loginToSignup()
        }

        binding.loginButton.setOnClickListener {
            val loginData = LoginData(
                binding.loginEmailEditText.text.toString(),
                binding.loginPasswordEditText.text.toString()
            )

            login(loginData)
        }
    }

    private fun login(loginData : LoginData) {
        val url = "http://test.api.catering.bluecodegames.com/user/login"

        val params = HashMap<String, String>()
        params["email"] = loginData.email
        params["password"] = loginData.password
        val jsonObject = JSONObject(params as Map<*, *>)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                Toast.makeText(context, "Connexion réussie", Toast.LENGTH_SHORT).show()
                (activity as? LoginActivity)?.loginToCommand(439) // id de bob
            }, {
                Log.e("API", it.toString())
                Toast.makeText(context, "API request failed", Toast.LENGTH_SHORT).show()
            }
        )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)

        Volley.newRequestQueue(context).add(request)
    }
}