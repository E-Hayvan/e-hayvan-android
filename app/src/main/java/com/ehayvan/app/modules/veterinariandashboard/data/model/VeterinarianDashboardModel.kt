package com.ehayvan.app.modules.veterinariandashboard.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class VeterinarianDashboardModel(
  var txtHello: String? = MyApp.getInstance().resources.getString(R.string.lbl_hello)
  ,
  var txtUser: String? = MyApp.getInstance().resources.getString(R.string.lbl_alice)
  ,
  var txtCount: String? = "2"
  ,
  var txtText: String? = MyApp.getInstance().resources.getString(R.string.msg_today_s_appoint)

)
