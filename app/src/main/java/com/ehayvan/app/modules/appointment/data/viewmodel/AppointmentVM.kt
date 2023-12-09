package com.ehayvan.app.modules.appointment.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.appointment.`data`.model.AppointmentModel
import org.koin.core.KoinComponent

class AppointmentVM : ViewModel(), KoinComponent {
  val appointmentModel: MutableLiveData<AppointmentModel> = MutableLiveData(AppointmentModel())

  var navArguments: Bundle? = null
}
