package com.ehayvan.app.modules.addpetprofile.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class AddPetProfileModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_add_pet_profile)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etLanguageValue: String? = null
)
