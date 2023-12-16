package com.ehayvan.app.modules.editpetrecordsone.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class EditPetRecordsOneModel(

  var txtPetName: String? = MyApp.getInstance().resources.getString(R.string.lbl_dudu)

)
