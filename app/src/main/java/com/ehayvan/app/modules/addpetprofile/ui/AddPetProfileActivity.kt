package com.ehayvan.app.modules.addpetprofile.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityAddPetProfileBinding
import com.ehayvan.app.modules.addpetprofile.`data`.viewmodel.AddPetProfileVM
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.ui.ListPetAdapter
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import org.json.JSONException
import org.json.JSONObject
import kotlin.String
import kotlin.Unit

class AddPetProfileActivity :
    BaseActivity<ActivityAddPetProfileBinding>(R.layout.activity_add_pet_profile) {
  private val viewModel: AddPetProfileVM by viewModels<AddPetProfileVM>()
  private lateinit var selectedType : String
  private lateinit var requestQueue: RequestQueue
  private lateinit var owner : String

  override fun onInitialized(): Unit {
    requestQueue = Volley.newRequestQueue(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val ownerID = intent.extras?.getString("ownerID")
    if (ownerID != null) {
      owner = ownerID
    }
    val mSpinner = findViewById<Spinner>(R.id.spinner)

    // Create a list to display in the Spinner
    val mList = arrayOf<String?>("Dog", "Cat", "Bird", "Other")

    // Create an adapter as shown below
    val mArrayAdapter = ArrayAdapter<Any?>(this, R.layout.spinner_list, mList)
    mArrayAdapter.setDropDownViewResource(R.layout.spinner_list)

    // Set the adapter to the Spinner
    mSpinner.prompt = "Select pet's type";
    mSpinner.adapter = mArrayAdapter
    mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(
        parentView: AdapterView<*>,
        selectedItemView: View,
        position: Int,
        id: Long
      ) {
        selectedType = (position+1).toString()
      }

      override fun onNothingSelected(parentView: AdapterView<*>?) {
        // Do nothing here
      }
    }
    binding.addPetProfileVM = viewModel
  }

  @SuppressLint("SuspiciousIndentation")
  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.btnAddPet.setOnClickListener {
      val petName = binding.etInputContent.text
      val petAge = binding.etLanguage.text
      if (petAge.isEmpty())
        binding.etLanguage.error = "You should enter the pet's age"
      if (petName.isEmpty())
        binding.etInputContent.error = "You should enter the pet's name"
      if (petAge.isNotEmpty() && petName.isNotEmpty()) {
        val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/pets"
        var jsonBody = JSONObject()
        try {
          // Create the user object
          val userObject = JSONObject()
          userObject.put("petName", petName)
          userObject.put("age", petAge)
          userObject.put("petTypeID", selectedType)
          userObject.put("description", "")
          userObject.put("petOwnerID", owner)
          // Add the user object to the main JSON body
          jsonBody = userObject
        } catch (e: JSONException) {
          e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.POST, url, jsonBody,
          { response ->
            // Handle the response
            if (response != null) {
              val bundle = Bundle()
              val intent = Intent(this, PetOwnerDashboardActivity::class.java)
              bundle.putString("ownerID", owner)
              intent.putExtras(bundle)
              startActivity(intent)
              finish()
            }
          },
          { error ->
            // Handle errors
            Log.e("YourActivity", "Error: ${error.message}")
          })

          requestQueue.add(jsonObjectRequest)
      }

    }
  }

  companion object {
    const val TAG: String = "ADD_PET_PROFILE_ACTIVITY"

  }
}
