package com.ehayvan.app.modules.appointment.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class AppointmentModel(

  var etInputContentValue: String? = null
  ,

  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,

  var etInputContentOneValue: String? = null
)
