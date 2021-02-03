package com.bonacode.securehome.ui.feature.setup.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentSetupIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupIntroFragment : ViewModelFragment<SetupIntroViewModel, FragmentSetupIntroBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSetupIntroBinding = FragmentSetupIntroBinding.inflate(inflater, container, false)

    override val viewModel: SetupIntroViewModel by viewModels()
}
