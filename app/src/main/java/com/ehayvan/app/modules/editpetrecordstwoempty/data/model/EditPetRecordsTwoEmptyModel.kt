package com.ehayvan.app.modules.editpetrecordstwoempty.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class EditPetRecordsTwoEmptyModel(

  var txtTitleOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_no_drops_added)
  ,
  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)


)
