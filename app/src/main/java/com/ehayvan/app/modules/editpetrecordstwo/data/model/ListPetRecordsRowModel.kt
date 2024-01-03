package com.ehayvan.app.modules.editpetrecordstwo.data.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class ListPetRecordsRowModel(
    var txtTitleOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_clevor),
    var txtDate: String? = MyApp.getInstance().resources.getString(R.string.lbl_11_03_2023),
    var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_4_drops_left),
    var txtDuration: String? = MyApp.getInstance().resources.getString(R.string.lbl_every_5_days),
    var medicationID: String? = null,
)