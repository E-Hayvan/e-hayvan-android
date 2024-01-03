package com.ehayvan.app.modules.veterinariandashboard.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityVeterinarianDashboardBinding
import com.ehayvan.app.modules.editpetrecordsone.ui.EditPetRecordsOneActivity
import com.ehayvan.app.modules.editpetrecordstwo.ui.EditPetRecordsTwoActivity
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.veterinariandashboard.`data`.model.ListtitleRowModel
import com.ehayvan.app.modules.veterinariandashboard.`data`.viewmodel.VeterinarianDashboardVM
import com.ehayvan.app.modules.viewprofile.ui.ViewProfileActivity
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.Int
import kotlin.String
import kotlin.Unit

class VeterinarianDashboardActivity :
    BaseActivity<ActivityVeterinarianDashboardBinding>(R.layout.activity_veterinarian_dashboard) {
  private val viewModel: VeterinarianDashboardVM by viewModels<VeterinarianDashboardVM>()
  private lateinit var requestQueue: RequestQueue
  private lateinit var vetID: String
  private lateinit var listAdapter: ListtitleAdapter
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("vetID")
    vetID = intent.extras?.getString("vetID").toString()
    requestQueue = Volley.newRequestQueue(this)
    getAppointments()
  }

  private fun getAppointments() {
    val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/veterinarians/$vetID"
    val body1: ImageView = findViewById(R.id.imageEmptySpaceIll)
    val body2: TextView = findViewById(R.id.txtTitle)
    val body3: TextView = findViewById(R.id.txtBody)
    val recyclerList: RecyclerView = findViewById(R.id.recyclerListtitle)
    val container : LinearLayout = findViewById(R.id.linearNavigationSe)
    val jsonObjectRequest = JsonObjectRequest(
      Request.Method.GET, url, null,
      { response ->
        // Handle the response
        if (response != null) {
          val jsonString = response.toString()
          val jsonObject = JSONObject(jsonString)
          val appointmentsArray = jsonObject.getJSONArray("appointments")
          val userName = jsonObject.getJSONObject("user")
          viewModel.veterinarianDashboardModel.value?.txtUser = userName.getString("name") + " " + userName.getString("surname")
          val appointmentList = mutableListOf<ListtitleRowModel>()
          for (i in 0 until appointmentsArray.length()) {
            var isFound = false
            var petName = ""
            var petType = ""
            var petAge = ""
            val appointment = appointmentsArray.getJSONObject(i)
            val petOwnersArray = jsonObject.getJSONArray("petOwners")
            val petOwnerID = appointment.getString("petOwnerID")
            val petID = appointment.getString("petID")
            val appointmentDate = appointment.getString("appointmentDate")
            val appointmentID = appointment.getString("appointmentID")
            for (j in 0 until petOwnersArray.length()) {
              if (isFound)
                break
              val petOwner = petOwnersArray.getJSONObject(j)
              val petOwnerID2 = petOwner.getString("petOwnerID")
              if (petOwnerID == petOwnerID2) {
                val petsArray = petOwner.getJSONArray("pets")
                for (z in 0 until petsArray.length()) {
                  if (isFound)
                    break
                  val pet = petsArray.getJSONObject(z)
                  val petID2 = pet.getString("petID")
                  if (petID2 == petID) {
                    petName = pet.getString("petName")
                    val petType2 = pet.getInt("petTypeID")
                    if (petType2 == 1)
                      petType = "Cat"
                    if (petType2 == 2)
                      petType = "Dog"
                    if (petType2 == 3)
                      petType = "Bird"
                    if (petType2 == 4)
                      petType = "Other"
                    petAge = pet.getString("age")
                    isFound = true
                  }
                }
              }
            }
            if (petName != "") {
              val appointmentTime = extractTimeFromAppointmentDate(appointmentDate)
              val listtitleRowModel = ListtitleRowModel(petName, petType, petAge, appointmentTime, appointmentID, petID)
              appointmentList.add(listtitleRowModel)
            }
          }

          viewModel.veterinarianDashboardModel.value?.txtCount = appointmentList.size.toString()
          listAdapter.updateData(appointmentList)
          binding.recyclerListtitle.adapter = listAdapter
          if (appointmentList.isNotEmpty()) {
            body1.visibility = View.GONE
            body2.visibility = View.GONE
            body3.visibility = View.GONE
            container.visibility = View.VISIBLE
            recyclerList.visibility = View.VISIBLE
          } else {
            body1.visibility = View.VISIBLE
            body2.visibility = View.VISIBLE
            body3.visibility = View.VISIBLE
            container.visibility = View.GONE
            recyclerList.visibility = View.GONE
          }
        }

        binding.veterinarianDashboardVM = viewModel
      },
      { error ->
        // Handle errors
        Log.e("YourActivity", "Error: ${error.message}")
      })


    requestQueue.add(jsonObjectRequest)
  }

  override fun setUpClicks(): Unit {
    val dummyList = listOf(ListtitleRowModel("",""))
    listAdapter = ListtitleAdapter(dummyList)
    listAdapter.setOnItemClickListener(object : ListtitleAdapter.OnItemClickListener {
      override fun onItemClick(view: View, position: Int, item: ListtitleRowModel) {
        val bundle = Bundle()
        val intent = Intent(view.context, EditPetRecordsOneActivity::class.java)
        bundle.putString("petName", item.txtTitle)
        bundle.putString("petID", item.petID)
        bundle.putString("vetID", vetID)
        intent.putExtras(bundle)
        startActivity(intent)
      }
      override fun onImageClick(view: View, position: Int, item: ListtitleRowModel) {
        val url = "http://ehayvan.eu-north-1.elasticbeanstalk.com/api/appointments/${item.appointmentID}"
        val jsonObjectRequest = JsonObjectRequest(
          Request.Method.DELETE, url, null,
          { response ->
            // Handle the response
            if (response != null) {
              Toast.makeText(this@VeterinarianDashboardActivity, "Appointment Successfully Removed", Toast.LENGTH_SHORT).show()
              val bundle = Bundle()
              val intent = Intent(this@VeterinarianDashboardActivity, VeterinarianDashboardActivity::class.java)
              bundle.putString("vetID", vetID)
              intent.putExtras(bundle)
              startActivity(intent)
              finishAffinity()
            }
          },
          { error ->
            Toast.makeText(this@VeterinarianDashboardActivity, "Appointment Successfully Removed", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            val intent = Intent(this@VeterinarianDashboardActivity, VeterinarianDashboardActivity::class.java)
            bundle.putString("vetID", vetID)
            intent.putExtras(bundle)
            startActivity(intent)
            finishAffinity()
            Log.e("YourActivity", "Error: ${error.message}")
          })

        requestQueue.add(jsonObjectRequest)
      }
    })
    binding.frameStackvector.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, ViewProfileActivity::class.java)
      bundle.putString("vetID", vetID)
      intent.putExtras(bundle)
      startActivity(intent)
    }
  }

  fun extractTimeFromAppointmentDate(appointmentDate: String): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val parsedDate: Date? = try {
      dateFormat.parse(appointmentDate)
    } catch (e: Exception) {
      // Handle parsing error or return null
      null
    }

    return parsedDate?.let {
      val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
      timeFormat.format(it)
    }
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
