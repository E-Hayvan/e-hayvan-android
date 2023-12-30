package com.ehayvan.app.modules.register.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatCheckBox
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityRegisterBinding
import com.ehayvan.app.modules.login.ui.LogInActivity
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.register.data.viewmodel.RegisterVM
import com.ehayvan.app.modules.veterinariandashboard.ui.VeterinarianDashboardActivity
import org.json.JSONException
import org.json.JSONObject


class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
  private val viewModel: RegisterVM by viewModels<RegisterVM>()
  private lateinit var requestQueue: RequestQueue
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    requestQueue = Volley.newRequestQueue(this)
    val loginBtn = findViewById<TextView>(R.id.txtConfirmation)
    loginBtn.setOnClickListener {
      // Start the LoginActivity when the button is clicked
      val intent = Intent(this, LogInActivity::class.java)
      startActivity(intent)
      overridePendingTransition(0, 0);
    }
    val checkBoxText = findViewById<AppCompatCheckBox>(R.id.checkBoxText)
    val checkBoxTextOne = findViewById<AppCompatCheckBox>(R.id.checkBoxTextOne)
    val checkBoxTextTwo = findViewById<AppCompatCheckBox>(R.id.checkBoxTextTwo)
    val progressBar = findViewById<ProgressBar>(R.id.progressBar)
    val signUpButton = findViewById<LinearLayout>(R.id.linearPrimaryButton)
    val layout = findViewById<LinearLayout>(R.id.linearOnboardingCr)
    val txtLabel1 = findViewById<EditText>(R.id.txtLabel1)
    val txtLabel2 = findViewById<EditText>(R.id.txtLabel2)
    val txtLabel3 = findViewById<EditText>(R.id.txtLabel3)

    checkBoxText.setOnClickListener {
      // When checkBoxText is clicked, uncheck checkBoxTextOne
      checkBoxTextOne.isChecked = false
    }

    checkBoxTextOne.setOnClickListener {
      // When checkBoxTextOne is clicked, uncheck checkBoxText
      checkBoxText.isChecked = false
    }

    signUpButton.setOnClickListener {
      val inputText1 = txtLabel1.text.toString().trim()
      val spaceIndex = inputText1.indexOf(' ')
      var mailCheck = true
      var surnameCheck = true
      val name: String
      val surname: String

      if (spaceIndex != -1) {
        // If there is a space, split the input into name and surname
        name = inputText1.substring(0, spaceIndex)
        surname = inputText1.substring(spaceIndex + 1)
      } else {
        // If there is no space, consider the entire input as the name
        name = inputText1
        surname = ""
      }
      val inputText2 = txtLabel2.text.toString().trim()

      if (inputText2.isNotEmpty() && (!inputText2.contains("@") || !inputText2.contains("."))) {
        txtLabel2.error = "Invalid email address"
        mailCheck = false
      }
      val inputText3 = txtLabel3.text.toString().trim()
      if (inputText1.isEmpty())
        txtLabel1.error = "Name and Surname field cannot be empty"
      if (surname == "") {
        txtLabel1.error = "Surname field cannot be empty"
        surnameCheck = false
      }
      if (inputText2.isEmpty())
        txtLabel2.error = "Email field cannot be empty"
      if (inputText3.isEmpty())
        txtLabel3.error = "Password field cannot be empty"
      if (checkBoxTextTwo.isChecked && (!checkBoxText.isChecked && !checkBoxTextOne.isChecked)) {
        checkBoxTextTwo.error = null
        checkBoxTextOne.error = "You should select a user type"
        checkBoxText.error = "You should select a user type"
      } else if (!checkBoxTextTwo.isChecked && (checkBoxText.isChecked || checkBoxTextOne.isChecked)){
        checkBoxTextTwo.error = "You Should Accept the Terms and Conditions"
        checkBoxTextOne.error = null
        checkBoxText.error = null
      } else if (!checkBoxTextTwo.isChecked && (!checkBoxText.isChecked && !checkBoxTextOne.isChecked)) {
        checkBoxTextOne.error = "You should select a user type"
        checkBoxText.error = "You should select a user type"
        checkBoxTextTwo.error = "You Should Accept the Terms and Conditions"
      } else {
        checkBoxTextTwo.error = null
        checkBoxTextOne.error = null
        checkBoxText.error = null
      }
      if ((checkBoxText.isChecked || checkBoxTextOne.isChecked) && checkBoxTextTwo.isChecked &&
        inputText1.isNotEmpty() && inputText2.isNotEmpty() && inputText3.isNotEmpty() && surnameCheck && mailCheck
      ) {
        progressBar.visibility = View.VISIBLE
        layout.visibility = View.GONE
        var url : String = ""
        if (checkBoxText.isChecked) {
          url = "http://192.168.0.19:8080/api/petowners/newowner"
        }
        if (checkBoxTextOne.isChecked) {
          url = "http://192.168.0.19:8080/api/veterinarians"
        }

        val jsonBody = JSONObject()
        try {
          // Create the user object
          val userObject = JSONObject()
          userObject.put("name", name)
          userObject.put("password", inputText3)
          userObject.put("email", inputText2)
          userObject.put("surname", surname)
          // Add the user object to the main JSON body
          jsonBody.put("user", userObject)
          if (checkBoxTextOne.isChecked) {
            jsonBody.put("clinic", "")
          }
        } catch (e: JSONException) {
          e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.POST, url, jsonBody,
          { response ->
            // Handle the response
            progressBar.visibility = View.GONE
            if (checkBoxText.isChecked) {
              val bundle = Bundle()
              val ownerID = response.optString("ownerID")
              Log.d("Pet Owner ID", ownerID)
              val intent = Intent(this, PetOwnerDashboardActivity::class.java)
              bundle.putString("ownerID", ownerID)
              intent.putExtras(bundle)
              startActivity(intent)
              finishAffinity()
            }
            if (checkBoxTextOne.isChecked) {
              val bundle = Bundle()
              val vetId = response.optString("vetID")
              Log.d("Vet ID", vetId)
              val intent = Intent(this, VeterinarianDashboardActivity::class.java)
              bundle.putString("vetID", vetId)
              intent.putExtras(bundle)
              startActivity(intent)
              finishAffinity()
            }
          },
          { error ->
            // Handle errors
            Log.e("YourActivity", "Error: ${error.message}")
          })
        
        requestQueue.add(jsonObjectRequest)
      }
    }
    binding.registerVM = viewModel
  }


  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "REGISTER_ACTIVITY"

  }
}
