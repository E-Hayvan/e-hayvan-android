package com.ehayvan.app.modules.viewpetrecordsone.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class ViewPetRecordsOneModel(
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.msg_view_pet_record)
  ,
  var txtTitleOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_vaccines)
  ,
  var txtTitleTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_drops)
  ,
  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,
  var txtTitleThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_medications)

)
