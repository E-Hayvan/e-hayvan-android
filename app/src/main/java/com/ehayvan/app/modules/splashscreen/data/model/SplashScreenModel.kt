package com.ehayvan.app.modules.splashscreen.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class SplashScreenModel(
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_e_h_yv_n)

)
