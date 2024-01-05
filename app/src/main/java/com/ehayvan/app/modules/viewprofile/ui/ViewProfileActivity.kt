package com.ehayvan.app.modules.viewprofile.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewProfileBinding
import com.ehayvan.app.modules.editprofile.ui.EditProfileActivity
import com.ehayvan.app.modules.register.ui.RegisterActivity
import com.ehayvan.app.modules.viewprofile.`data`.viewmodel.ViewProfileVM
import org.json.JSONObject
import kotlin.String
import kotlin.Unit

class ViewProfileActivity : BaseActivity<ActivityViewProfileBinding>(R.layout.activity_view_profile)
    {
  private val viewModel: ViewProfileVM by viewModels<ViewProfileVM>()
      private lateinit var requestQueue: RequestQueue
      private lateinit var vetID: String
      private lateinit var ownerID: String
      private lateinit var password: String
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    requestQueue = Volley.newRequestQueue(this)
    val owner = intent.extras?.getString("ownerID")
    if (owner != null) {
      ownerID = owner
      vetID = ""
      val userType = "petowners/"
      getProfile(userType, ownerID)
    }
    val vet = intent.extras?.getString("vetID")
    if (vet != null) {
      vetID = vet
      ownerID = ""
      val userType = "veterinarians/"
      binding.container.visibility = View.VISIBLE
      getProfile(userType, vetID)
    }
    binding.viewProfileVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.btnEditProfileOne.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, EditProfileActivity::class.java)
      bundle.putString("vetID", vetID)
      bundle.putString("ownerID", ownerID)
      bundle.putString("password", password)
      intent.putExtras(bundle)
      startActivity(intent)
      finishAffinity()
    }
    binding.btnChangePassword.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, RegisterActivity::class.java)
      intent.putExtras(bundle)
      startActivity(intent)
      finishAffinity()
    }
  }

      fun getProfile(userType : String, userID : String) {
        val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/$userType$userID"
        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.GET, url, null,
          { response ->
            // Handle the response
            if (response != null) {
              val jsonString = response.toString()
              val jsonObject = JSONObject(jsonString)
              var clinic = ""
              if (userType == "veterinarians/")
                clinic = jsonObject.getString("clinic")
              val userName = jsonObject.getJSONObject("user")
              password = userName.getString("password")
              viewModel.viewProfileModel.value?.txtNameAndSurname = userName.getString("name") + " " + userName.getString("surname")
              viewModel.viewProfileModel.value?.txtEMail = userName.getString("email")
              if (clinic != "null") {
                viewModel.viewProfileModel.value?.txtClinic = clinic
              }
              val userTypeID = userName.getString("userTypeID")
              if (userTypeID == "1") {
                viewModel.viewProfileModel.value?.txtUserType = "Pet Owner"
              }
              if (userTypeID == "2") {
                viewModel.viewProfileModel.value?.txtUserType = "Veterinarian"
              }
            binding.viewProfileVM = viewModel
          }
          },
          { error ->
            // Handle errors
            Log.e("YourActivity", "Error: ${error.message}")
          })

        requestQueue.add(jsonObjectRequest)
      }

      companion object {
    const val TAG: String = "VIEW_PROFILE_ACTIVITY"

  }
}
