package com.sixtyninefourtwenty.httpstatusstuff.ui.pizza

import android.os.Bundle
import android.view.View
import com.sixtyninefourtwenty.httpstatusstuff.data.HttpPizzaStatusCode
import com.sixtyninefourtwenty.httpstatusstuff.ui.HttpStatusFragment

class PizzaFragment : HttpStatusFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoAdapter.submitList(HttpPizzaStatusCode.entries.toList())
    }

}