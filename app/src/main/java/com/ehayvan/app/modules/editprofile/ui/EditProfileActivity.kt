package com.ehayvan.app.modules.editprofile.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditProfileBinding
import com.ehayvan.app.modules.editprofile.data.viewmodel.EditProfileVM

class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile) {
    private val viewModel: EditProfileVM by viewModels<EditProfileVM>()

    override fun onInitialized(): Unit {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.editProfileVM = viewModel
    }

    override fun setUpClicks(): Unit {
        binding.imageArrowleft.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val TAG: String = "EDIT_PROFILE_ACTIVITY"

    }
}