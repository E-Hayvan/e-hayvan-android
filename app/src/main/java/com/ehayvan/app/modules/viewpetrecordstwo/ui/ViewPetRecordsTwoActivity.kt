package com.ehayvan.app.modules.viewpetrecordstwo.ui

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewPetRecordsTwoBinding
import com.ehayvan.app.modules.editpetrecordstwo.data.model.ListPetRecordsRowModel
import com.ehayvan.app.modules.editpetrecordstwo.ui.ListPetRecordsAdapter
import com.ehayvan.app.modules.viewpetrecordstwo.`data`.viewmodel.ViewPetRecordsTwoVM
import org.json.JSONObject
import kotlin.String
import kotlin.Unit

class ViewPetRecordsTwoActivity :
    BaseActivity<ActivityViewPetRecordsTwoBinding>(R.layout.activity_view_pet_records_two) {
  private val viewModel: ViewPetRecordsTwoVM by viewModels<ViewPetRecordsTwoVM>()
  private lateinit var listAdapter: ListPetRecordsAdapter
  private lateinit var requestQueue: RequestQueue
  private lateinit var petID: String
  private lateinit var ownerID: String

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    ownerID = intent.extras?.getString("ownerID").toString()
    requestQueue = Volley.newRequestQueue(this)
    viewModel.viewPetRecordsTwoModel.value?.txtTitle = intent.extras?.getString("medType")
    viewModel.viewPetRecordsTwoModel.value?.txtPetName = intent.extras?.getString("petName")
    petID = intent.extras?.getString("petID").toString()
    val dummyList: List<ListPetRecordsRowModel> = emptyList()
    listAdapter = ListPetRecordsAdapter(dummyList)
    binding.recyclerListPetRecords.adapter = listAdapter
    listAdapter.setOnItemClickListener(object : ListPetRecordsAdapter.OnItemClickListener {
      override fun onItemClick(view: android.view.View, position: kotlin.Int, item: ListPetRecordsRowModel) {
        onClickRecyclerListPetRecords(view, position, item)
      }
    })

    binding.viewPetRecordsTwoVM = viewModel
    getMedications()
  }

  private fun getMedications() {
    val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/pets/$petID"
    val body1: ImageView = findViewById(R.id.imageIllustration)
    val body2: TextView = findViewById(R.id.txtTitleOne)
    val recyclerList: RecyclerView = findViewById(R.id.recyclerListPetRecords)
    val jsonObjectRequest = JsonObjectRequest(
      Request.Method.GET, url, null,
      { response ->
        // Handle the response
        if (response != null) {
          val jsonString = response.toString()
          val jsonObject = JSONObject(jsonString)
          val medicationsArray = jsonObject.getJSONArray("medications")
          val medicationList = mutableListOf<ListPetRecordsRowModel>()
          for (i in 0 until medicationsArray.length()) {
            val medication = medicationsArray.getJSONObject(i)
            val schedule = medication.getJSONObject("scheduleID")
            val medTypeID = medication.getInt("medTypeID")
            var medType = ""
            if (medTypeID == 4)
              medType = "Vaccines"
            if (medTypeID == 5)
              medType = "Drops"
            if (medTypeID == 6)
              medType = "Medications"
            if (medType == viewModel.viewPetRecordsTwoModel.value?.txtTitle) {
              val listPetRecords = ListPetRecordsRowModel(medication.getString("medicationName"), schedule.getString("beginningDate"), schedule.getString("doseCount"), schedule.getString("doseFrequency"), medication.getString("medicationID"))
              medicationList.add(listPetRecords)
            } else {
              continue
            }
          }

          listAdapter.updateData(medicationList)
          binding.recyclerListPetRecords.adapter = listAdapter
          if (medicationList.isNotEmpty()) {
            body1.visibility = View.GONE
            body2.visibility = View.GONE
            recyclerList.visibility = View.VISIBLE
          } else {
            body1.visibility = View.VISIBLE
            body2.visibility = View.VISIBLE
            recyclerList.visibility = View.GONE
          }
        }

        binding.viewPetRecordsTwoVM = viewModel
      },
      { error ->
        // Handle errors
        Log.e("YourActivity", "Error: ${error.message}")
      })

    requestQueue.add(jsonObjectRequest)
  }

  fun onClickRecyclerListPetRecords(
    view: android.view.View,
    position: kotlin.Int,
    item: ListPetRecordsRowModel
  ): Unit {
    // Handle click events if needed
    when (view.id) {
    }
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
  }

  companion object {
    const val TAG: String = "VIEW_PET_RECORDS_TWO_ACTIVITY"

  }
}
