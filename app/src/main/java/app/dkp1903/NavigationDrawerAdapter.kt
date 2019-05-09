package app.dkp1903.throttleback.adapters

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
//import app.dkp1903.throttleback.R
import app.dkp1903.throttleback.activities.MainActivity
import app.dkp1903.throttleback.fragments.MainScreenFragment
import kotlinx.android.synthetic.main.row_custom_navigationdrawer.view.*

//this extends the default adapter class to actually be an adapter
//navViewHolder is an inner class, that is inside another class
class NavigationDrawerAdapter(_contentList: ArrayList<String>, _getImages: IntArray, _context: Context)
    : RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>(){

     override fun onBindViewHolder(holder: NavViewHolder?, position: Int)
    {
        //first we need to set the background of the image
        holder?.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_GET?.setText(contentList?.get(position))
        //clicklistener means that on clikcking on the particular section, the fragment opens
        holder?.contentHolder?.setOnClickListener({
            if(position == 0 ){
                //All songs fragment has to be opened
                val mainScreenFragment = MainScreenFragment()//this is an object of the msf class
                (mContext as MainActivity).supportFragmentManager
                //this shows an error inititally because we need
                // to typecast the context to the activity that
                // we are currently involved in
                //the as MainActivity means that we are going to invoke our adapter through the MainActivity.kt file
            }
        })
    }

    var contentList: ArrayList<String> ?= null
    var getImages: IntArray?=null
    var mContext: Context ?= null
    init {
        this.contentList = _contentList
        this.getImages = _getImages
        this.mContext = _context
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder{
        //linking XML file to the adapter
        //LayoutInflater is a class used to manipulate Android Screens using
        //predefined XML layouts.
        //Since we have created our own XML files
        //we do not need to use the predefined layouts
        var itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_navigationdrawer, parent, false)
        //In order to inflate the view, that is, to use the XML file
        //we use the Inflate method
        //this returns an object of type View, which we can pass inside the NavViewHolder class
        val returnThis = NavViewHolder(itemView)
        return returnThis
    }


    override fun getItemCount(): Int
    {

    }


    class NavViewHolder: RecyclerView.ViewHolder(itemView){
        //we initialise our views here
        var icon_GET: ImageView ?= null
        var text_GET: TextView?= null
        var contentHolder: RelativeLayout ?= null
        init {
            icon_GET = itemView?.findViewById(R.id.icon_navdrawer)
            text_GET = itemView?.findViewById(R.id.text_navdrawer)
            contentHolder = itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }

    }
}