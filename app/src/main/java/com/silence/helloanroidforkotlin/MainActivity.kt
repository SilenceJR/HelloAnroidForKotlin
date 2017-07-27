package com.silence.helloanroidforkotlin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_recycler.view.*


class MainActivity : AppCompatActivity() {

    val mRecyclerAdapter = RecyclerAdapter(this)

    val data = listOf<String>(
            "http://image.tianjimedia.com/uploadImages/2015/159/42/3JFPCDDY03A1.jpg",
            "http://image.tianjimedia.com/uploadImages/2015/159/47/MKNV9240LRZA.jpg",
            "http://image.tianjimedia.com/uploadImages/2015/159/45/A8F7KC7N7BKD.jpg",
            "http://image.tianjimedia.com/uploadImages/2015/159/46/AB05NY8T8P56.jpg",
            "http://image.tianjimedia.com/uploadImages/2015/159/41/9SZD906LL6W9.jpg",
            "http://image.tianjimedia.com/uploadImages/2016/120/35/456C4NCOC1E2.jpg",
            "http://image.tianjimedia.com/uploadImages/2016/336/54/DZU9493XZ242.JPG")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()

    }

    fun initView() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mRecyclerAdapter

    }

    fun initData() {
        mRecyclerAdapter.setData(data)
        mRecyclerAdapter.notifyDataSetChanged()
    }

}

class RecyclerAdapter(var context: Context) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private lateinit var data : List<String>

    fun setData(value : List<String>) {
        this.data = value
    }

    fun getData() = data

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Glide.with(context)
                .load(data.get(position))
                .into(holder.img)

        holder.img.setOnClickListener {
            Log.i("tag", "点击了第$position,个条目")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.img
    }
}

