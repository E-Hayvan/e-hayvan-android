package com.ehayvan.app.modules.viewpetprofile.ui

import android.view.View
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewPetProfileBinding
import com.ehayvan.app.modules.viewpetprofile.`data`.model.ContentRowModel
import com.ehayvan.app.modules.viewpetprofile.`data`.viewmodel.ViewPetProfileVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ViewPetProfileActivity :
    BaseActivity<ActivityViewPetProfileBinding>(R.layout.activity_view_pet_profile) {
  private val viewModel: ViewPetProfileVM by viewModels<ViewPetProfileVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val contentAdapter = ContentAdapter(viewModel.contentList.value?:mutableListOf())
    binding.recyclerContent.adapter = contentAdapter
    contentAdapter.setOnItemClickListener(
    object : ContentAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ContentRowModel) {
        onClickRecyclerContent(view, position, item)
      }
    }
    )
    viewModel.contentList.observe(this) {
      contentAdapter.updateData(it)
    }
    binding.viewPetProfileVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerContent(
    view: View,
    position: Int,
    item: ContentRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "VIEW_PET_PROFILE_ACTIVITY"

  }
}