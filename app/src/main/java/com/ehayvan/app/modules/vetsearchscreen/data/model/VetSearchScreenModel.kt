package com.ehayvan.app.modules.vetsearchscreen.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class VetSearchScreenModel(
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_dobby_clinic)
  ,
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.msg_alice_ruth)
  ,
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.msg_guy_hawkings)
  ,
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.msg_hanna_blair)

)
