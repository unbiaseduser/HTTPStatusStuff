package com.sixtyninefourtwenty.httpstatusstuff.ui.dog

import android.os.Bundle
import android.view.View
import com.sixtyninefourtwenty.httpstatusstuff.data.HttpDogStatusCode
import com.sixtyninefourtwenty.httpstatusstuff.ui.HttpStatusFragment

class DogFragment : HttpStatusFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoAdapter.submitList(HttpDogStatusCode.entries.toList())
    }

}