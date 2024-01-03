package com.ehayvan.app.modules.vetsearchscreen.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityVetSearchScreenBinding
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.ui.ListPetAdapter
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.vetsearchscreen.`data`.model.ListVetModel
import com.ehayvan.app.modules.vetsearchscreen.`data`.viewmodel.VetSearchScreenVM
import com.ehayvan.app.modules.viewpetprofile.ui.ViewPetProfileActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.String
import kotlin.Unit

class VetSearchScreenActivity :
  BaseActivity<ActivityVetSearchScreenBinding>(R.layout.activity_vet_search_screen) {

  private val viewModel: VetSearchScreenVM by viewModels<VetSearchScreenVM>()
  private lateinit var requestQueue: RequestQueue
  private lateinit var owner: String
  private lateinit var listVetAdapter: ListVetAdapter
  private val controlLiveData = MutableLiveData<Boolean>()
  private lateinit var queryVet: String
  private val vetListLiveData = MutableLiveData<List<ListVetModel>>()

  override fun onInitialized(): Unit {
    requestQueue = Volley.newRequestQueue(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val ownerID = intent.extras?.getString("ownerID")
    if (ownerID != null) {
      owner = ownerID
    }
    binding.vetSearchScreenVM = viewModel

    // Initialize and set up the RecyclerView with the ListVetAdapter
    val dummyList = emptyList<ListVetModel>()
    listVetAdapter = ListVetAdapter(dummyList)
    binding.recyclerListVet.adapter = listVetAdapter

    // Optional: Set up item click listener for RecyclerView items
    listVetAdapter.setOnItemClickListener(object : ListVetAdapter.OnItemClickListener {
      @SuppressLint("UseCompatLoadingForDrawables")
      override fun onItemClick(view: View, position: Int, item: ListVetModel) {
        val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/petowners/updateVet/${item.vetID}/$owner"
        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.PUT, url, null,
          { response ->
            // Handle the response
            if (response != null) {
              view.background = getDrawable(R.drawable.rectangle_bg_green_600_border_gray_51_radius_14)
              for (i in 0 until binding.recyclerListVet.childCount) {
                val otherView = binding.recyclerListVet.getChildAt(i)
                if (otherView != view) {
                  otherView.background = getDrawable(R.drawable.rectangle_bg_blue_600_radius_14)
                }
              }

              Toast.makeText(this@VetSearchScreenActivity, "Vet Change Successful", Toast.LENGTH_SHORT).show()
            }
          },
          { error ->
            // Handle errors
            Log.e("YourActivity", "Error: ${error.message}")
          })

        requestQueue.add(jsonObjectRequest)
      }
    })

    val searchView = findViewById<SearchView>(R.id.searchView)

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
          queryVet = query
          postReq()
        }
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return true
      }
    })

    vetListLiveData.observe(this) { vetList ->
      // Update UI with vetList
      listVetAdapter.updateData(vetList)
      binding.recyclerListVet.adapter = listVetAdapter
      if (vetList.isNotEmpty()) {
        binding.recyclerListVet.visibility = View.VISIBLE
      } else {
        binding.recyclerListVet.visibility = View.GONE
      }
    }

  }

  fun postReq() {
    val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/veterinarians/search/$queryVet"
    val jsonArrayRequest = JsonArrayRequest(
      Request.Method.GET, url, null,
      { response ->
        // Handle the response
        if (response != null) {
          val vetList = mutableListOf<ListVetModel>()

          for (i in 0 until response.length()) {
            val vetObject = response.getJSONObject(i)
            val vetID = vetObject.getString("vetID")
            val petOwnersArray = vetObject.getJSONArray("petOwners")
            var isFound = false
            for (j in 0 until petOwnersArray.length()) {
              val petOwnerObject = petOwnersArray.getJSONObject(j)
              val petOwnerID = petOwnerObject.getString("petOwnerID")

              if (petOwnerID == owner) {
                isFound = true
                break
              }
            }
            // Extract relevant information from the vetObject

            val vetName = vetObject.getJSONObject("user").getString("name")
            val listVetRowModel = ListVetModel(vetName, vetID, isFound)
            vetList.add(listVetRowModel)
          }

          // Set the result in the LiveData
          vetListLiveData.value = vetList

          listVetAdapter.updateData(vetList)
          binding.recyclerListVet.adapter = listVetAdapter
          if (vetList.isNotEmpty()) {
            binding.recyclerListVet.visibility = View.VISIBLE
          } else {
            binding.recyclerListVet.visibility = View.GONE
          }
        }
      },
      { error ->
        // Handle errors
        vetListLiveData.value = emptyList()
        Log.e("YourActivity", "Error: ${error.message}")
      })

    requestQueue.add(jsonArrayRequest)

  }
  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
  }

  fun onClickRecyclerVet(
    view: View,
    position: Int,
    item: ListVetModel
  ): Unit {
    when(view.id) {
      //TODO Write the vet green style with api
    }
  }

  companion object {
    const val TAG: String = "VET_SEARCH_SCREEN_ACTIVITY"
  }
}
