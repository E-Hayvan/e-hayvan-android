package com.ehayvan.app.modules.appointment.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class AppointmentModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.msg_reserve_an_appo)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentOneValue: String? = null
)
