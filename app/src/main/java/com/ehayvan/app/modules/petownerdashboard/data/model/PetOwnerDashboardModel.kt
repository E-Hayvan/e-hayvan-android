package com.ehayvan.app.modules.petownerdashboard.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class PetOwnerDashboardModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHello: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUser: String? = MyApp.getInstance().resources.getString(R.string.lbl_esther)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCount: String? = "2"
  ,
)
