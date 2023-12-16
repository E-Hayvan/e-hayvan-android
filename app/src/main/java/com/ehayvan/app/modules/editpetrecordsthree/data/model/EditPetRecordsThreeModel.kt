package com.ehayvan.app.modules.editpetrecordsthree.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class EditPetRecordsThreeModel(

  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,
  var etInputContentValue: String? = null,

  var etInputContentOneValue: String? = null,

  var etInputContentTwoValue: String? = null,

  var etInputContentThreeValue: String? = null
)
