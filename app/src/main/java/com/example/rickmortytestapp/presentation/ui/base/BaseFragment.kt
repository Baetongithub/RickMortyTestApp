package com.example.rickmortytestapp.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val viewBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _vb: VB? = null
    val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _vb = viewBinding.invoke(inflater, container, false)

        initView()
        initViewModel()
        initCheckInternet()
        return vb.root
    }

    protected open fun initCheckInternet(){}
    protected open fun initViewModel(){}
    protected open fun initView(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}