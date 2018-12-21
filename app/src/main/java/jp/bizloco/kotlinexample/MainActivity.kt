package jp.bizloco.kotlinexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import jp.bizloco.kotlinexample.service.core.ApiClient
import jp.bizloco.kotlinexample.service.response.ImagesResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val URL: String = "https://raw.githubusercontent.com/lehieudut/KotlinExample/master/astronomy_quotes.json"
    // declare nullable variable
    private var mAdapter: RecyclerViewAdapter? = null
    // init Array mList
    var mList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

//        mBtnLoad.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//                loadApi()
//            }
//        })

        // use lamda
        //mBtnLoad.setOnClickListener { view -> loadApi() }
        mBtnLoad.setOnClickListener { loadApi() }
    }

    fun loadApi() {
        ApiClient.getService().getQuotes(URL).enqueue(object : Callback<ImagesResponse> {
            override fun onFailure(call: Call<ImagesResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ImagesResponse>, response: Response<ImagesResponse>) {
                if (response.body() != null) {
                    val stringRes: String? = response.body()?.url
                    // Split string to Array. Convert array to array mList
                    val myList: ArrayList<String> = stringRes?.split(",")?.toCollection(ArrayList())!!
                    Log.e("hieu", myList.size.toString())
                    mList.clear()
                    mList.addAll(myList)

//                    runOnUiThread(object : Runnable{
//                        override fun run() {
//                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        }
//
//                    })
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
//        mAdapter = RecyclerViewAdapter(this, mList, object  : ItemClickListener{
//            override fun onClickItem(position: Int) {
//                Toast.makeText(this@MainActivity, mList.get(position), Toast.LENGTH_SHORT).show()
//            }
//        })

        //use lamda
        val listener = ItemClickListener { position -> Toast.makeText(this@MainActivity, mList.get(position), Toast.LENGTH_SHORT).show() }
        mAdapter = RecyclerViewAdapter(this, mList, listener)

        mRecyclerView.setLayoutManager(GridLayoutManager(this, 3))
        mRecyclerView.setItemAnimator(DefaultItemAnimator())
        mRecyclerView.setAdapter(mAdapter)
    }

}
