 package com.heller.climaApp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heller.climaApp.AppViewModel
import com.heller.climaApp.R
import com.heller.climaApp.apiServices.LocationBean
import com.heller.climaApp.databinding.FragmentFirstBinding
import com.heller.climaApp.view.adapters.MyLocationRecyclerViewAdapter


 class LocationFragment : Fragment(), LocationListener {

    private lateinit var mViewModel: AppViewModel
    private lateinit var mBinding: FragmentFirstBinding
    private lateinit var mAdapter: MyLocationRecyclerViewAdapter
    private lateinit var mLayout:LinearLayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        mBinding  = FragmentFirstBinding.inflate(inflater,container,false)

        mAdapter = MyLocationRecyclerViewAdapter(this)
        mLayout = LinearLayoutManager(context)
        mBinding.recyclerViewLocations.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = mAdapter
        }

        mBinding.editTextSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.getLocations(s.toString()).observe(viewLifecycleOwner,{
                   it.let {
                      if(it!=null && it.isNotEmpty()){
                          mAdapter.updateItems(it)
                          mBinding.lottie.visibility = View.GONE
                          mBinding.recyclerViewLocations.visibility = View.VISIBLE
                      }else{
                          mAdapter.updateItems(emptyList())
                          mBinding.lottie.visibility = View.VISIBLE
                          mBinding.recyclerViewLocations.visibility = View.GONE
                      }
                   }
                })
            }
        })

        return mBinding.root
    }
    
    override fun onClickLocation(obj: LocationBean) {

        val bundle = Bundle()
        bundle.putString("title",obj.title)
        bundle.putString("woeid",obj.woeid)
        bundle.putString("location_type",obj.locationType)

        findNavController().navigate(
            R.id.action_FirstFragment_to_SecondFragment,
                bundle
        )
    }
}