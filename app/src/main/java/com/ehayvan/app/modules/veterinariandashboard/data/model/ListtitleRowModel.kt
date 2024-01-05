package com.ehayvan.app.modules.veterinariandashboard.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class ListtitleRowModel(
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_bubu)
  ,
  var txtType: String? = MyApp.getInstance().resources.getString(R.string.msg_dog_2_years_o2)
  ,
  var txtAge: String? = MyApp.getInstance().resources.getString(R.string.msg_dog_2_years_o2)
  ,
  var txtTime: String? = null,
  var appointmentID: String? = null,
  var petID: String? = null
)
