package com.bonacode.securehome.ui.feature.setup.enterphonenumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bonacode.securehome.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentSetupEnterPhoneNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupEnterPhoneNumberFragment :
    ViewModelFragment<SetupEnterPhoneNumberViewModel, FragmentSetupEnterPhoneNumberBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSetupEnterPhoneNumberBinding = FragmentSetupEnterPhoneNumberBinding.inflate(inflater, container, false)

    override val viewModel: SetupEnterPhoneNumberViewModel by activityViewModels()
}
