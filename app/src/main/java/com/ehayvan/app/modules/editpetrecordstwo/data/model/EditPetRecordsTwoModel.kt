package com.ehayvan.app.modules.editpetrecordstwo.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class EditPetRecordsTwoModel(

  var txtTitleOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_clevor)
  ,
  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_drops_left)
  ,
  var txtDuration: String? = MyApp.getInstance().resources.getString(R.string.lbl_every_5_days),
  var txtTitle: String? = "Drops"

)
