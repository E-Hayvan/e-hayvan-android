package com.ehayvan.app.modules.dashboard.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class DashboardModel(


  var txtName: String? = MyApp.getInstance().resources.getString(R.string.lbl_esther)
)
