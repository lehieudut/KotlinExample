package jp.bizloco.kotlinexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import jp.bizloco.kotlinexample.service.core.ApiClient
import jp.bizloco.kotlinexample.service.response.QuotesResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val URL: String = "https://gitlab.com/lehieudut/astronomyquotesserver/raw/master/astronomy_quotes.json"
    // declare nullable variable
    private var mAdapter: RecyclerViewAdapter? = null
    // init Array list
    var mList: ArrayList<String>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        // use lamda
        mBtnLoad.setOnClickListener { loadApi() }
    }

    fun loadApi() {
        ApiClient.getService().getQuotes(URL).enqueue(object : Callback<QuotesResponse> {
            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<QuotesResponse>, response: Response<QuotesResponse>) {
                if (response.body() != null) {
                    val stringRes: String? = response.body()?.url
                    // Split string to Array. Convert array to array list
                    val myList: ArrayList<String> = stringRes?.split(",")?.toCollection(ArrayList())!!
                    Log.e("hieu", myList.size.toString())
                    mList?.clear()
                    mList?.addAll(myList)

                    //use lamda
                    runOnUiThread {
                        mAdapter?.notifyDataSetChanged()
                    }
                }
                mAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun initRecyclerView() {
        //use lamda
        mAdapter = RecyclerViewAdapter(this, mList, { position ->
            Toast.makeText(this@MainActivity, mList?.get(position), Toast.LENGTH_SHORT).show()
        })
        mRecyclerView.setLayoutManager(GridLayoutManager(this, 3))
        mRecyclerView.setItemAnimator(DefaultItemAnimator())
        mRecyclerView.setAdapter(mAdapter)
    }
}
