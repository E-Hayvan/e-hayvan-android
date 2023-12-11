package com.ehayvan.app.modules.editpetrecordsthree.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class EditPetRecordsThreeModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtBody: String? = MyApp.getInstance().resources.getString(R.string.msg_edit_pet_record)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_drops)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)
  ,
  var etInputContentValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentOneValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentTwoValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etInputContentThreeValue: String? = null
)
