package com.sixtyninefourtwenty.httpstatusstuff.ui.cat

import android.os.Bundle
import android.view.View
import com.sixtyninefourtwenty.httpstatusstuff.data.HttpCatStatusCode
import com.sixtyninefourtwenty.httpstatusstuff.ui.HttpStatusFragment

class CatFragment : HttpStatusFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoAdapter.submitList(HttpCatStatusCode.entries.toList())
    }

}