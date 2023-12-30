package com.ehayvan.app.modules.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityLogInBinding
import com.ehayvan.app.modules.login.`data`.viewmodel.LogInVM
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.register.ui.RegisterActivity
import com.ehayvan.app.modules.veterinariandashboard.ui.VeterinarianDashboardActivity
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import kotlin.String
import kotlin.Unit

class LogInActivity : BaseActivity<ActivityLogInBinding>(R.layout.activity_log_in) {
  private val viewModel: LogInVM by viewModels<LogInVM>()
  private lateinit var requestQueue: RequestQueue
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    requestQueue = Volley.newRequestQueue(this)
    val registerBtn = findViewById<TextView>(R.id.txtConfirmation)
    val loginBtn = findViewById<LinearLayout>(R.id.linearPrimaryButton)
    val txtLabel1 = findViewById<EditText>(R.id.txtLabel1)
    val txtLabel2 = findViewById<EditText>(R.id.txtLabel2)
    val txtLabel3 = findViewById<TextView>(R.id.txtError)
    val txtContainer = findViewById<LinearLayout>(R.id.linearOnboardingCr)
    val progressBar = findViewById<ProgressBar>(R.id.progressBar)
    registerBtn.setOnClickListener {
      val intent = Intent(this, RegisterActivity::class.java)
      startActivity(intent)
      overridePendingTransition(0, 0)
    }
    loginBtn.setOnClickListener {
      var mailCheck = true
      // Start the LoginActivity when the button is clicked
      val inputText1 = txtLabel1.text.toString().trim()
      val inputText2 = txtLabel2.text.toString().trim()
      if (inputText1.isEmpty())
        txtLabel1.error = "Email field cannot be empty"
      if (inputText2.isEmpty())
        txtLabel2.error = "Password field cannot be empty"
      if (inputText1.isNotEmpty() && (!inputText1.contains("@") || !inputText1.contains("."))) {
        txtLabel1.error = "Invalid email address"
        mailCheck = false
      }
      if (inputText1.isNotEmpty() && inputText2.isNotEmpty() && mailCheck) {
        progressBar.visibility = View.VISIBLE
        txtContainer.visibility = View.GONE
        val url = "http://192.168.0.19:8080/api/customers/login/$inputText1/$inputText2"
        val jsonBody = JSONObject()
        try {
          // Create the user object
          val userObject = JSONObject()
          userObject.put("password", txtLabel2)
          userObject.put("email", txtLabel1)
          // Add the user object to the main JSON body
        } catch (e: JSONException) {
          e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.GET, url, jsonBody,
          { response ->
            // Handle the response
            if (response != null) {
              val userTypeID = response.optString("userTypeID")
              if (userTypeID.toInt() == 1) {
                val ownerID = response.optString("ownerID")
                val bundle = Bundle()
                Log.d("Pet Owner ID", ownerID)
                val intent = Intent(this, PetOwnerDashboardActivity::class.java)
                bundle.putString("ownerID", ownerID)
                intent.putExtras(bundle)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finishAffinity()
              }
              if (userTypeID.toInt() == 2) {
                val vetId = response.optString("vetID")
                val bundle = Bundle()
                Log.d("Vet ID", vetId)
                val intent = Intent(this, VeterinarianDashboardActivity::class.java)
                bundle.putString("vetID", vetId)
                intent.putExtras(bundle)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finishAffinity()
              }
            } else {
              progressBar.visibility = View.GONE
              txtContainer.visibility = View.VISIBLE
              txtLabel3.visibility = View.VISIBLE
            }
          },
          { error ->
            // Handle errors
            Log.e("YourActivity", "Error: ${error.message}")
          })

        requestQueue.add(jsonObjectRequest)

      }
    }
    binding.logInVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "LOG_IN_ACTIVITY"

  }
}
