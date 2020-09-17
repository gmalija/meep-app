package com.gmalija.meepapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<binding : ViewDataBinding, viewModel : BaseViewModel> : Fragment() {

    protected abstract val viewModel: viewModel
    private lateinit var bindingObject: binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingObject = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return bindingObject.root
    }

    /**
     * Get layout resource id which inflate in onCreateView.
     */
    abstract fun getLayoutResId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doDataBinding(view, savedInstanceState)
    }

    /**
     * Do your other stuff in init after binding layout.
     */
    abstract fun init(view: View, savedInstanceState: Bundle?)

    private fun doDataBinding(view: View, savedInstanceState: Bundle?) {
        // it is extra if you want to set life cycle owner in binding
        bindingObject.lifecycleOwner = viewLifecycleOwner
        init(view, savedInstanceState)
    }
    
}