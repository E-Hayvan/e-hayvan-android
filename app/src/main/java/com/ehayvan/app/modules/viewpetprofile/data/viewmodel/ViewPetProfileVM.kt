package com.ehayvan.app.modules.viewpetprofile.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.viewpetprofile.`data`.model.ContentRowModel
import com.ehayvan.app.modules.viewpetprofile.`data`.model.ViewPetProfileModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ViewPetProfileVM : ViewModel(), KoinComponent {
  val viewPetProfileModel: MutableLiveData<ViewPetProfileModel> =
      MutableLiveData(ViewPetProfileModel())

  var navArguments: Bundle? = null

  val contentList: MutableLiveData<MutableList<ContentRowModel>> = MutableLiveData(mutableListOf())
}
