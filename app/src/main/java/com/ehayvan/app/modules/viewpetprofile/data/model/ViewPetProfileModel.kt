package com.ehayvan.app.modules.viewpetprofile.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class ViewPetProfileModel(

  var txtName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,
  var txtType: String? = MyApp.getInstance().resources.getString(R.string.lbl_dog)
  ,
  var txtAge: String? = MyApp.getInstance().resources.getString(R.string.lbl_3_years_old)
)
