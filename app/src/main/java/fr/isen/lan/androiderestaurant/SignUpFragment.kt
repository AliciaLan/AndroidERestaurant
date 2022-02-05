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
import fr.isen.lan.androiderestaurant.model.SignUpData
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
            val signUpData = SignUpData(
                binding.signupFirstnameEditText.text.toString(),
                binding.signupLastnameEditText.text.toString(),
                binding.signupAddressEditText.text.toString(),
                binding.signupEmailEditText.text.toString(),
                binding.signupPasswordEditText.text.toString()
            )

            if (signUpData.firstname != "" && signUpData.lastname != "" && signUpData.address != "") {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(signUpData.email).matches()) {
                    if (signUpData.password.length > 8) {
                        signUp(signUpData)
                    } else {
                        Toast.makeText(context, "Merci d'entrer un mot de passe d'au moins 8 caractères", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Merci d'entrer un email valide", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Merci de remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp(signUpData : SignUpData) {
        val url = "http://test.api.catering.bluecodegames.com/user/register"

        val params = HashMap<String, Any>()
        params["id_shop"] = 1
        params["firstname"] = signUpData.firstname
        params["lastname"] = signUpData.lastname
        params["address"] = signUpData.address
        params["email"] = signUpData.email
        params["password"] = signUpData.password
        val jsonObject = JSONObject(params as Map<*, *>)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                Toast.makeText(context, "Inscription réussie", Toast.LENGTH_SHORT).show()
                (activity as? LoginActivity)?.signupToLogin()
            }, {
                Log.e("API", it.toString())
                Toast.makeText(context, "API request failed", Toast.LENGTH_SHORT).show()
            }
        )

        request.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)

        Volley.newRequestQueue(context).add(request)
    }
}