package com.ehayvan.app.modules.editprofile.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditProfileBinding
import com.ehayvan.app.modules.editprofile.data.viewmodel.EditProfileVM
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.veterinariandashboard.ui.VeterinarianDashboardActivity
import org.json.JSONException
import org.json.JSONObject
import kotlin.properties.Delegates

class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile) {
    private val viewModel: EditProfileVM by viewModels<EditProfileVM>()
    private lateinit var requestQueue: RequestQueue
    private lateinit var vetID: String
    private lateinit var ownerID: String
    private lateinit var userType: String
    private lateinit var password: String
    private lateinit var userID: String
    private var isVet = false

    override fun onInitialized(): Unit {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        requestQueue = Volley.newRequestQueue(this)
        password = intent.extras?.getString("password").toString()
        val owner = intent.extras?.getString("ownerID").toString()
        if (owner != "") {
            userID = owner
            userType = "petowners/update/"
        }
        val vet = intent.extras?.getString("vetID").toString()
        if (vet != "") {
            isVet = true
            binding.linearInputBox3.visibility = View.VISIBLE
            userID = vet
            userType = "veterinarians/update/"
        }
        binding.editProfileVM = viewModel
    }


    override fun setUpClicks(): Unit {
        binding.imageArrowleft.setOnClickListener {
            onBackPressed()
        }
        binding.btnEdit.setOnClickListener {
            val txtLabel1 = findViewById<EditText>(R.id.etInputContent)
            val txtLabel2 = findViewById<EditText>(R.id.etInputContentOne)
            val txtLabel3 = findViewById<EditText>(R.id.etLanguage)
            val inputText1 = txtLabel1.text.toString().trim()
            val spaceIndex = inputText1.indexOf(' ')
            var mailCheck = true
            var surnameCheck = true
            var clinicCheck = true
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
            if (inputText1.isEmpty())
                txtLabel1.error = "Name and Surname field cannot be empty"
            if (surname == "") {
                txtLabel1.error = "Surname field cannot be empty"
                surnameCheck = false
            }
            if (isVet) {
                val inputText3 = txtLabel3.text.toString().trim()
                if (inputText3.isEmpty()) {
                    txtLabel3.error = "Clinic field cannot be empty"
                    clinicCheck = false
                } else {
                    clinicCheck = true
                }
            }
            if (inputText2.isEmpty())
                txtLabel2.error = "Email field cannot be empty"
            if (inputText1.isNotEmpty() && inputText2.isNotEmpty() && surnameCheck && mailCheck && clinicCheck) {
                val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/$userType$userID"
                val jsonBody = JSONObject()
                try {
                    // Create the user object
                    val userObject = JSONObject()
                    userObject.put("name", name)
                    userObject.put("password", password)
                    userObject.put("email", inputText2)
                    userObject.put("surname", surname)
                    // Add the user object to the main JSON body
                    jsonBody.put("user", userObject)
                    if (userType == "veterinarians/update/")
                        jsonBody.put("clinic", txtLabel3.text.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.PUT, url, jsonBody,
                    { response ->
                        // Handle the response
                        if (response != null) {
                            Toast.makeText(this, "Profile Edited Successfully", Toast.LENGTH_SHORT).show()
                            if (userType == "veterinarians/update/") {
                                val bundle = Bundle()
                                val intent = Intent(this, VeterinarianDashboardActivity::class.java)
                                bundle.putString("vetID", userID)
                                intent.putExtras(bundle)
                                startActivity(intent)
                                finishAffinity()
                            } else {
                                val bundle = Bundle()
                                val intent = Intent(this, PetOwnerDashboardActivity::class.java)
                                bundle.putString("ownerID", userID)
                                intent.putExtras(bundle)
                                startActivity(intent)
                                finishAffinity()
                            }
                        }
                    },
                    { error ->
                        // Handle errors
                        Toast.makeText(this, "Profile Edited Failed", Toast.LENGTH_SHORT).show()
                        Log.e("YourActivity", "Error: ${error.message}")
                    })

                requestQueue.add(jsonObjectRequest)
            }
            }
        }




    companion  object {
        const val TAG: String = "EDIT_PROFILE_ACTIVITY"
    }
}