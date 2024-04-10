package com.mykotlindemo.ui.fragment.middleFragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup.LayoutParams
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mykotlindemo.R
import com.mykotlindemo.databinding.AddDataDesignBinding
import com.mykotlindemo.databinding.FragmentMiddleBinding
import com.mykotlindemo.ui.roomdatabse.Dao
import com.mykotlindemo.ui.roomdatabse.DataEntity
import com.mykotlindemo.ui.roomdatabse.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiddleFragmentViewModel<T>(activity: Activity, mBinding: T) : ViewModel(), OnClickListener {
    @SuppressLint("StaticFieldLeak")
    var activity: Activity
    var mBinding: FragmentMiddleBinding
    lateinit var adepter: MovieDataAdepter
    lateinit var Dao: Dao
    private var movieList: MutableList<DataEntity> = ArrayList<DataEntity>()

    init {
        this.activity = activity
        this.mBinding = mBinding as FragmentMiddleBinding
        iniView()
    }

    private fun iniView() {
        mBinding.cvBtnAdd.setOnClickListener(this)
        Dao = MovieDatabase.getDatabase(activity).Dao()

        setAllData()

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adepter = MovieDataAdepter(activity)
        mBinding.rcvRoomDatabase.adapter = adepter
        mBinding.rcvRoomDatabase.layoutManager = layoutManager
    }

    private fun setAllData() {
        CoroutineScope(Dispatchers.IO).launch {
            val readData = Dao.getAllData()
            adepter.setData(readData)
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            mBinding.cvBtnAdd.id -> {

                val layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                val mAddBinding = AddDataDesignBinding.inflate(layoutInflater as LayoutInflater)
                val with = activity.resources.displayMetrics.widthPixels * 90 / 100
                val dialog = Dialog(activity, R.style.BottomSheetDialog)
                dialog.setContentView(mAddBinding.root)
                dialog.window!!.setLayout(with, LayoutParams.WRAP_CONTENT)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(true)
                dialog.show()

                mAddBinding.edtMovieUrl.setText("https://hips.hearstapps.com/digitalspyuk.cdnds.net/16/36/1473426365-doctor-strange-payoff-poster.jpg?resize=768:*")
                mAddBinding.btnSave.setOnClickListener {

                    when {
                        mAddBinding.edtMovieName.text.toString()
                            .isNotEmpty() && mAddBinding.edtMovieDescription.text.toString()
                            .isNotEmpty() && mAddBinding.edtMovieUrl.text.toString().isNotEmpty() -> {
                            val movieName = mAddBinding.edtMovieName.text.toString()
                            val movieUrl = mAddBinding.edtMovieUrl.text.toString()
                            val movieDescription = mAddBinding.edtMovieDescription.text.toString()


                            CoroutineScope(Dispatchers.IO).launch {
                                val dataEntity = DataEntity(0, movieName, movieUrl, movieDescription)
                                Dao.addData(dataEntity)
                            }
                            setAllData()
                            mBinding.rcvRoomDatabase.adapter = adepter
                            dialog.dismiss()
                        }
                        else -> {
                            when {
                                mAddBinding.edtMovieName.text.toString()
                                    .isEmpty() && mAddBinding.edtMovieUrl.text.toString()
                                    .isEmpty() && mAddBinding.edtMovieDescription.text.isEmpty() -> {
                                    Toast.makeText(
                                        activity,
                                        "Please Enter Movie Name, Url & Description",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                mAddBinding.edtMovieName.text.toString()
                                    .isEmpty() && mAddBinding.edtMovieUrl.text.toString().isEmpty() -> {
                                    Toast.makeText(
                                        activity,
                                        "Please Enter Movie Name & Url",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                mAddBinding.edtMovieName.text.toString()
                                    .isEmpty() && mAddBinding.edtMovieDescription.text.isEmpty() -> {
                                    Toast.makeText(
                                        activity,
                                        "Please Enter Movie Name & Description",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                mAddBinding.edtMovieUrl.text.toString()
                                    .isEmpty() && mAddBinding.edtMovieDescription.text.isEmpty() -> {
                                    Toast.makeText(
                                        activity,
                                        "Please Enter Movie Url & Description",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                mAddBinding.edtMovieName.text.toString().isEmpty() -> {
                                    Toast.makeText(activity, "Please Enter Movie Name", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                mAddBinding.edtMovieUrl.text.toString().isEmpty() -> {
                                    Toast.makeText(activity, "Please Enter Movie Url", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                mAddBinding.edtMovieDescription.text.isEmpty() -> {
                                    Toast.makeText(
                                        activity,
                                        "Please Enter Movie Description",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}