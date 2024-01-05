package com.ehayvan.app.modules.petownerdashboard.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class PetOwnerDashboardModel(
  var txtHello: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
  ,
  var txtUser: String? = MyApp.getInstance().resources.getString(R.string.lbl_esther)
  ,

  var txtCount: String? = "1"
  ,
)
