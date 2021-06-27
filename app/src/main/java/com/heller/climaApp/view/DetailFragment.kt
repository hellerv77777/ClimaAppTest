package com.heller.climaApp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.heller.climaApp.AppViewModel
import com.heller.climaApp.databinding.FragmentSecondBinding
import com.heller.climaApp.view.adapters.MyLocationDetailRecyclerViewAdapter
import com.heller.climaApp.view.adapters.RecyclerViewPositionHelper

class DetailFragment : Fragment() {

    private lateinit var mViewModel: AppViewModel
    private lateinit var mBinding: FragmentSecondBinding
    private lateinit var mAdapter: MyLocationDetailRecyclerViewAdapter
    private lateinit var mLayout: LinearLayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        mBinding  = FragmentSecondBinding.inflate(inflater,container,false)

       val woeid = arguments?.getString("woeid").toString()

        mBinding.textViewCity.text = arguments?.getString("title").toString()
        mBinding.textType.text = arguments?.getString("location_type").toString()

        val linearSnapHelper: LinearSnapHelper = RecyclerViewPositionHelper()
        linearSnapHelper.attachToRecyclerView(mBinding.recyclerDetail)

        mAdapter = MyLocationDetailRecyclerViewAdapter()
        mLayout = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        mBinding.recyclerDetail.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = mAdapter
        }

        mViewModel.getDataLocation(woeid).observe(viewLifecycleOwner,{
            if(it!=null) {
                mAdapter.updateItems(it.consolidated_weather)
            }
        })

        return mBinding.root
    }
}