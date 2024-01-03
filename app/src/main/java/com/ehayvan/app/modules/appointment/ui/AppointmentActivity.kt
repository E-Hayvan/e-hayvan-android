package com.ehayvan.app.modules.appointment.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityAppointmentBinding
import com.ehayvan.app.modules.appointment.`data`.viewmodel.AppointmentVM
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.String
import kotlin.Unit

class AppointmentActivity : BaseActivity<ActivityAppointmentBinding>(R.layout.activity_appointment)
    {
  private val viewModel: AppointmentVM by viewModels<AppointmentVM>()
      private lateinit var requestQueue: RequestQueue
      private lateinit var owner: String
      private lateinit var petID : String
      private var vetID : String = ""

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    requestQueue = Volley.newRequestQueue(this)
    petID = intent.extras?.getString("petID").toString()
    owner = intent.extras?.getString("ownerID").toString()
    viewModel.appointmentModel.value?.txtPetName = intent.extras?.getString("petName")
    val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/petowners/$owner"
    val jsonObjectRequest = JsonObjectRequest(
      Request.Method.GET, url, null,
      { response ->
        // Handle the response
        if (response != null) {
          val jsonString = response.toString()
          val jsonObject = JSONObject(jsonString)
          vetID = jsonObject.getString("vetID")
          binding.appointmentVM = viewModel
        }

      },
      { error ->
        // Handle errors
        binding.appointmentVM = viewModel
        Log.e("YourActivity", "Error: ${error.message}")
      })

    requestQueue.add(jsonObjectRequest)

  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.etInputContent.setOnClickListener {
      showDatePickerDialog(binding.etInputContent)
    }
    binding.etInputContentOne.setOnClickListener {
      showTimePickerDialog(binding.etInputContentOne)
    }
    binding.btnReserve.setOnClickListener {
      if (vetID != "null") {
        val date = binding.etInputContent.text
        val time = binding.etInputContentOne.text
        if (date.isEmpty())
          binding.etInputContent.error = "You should enter the date"
        if (time.isEmpty())
          binding.etInputContentOne.error = "You should enter the time"
        if (date.isNotEmpty() && time.isNotEmpty()) {
          val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/appointments"
          var jsonBody = JSONObject()
          try {
            // Create the user object
            val userObject = JSONObject()
            userObject.put("appointmentDate", "$date $time")
            userObject.put("petID", petID)
            userObject.put("vetID", vetID)
            userObject.put("petOwnerID", owner)
            jsonBody = userObject
          } catch (e: JSONException) {
            e.printStackTrace()
          }

          val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonBody,
            { response ->
              if (response != null) {
                Toast.makeText(this, "Appointment Successfully Reserved", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                val intent = Intent(this, PetOwnerDashboardActivity::class.java)
                bundle.putString("ownerID", owner)
                intent.putExtras(bundle)
                startActivity(intent)
                finishAffinity()
              }
            },
            { error ->
              // Handle errors
              Toast.makeText(this, "Appointment Failed", Toast.LENGTH_SHORT).show()
              Log.e("YourActivity", "Error: ${error.message}")
            })

          requestQueue.add(jsonObjectRequest)
        }
      } else {
        Toast.makeText(this, "You Should Select Veterinarian First", Toast.LENGTH_SHORT).show()
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

      private fun showTimePickerDialog(etInputContentOne: TextView) {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
          this,
          { _, hourOfDay, minute ->
            val selectedTime = formatTime(hourOfDay, minute)
            etInputContentOne.text = selectedTime
          },
          currentHour,
          currentMinute,
          true // 24-hour format
        )

        timePickerDialog.show()
      }

      private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
      }

      private fun formatTime(hourOfDay: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(calendar.time)
      }

  companion object {
    const val TAG: String = "APPOINTMENT_ACTIVITY"

  }
}
