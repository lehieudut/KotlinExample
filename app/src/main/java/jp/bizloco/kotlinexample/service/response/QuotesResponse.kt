package jp.bizloco.kotlinexample.service.response

import com.google.gson.annotations.SerializedName


/**
 * Created by PC on 11/26/2016.
 */
class QuotesResponse(@SerializedName("quotes") val url: String = "")
