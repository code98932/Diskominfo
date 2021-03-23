package com.example.diskominfo

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diskominfo.Model.Item
import com.example.diskominfo.adapter.FeedAdapter
import com.example.diskominfo.common.HTTPDataHandler
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*


class MainActivity : AppCompatActivity() {

    private val RSS_link = "https://www.bekasikota.go.id/rss"
    private val RSS_to_JSON = " https://api.rss2json.com/v1/api.json?rss_url="



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "NEWS"
        setSupportActionBar(toolbar)

        val linearLayoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = linearLayoutManager

        loadRSS()

        swipeToRef.setOnRefreshListener {
            loadRSS()
            swipeToRef.isRefreshing = false
        }
    }

    private fun loadRSS() {
        val loadRSSAsync = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<String, String, String>() {
               var mDialog = ProgressDialog(this@MainActivity)


                override fun onPreExecute() {
                    mDialog.setMessage("Please wait...")
                    mDialog.show()
                }

                override fun onPostExecute(result: String?) {
                        mDialog.dismiss()
                        swipeToRef.isRefreshing = false
                        var rssObject: Item
                        rssObject = Gson().fromJson<Item>(result, Item::class.java)
                        val adapter = FeedAdapter(rssObject, baseContext)
                        recyclerview.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }


                override fun doInBackground(vararg params: String?): String {
                    val result: String
                    val http = HTTPDataHandler()
                    result = http.GetHTTPDataHAndler(params[0])
                    return result


                }
            }

            val url_get_data = StringBuilder(RSS_to_JSON)
            url_get_data.append(RSS_link)
            loadRSSAsync.execute(url_get_data.toString())
        }

    }
