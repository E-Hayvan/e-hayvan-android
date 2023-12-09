package com.ehayvan.app.modules.viewpetrecordsone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.viewpetrecordsone.`data`.model.ViewPetRecordsOneModel
import org.koin.core.KoinComponent

class ViewPetRecordsOneVM : ViewModel(), KoinComponent {
  val viewPetRecordsOneModel: MutableLiveData<ViewPetRecordsOneModel> =
      MutableLiveData(ViewPetRecordsOneModel())

  var navArguments: Bundle? = null
}
