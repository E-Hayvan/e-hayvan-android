package com.ehayvan.app.modules.viewprofile.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.viewprofile.`data`.model.ViewProfileModel
import org.koin.core.KoinComponent

class ViewProfileVM : ViewModel(), KoinComponent {
  val viewProfileModel: MutableLiveData<ViewProfileModel> = MutableLiveData(ViewProfileModel())

  var navArguments: Bundle? = null

}
