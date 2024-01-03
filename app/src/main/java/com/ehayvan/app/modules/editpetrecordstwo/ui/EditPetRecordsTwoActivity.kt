package com.ehayvan.app.modules.editpetrecordstwo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsTwoBinding
import com.ehayvan.app.modules.editpetrecordsthree.ui.EditPetRecordsThreeActivity
import com.ehayvan.app.modules.editpetrecordstwo.data.model.ListPetRecordsRowModel
import com.ehayvan.app.modules.editpetrecordstwo.data.viewmodel.EditPetRecordsTwoVM
import com.ehayvan.app.modules.veterinariandashboard.data.model.ListtitleRowModel
import com.ehayvan.app.modules.veterinariandashboard.ui.ListtitleAdapter
import org.json.JSONObject
import kotlin.Unit

class EditPetRecordsTwoActivity :
  BaseActivity<ActivityEditPetRecordsTwoBinding>(R.layout.activity_edit_pet_records_two) {
  private val viewModel: EditPetRecordsTwoVM by viewModels<EditPetRecordsTwoVM>()
  private lateinit var listAdapter: ListPetRecordsAdapter
  private lateinit var requestQueue: RequestQueue
  private lateinit var petID: String
  private lateinit var vetID: String

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    vetID = intent.extras?.getString("vetID").toString()
    requestQueue = Volley.newRequestQueue(this)
    viewModel.editPetRecordsTwoModel.value?.txtTitle = intent.extras?.getString("medType")
    viewModel.editPetRecordsTwoModel.value?.txtPetName = intent.extras?.getString("petName")
    petID = intent.extras?.getString("petID").toString()
    val dummyList: List<ListPetRecordsRowModel> = emptyList()
    listAdapter = ListPetRecordsAdapter(dummyList)
    binding.recyclerListPetRecords.adapter = listAdapter
    listAdapter.setOnItemClickListener(object : ListPetRecordsAdapter.OnItemClickListener {
      override fun onItemClick(view: android.view.View, position: kotlin.Int, item: ListPetRecordsRowModel) {
        val bundle = Bundle()
        val intent = Intent(this@EditPetRecordsTwoActivity, EditPetRecordsThreeActivity::class.java)
        bundle.putString("petName", viewModel.editPetRecordsTwoModel.value?.txtPetName)
        bundle.putString("petID", petID)
        bundle.putString("vetID", vetID)
        bundle.putString("medType", viewModel.editPetRecordsTwoModel.value?.txtTitle)
        bundle.putString("medicationID", item.medicationID)
        bundle.putString("type", "edit")
        intent.putExtras(bundle)
        startActivity(intent)
      }
    })
    binding.editPetRecordsTwoVM = viewModel
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
            if (medType == viewModel.editPetRecordsTwoModel.value?.txtTitle) {
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

        binding.editPetRecordsTwoVM = viewModel
      },
      { error ->
        // Handle errors
        Log.e("YourActivity", "Error: ${error.message}")
      })


    requestQueue.add(jsonObjectRequest)
  }
  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.btnAdd.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, EditPetRecordsThreeActivity::class.java)
      bundle.putString("petName", viewModel.editPetRecordsTwoModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("vetID", vetID)
      bundle.putString("medType", viewModel.editPetRecordsTwoModel.value?.txtTitle)
      bundle.putString("type", "add")
      intent.putExtras(bundle)
      startActivity(intent)
    }
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

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_TWO_ACTIVITY"
  }
}
