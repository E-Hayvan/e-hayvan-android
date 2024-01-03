package com.ehayvan.app.modules.editpetrecordsthree.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsThreeBinding
import com.ehayvan.app.modules.editpetrecordsthree.`data`.viewmodel.EditPetRecordsThreeVM
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.veterinariandashboard.ui.VeterinarianDashboardActivity
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.String
import kotlin.Unit

class EditPetRecordsThreeActivity :
    BaseActivity<ActivityEditPetRecordsThreeBinding>(R.layout.activity_edit_pet_records_three) {
  private val viewModel: EditPetRecordsThreeVM by viewModels<EditPetRecordsThreeVM>()
  private lateinit var requestQueue: RequestQueue
  private lateinit var medicationID: String
  private lateinit var petID: String
  private lateinit var vetID: String
  private lateinit var type: String
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    vetID = intent.extras?.getString("vetID").toString()
    requestQueue = Volley.newRequestQueue(this)
    viewModel.editPetRecordsThreeModel.value?.txtTitle = intent.extras?.getString("medType")
    viewModel.editPetRecordsThreeModel.value?.txtPetName = intent.extras?.getString("petName")
    type = intent.extras?.getString("type").toString()
    petID = intent.extras?.getString("petID").toString()
    if (type == "edit") {
      medicationID = intent.extras?.getString("medicationID").toString()
      binding.btnAdd.text = getString(R.string.edit)
    }

    binding.editPetRecordsThreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.etInputContentOne.setOnClickListener {
      showDatePickerDialog(binding.etInputContentOne)
    }
    binding.btnAdd.setOnClickListener {
      val body1 = binding.etInputContent.text
      val body2 = binding.etInputContentOne.text
      val body3 = binding.etInputContentTwo.text
      val body4 = binding.etInputContentThree.text
      if (body1.isEmpty())
        binding.etInputContent.error = "You should enter the medication name"
      if (body2.isEmpty())
        binding.etInputContentOne.error = "You should enter the beginning date"
      if (body3.isEmpty())
        binding.etInputContentTwo.error = "You should enter the frequency of drop"
      if (body4.isEmpty())
        binding.etInputContentThree.error = "You should enter the number of drop"
      if (body1.isNotEmpty() && body2.isNotEmpty() && body3.isNotEmpty() && body4.isNotEmpty()) {
        var url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/medications"
        var jsonBody = JSONObject()
        var requestMethod = Request.Method.POST
        if (type == "add") {
          url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/medications"
          jsonBody = JSONObject()
          try {
            // Create the user object
            val userObject = JSONObject()
            val scheduleObject = JSONObject()
            userObject.put("medicationName", body1)
            var medTypeID = 0
            if (viewModel.editPetRecordsThreeModel.value?.txtTitle == "Vaccines")
              medTypeID = 4
            if (viewModel.editPetRecordsThreeModel.value?.txtTitle == "Drops")
              medTypeID = 5
            if (viewModel.editPetRecordsThreeModel.value?.txtTitle == "Medications")
              medTypeID = 6
            userObject.put("medTypeID", medTypeID)
            scheduleObject.put("beginningDate", body2)
            scheduleObject.put("doseFrequency", body3)
            scheduleObject.put("doseCount", body4)
            userObject.put("scheduleID", scheduleObject)
            userObject.put("petID", petID)
            jsonBody = userObject
          } catch (e: JSONException) {
            e.printStackTrace()
          }
        }
        if (type == "edit") {
          url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/medications/update/$medicationID"
          jsonBody = JSONObject()
          requestMethod = Request.Method.PUT
          try {
            // Create the user object
            val userObject = JSONObject()
            val scheduleObject = JSONObject()
            userObject.put("medicationName", body1)
            var medTypeID = 0
            if (viewModel.editPetRecordsThreeModel.value?.txtTitle == "Vaccines")
              medTypeID = 4
            if (viewModel.editPetRecordsThreeModel.value?.txtTitle == "Drops")
              medTypeID = 5
            if (viewModel.editPetRecordsThreeModel.value?.txtTitle == "Medications")
              medTypeID = 6
            userObject.put("medTypeID", medTypeID)
            scheduleObject.put("beginningDate", body2)
            scheduleObject.put("doseFrequency", body3)
            scheduleObject.put("doseCount", body4)
            userObject.put("scheduleID", scheduleObject)
            userObject.put("petID", petID)
            jsonBody = userObject
          } catch (e: JSONException) {
            e.printStackTrace()
          }
        }

        val jsonObjectRequest = JsonObjectRequest(
          requestMethod, url, jsonBody,
          { response ->
            // Handle the response
            if (response != null) {
              val bundle = Bundle()
              val intent = Intent(this, VeterinarianDashboardActivity::class.java)
              bundle.putString("vetID", vetID)
              intent.putExtras(bundle)
              Toast.makeText(this@EditPetRecordsThreeActivity, "Record Successfully Edited", Toast.LENGTH_SHORT).show()
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
  }

  private fun showDatePickerDialog(etInputContent: TextView) {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
      this,
      { _, year, month, day ->
        val selectedDate = formatDate(year, month + 1, day)
        etInputContent.text = selectedDate
      },
      currentYear,
      currentMonth,
      currentDay
    )

    datePickerDialog.show()
  }
  private fun formatDate(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day)
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
  }
  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_THREE_ACTIVITY"

  }
}
