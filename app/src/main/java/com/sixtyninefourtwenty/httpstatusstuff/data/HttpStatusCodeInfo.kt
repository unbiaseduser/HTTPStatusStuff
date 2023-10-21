package com.sixtyninefourtwenty.httpstatusstuff.data

import android.content.Context
import android.content.res.Resources

interface HttpStatusCodeInfo {
    val code: Int
    fun label(res: Resources): String
    fun label(context: Context) = label(context.resources)
    fun url(): String
    fun imageUrl(): String
}