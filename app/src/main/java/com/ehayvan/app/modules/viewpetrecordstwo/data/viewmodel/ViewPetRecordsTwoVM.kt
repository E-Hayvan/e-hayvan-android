package com.ehayvan.app.modules.viewpetrecordstwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.editpetrecordstwo.data.model.ListPetRecordsRowModel
import com.ehayvan.app.modules.viewpetrecordstwo.`data`.model.ViewPetRecordsTwoModel
import org.koin.core.KoinComponent

class ViewPetRecordsTwoVM : ViewModel(), KoinComponent {
  val viewPetRecordsTwoModel: MutableLiveData<ViewPetRecordsTwoModel> =
      MutableLiveData(ViewPetRecordsTwoModel())

  var navArguments: Bundle? = null

    val listPetRecords: MutableLiveData<MutableList<ListPetRecordsRowModel>> =
        MutableLiveData(mutableListOf())
}
