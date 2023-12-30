package com.ehayvan.app.modules.petownerdashboard.ui

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityPetOwnerDashboardBinding
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.data.viewmodel.PetOwnerDashboardVM
import org.json.JSONException
import org.json.JSONObject


class PetOwnerDashboardActivity :
  BaseActivity<ActivityPetOwnerDashboardBinding>(R.layout.activity_pet_owner_dashboard) {

  private val viewModel: PetOwnerDashboardVM by viewModels<PetOwnerDashboardVM>()
  private lateinit var requestQueue: RequestQueue
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val ownerID = intent.extras?.getString("ownerID")
    requestQueue = Volley.newRequestQueue(this)

    val petId = 2
    makeGetRequest(petId)

  }

  override fun setUpClicks(): Unit {
    // Set up any additional click listeners if needed
  }

  fun onClickRecyclerListPet(
    view: View,
    position: Int,
    item: ListPetRowModel
  ): Unit {
    when(view.id) {
      // Handle item click actions if needed
    }
  }

  private fun makeGetRequest(petId: Int) {
    val url = "http://192.168.0.19:8080/api/veterinarians"
    val jsonBody = JSONObject()
    try {
      // Create the user object
      val userObject = JSONObject()
      userObject.put("name", "mobilevet")
      userObject.put("password", "mobilevet")
      userObject.put("email", "mobilevet")
      userObject.put("surname", "mobilevet")
      // Add the user object to the main JSON body
      jsonBody.put("user", userObject)
      jsonBody.put("clinic", "mobilevet")
    } catch (e: JSONException) {
      e.printStackTrace()
    }

    val jsonObjectRequest = JsonObjectRequest(
      Request.Method.POST, url, jsonBody,
      { response ->
        // Handle the response
        val petName = response.optString("vetID")
        val dummyList = listOf(
          ListPetRowModel(petName, "Price 1"),
          ListPetRowModel("Pet 2", "Price 2"),

          // Add more items as needed
        )
        val listPetAdapter = ListPetAdapter(dummyList)
        binding.recyclerListPet.adapter = listPetAdapter
        // Initialize and set up the RecyclerView with the ListPetAdapter
        listPetAdapter.setOnItemClickListener(object : ListPetAdapter.OnItemClickListener {
          override fun onItemClick(view: android.view.View, position: Int, item: ListPetRowModel) {
            onClickRecyclerListPet(view, position, item)
          }
        })

        binding.petOwnerDashboardVM = viewModel
        Log.d("YourActivity", "Pet Name: $petName")
      },
      { error ->
        // Handle errors
        Log.e("YourActivity", "Error: ${error.message}")
      })

    // Add the request to the RequestQueue.
    /*val jsonObjectRequest2 = JsonObjectRequest(
      Request.Method.GET, url, null,
      { response ->
        // Handle the response
        val petName = response.optString("petName")
        val dummyList = listOf(
          ListPetRowModel(petName, "Price 1"),
          ListPetRowModel("Pet 2", "Price 2"),

          // Add more items as needed
        )
        val listPetAdapter = ListPetAdapter(dummyList)
        binding.recyclerListPet.adapter = listPetAdapter
        // Initialize and set up the RecyclerView with the ListPetAdapter
        listPetAdapter.setOnItemClickListener(object : ListPetAdapter.OnItemClickListener {
          override fun onItemClick(view: android.view.View, position: Int, item: ListPetRowModel) {
            onClickRecyclerListPet(view, position, item)
          }
        })

        binding.petOwnerDashboardVM = viewModel
        Log.d("YourActivity", "Pet Name: $petName")
      },
      { error ->
        // Handle errors
        Log.e("YourActivity", "Error: ${error.message}")
      }
    )*/

    requestQueue.add(jsonObjectRequest)
  }

  override fun onStop() {
    super.onStop()
    // Cancel all requests when the activity is stopped to avoid memory leaks.
    requestQueue.cancelAll(this)
  }

  companion object {
    const val TAG: String = "PET_OWNER_DASHBOARD_ACTIVITY"
  }
}
