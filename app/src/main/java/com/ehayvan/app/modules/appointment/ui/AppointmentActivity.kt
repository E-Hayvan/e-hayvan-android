package com.ehayvan.app.modules.appointment.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityAppointmentBinding
import com.ehayvan.app.modules.appointment.`data`.viewmodel.AppointmentVM
import kotlin.String
import kotlin.Unit

class AppointmentActivity : BaseActivity<ActivityAppointmentBinding>(R.layout.activity_appointment)
    {
  private val viewModel: AppointmentVM by viewModels<AppointmentVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.appointmentVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "APPOINTMENT_ACTIVITY"

  }
}
