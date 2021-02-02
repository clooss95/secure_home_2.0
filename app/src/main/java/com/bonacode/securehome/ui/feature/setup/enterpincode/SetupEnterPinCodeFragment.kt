package com.bonacode.securehome.ui.feature.setup.enterpincode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bonacode.securehome.application.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentSetupEnterPinCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupEnterPinCodeFragment :
    ViewModelFragment<SetupEnterPinCodeViewModel, FragmentSetupEnterPinCodeBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSetupEnterPinCodeBinding =
        FragmentSetupEnterPinCodeBinding.inflate(inflater, container, false)

    override val viewModel: SetupEnterPinCodeViewModel by activityViewModels()
}
