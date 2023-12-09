package com.ehayvan.app.modules.veterinariandashboard.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.veterinariandashboard.`data`.model.ListtitleRowModel
import com.ehayvan.app.modules.veterinariandashboard.`data`.model.VeterinarianDashboardModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class VeterinarianDashboardVM : ViewModel(), KoinComponent {
  val veterinarianDashboardModel: MutableLiveData<VeterinarianDashboardModel> =
      MutableLiveData(VeterinarianDashboardModel())

  var navArguments: Bundle? = null

  val listtitleList: MutableLiveData<MutableList<ListtitleRowModel>> =
      MutableLiveData(mutableListOf())
}
