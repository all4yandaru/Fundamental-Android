package com.project.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail_category.*

class DetailCategoryFragment : Fragment() {

    //TODO 13: buat utk nangkep data nya
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataName = arguments?.getString(CategoryFragment.EXTRA_NAME)
        val dataDescription = arguments?.getLong(CategoryFragment.EXTRA_STOCK)

        tv_category_name.text = dataName
        tv_category_description.text = "Stock : $dataDescription"

        btn_profile.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment)
        )
    }
}