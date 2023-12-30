package com.ehayvan.app.modules.veterinariandashboard.ui

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityVeterinarianDashboardBinding
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.ui.ListPetAdapter
import com.ehayvan.app.modules.veterinariandashboard.`data`.model.ListtitleRowModel
import com.ehayvan.app.modules.veterinariandashboard.`data`.viewmodel.VeterinarianDashboardVM
import org.json.JSONException
import org.json.JSONObject
import kotlin.Int
import kotlin.String
import kotlin.Unit

class VeterinarianDashboardActivity :
    BaseActivity<ActivityVeterinarianDashboardBinding>(R.layout.activity_veterinarian_dashboard) {
  private val viewModel: VeterinarianDashboardVM by viewModels<VeterinarianDashboardVM>()
  private lateinit var requestQueue: RequestQueue
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("vetID")
    val vetID = intent.extras?.getString("vetID")
    requestQueue = Volley.newRequestQueue(this)
    makeGetRequest()
  }

  private fun makeGetRequest() {
    val url = "http://192.168.0.19:8080/api/veterinarians/7"

    val jsonObjectRequest2 = JsonObjectRequest(
      Request.Method.GET, url, null,
      { response ->
        // Handle the response
        val petName = response.optString("vetID")
        val dummyList = listOf(
          ListtitleRowModel(petName, "Price 1"),
          ListtitleRowModel("Pet 2", "Price 2"),
        )
        val listAppointmentAdapter = ListtitleAdapter(dummyList)
        binding.recyclerListtitle.adapter = listAppointmentAdapter
        // Initialize and set up the RecyclerView with the ListPetAdapter
        listAppointmentAdapter.setOnItemClickListener(object : ListtitleAdapter.OnItemClickListener {
          override fun onItemClick(view: View, position: Int, item: ListtitleRowModel) {
            onClickRecyclerListtitle(view, position, item)
          }
        })

        binding.veterinarianDashboardVM = viewModel
        Log.d("YourActivity", "Pet Name: $petName")
      },
      { error ->
        // Handle errors
        Log.e("YourActivity", "Error: ${error.message}")
      }
    )

     requestQueue.add(jsonObjectRequest2)
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerListtitle(
    view: View,
    position: Int,
    item: ListtitleRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "VETERINARIAN_DASHBOARD_ACTIVITY"

  }
}
