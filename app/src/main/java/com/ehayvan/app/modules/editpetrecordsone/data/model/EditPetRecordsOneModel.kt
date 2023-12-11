package com.ehayvan.app.modules.editpetrecordsone.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class EditPetRecordsOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.msg_edit_pet_record)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTitleOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_vaccines)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTitleTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_drops)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTitleThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_medications)

)
