package com.daffa.movcat

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var coordinatorLayout : CoordinatorLayout? = null
    private var bottomSheet :FrameLayout? = null
    private var data : DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        data  = intent.getSerializableExtra("Data") as DataModel
        coordinatorLayout = findViewById(R.id.coordinator)
        bottomSheet = coordinatorLayout?.findViewById(R.id.bottom_sheet)

        initLayout()

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {

                    }
                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                    }
                }
            }
        })

        cvPreview.setOnClickListener{
            if (behavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }else {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        behavior.peekHeight=0
    }

    private fun initLayout(){
        data?.let { data ->
            collapsingToolbar.title=data.title
            collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.trans))
            collapsingToolbar.setScrimVisibleHeightTrigger(500)
            toolbar.setNavigationIcon(R.drawable.left)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(10))
            Glide.with(applicationContext)
                .load(data.poster)
                .error(R.drawable.err)
                .apply(requestOptions)
                .into(ivPost)
            play.setOnClickListener {
                startActivity(Intent(ACTION_VIEW, Uri.parse(data.poster)))
            }
            tvTitle.text=data.title
            tvGenre.text=data.genres
            tvDirector.text=data.director
            tvBudget.text=data.budget
            tvRating.text=data.rating
            tvLanguage.text=data.language
            tvRuntime.text=data.runtime
            tvRevenue.text=data.revenue
            tvStoryLine.text="   ${data.overview}"
            rvStar.apply {
                layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                adapter = StarAdapter(context,data.star)
            }
            rvTheater.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TheaterAdapter(context,initList())
            }
        }
    }
    private fun initList() : List<TheaterModel> =
            listOf(
                TheaterModel("https://www.jadwalnonton.com/data/images/theaters/empire-xxi-yogyakarta_430x280.jpg","EMPIRE XXI","Jl. Urip Sumoharjo. Yogyakarta. Telp. (0274) 551 021","09.00, 12.00, 15.00"),
                TheaterModel("https://www.jadwalnonton.com/data/images/theaters/cgv-jwalk-mall-yogyakarta_430x280.jpg","CGV Jwalk Mall","Sahid J-Walk Lt. 3. Kawasan Sahid Yogya Lifestyle City . Jl. Babarsari No. 2 Catur, Depok, Sleman, Yogyakarta","09.00, 12.00, 15.00"),
                TheaterModel("https://www.jadwalnonton.com/data/images/theaters/sleman-city-hall_430x280.jpg","SLEMAN CITY HALL XXI","Mall Sleman City Hall Lt. 2. Jln. Gito Gati, Denggung, Tridadi . Kec. Sleman, Kabupaten Sleman. Daerah Istimewa Yogyakarta. Telp. (0274) 2882121","09.00, 12.00, 15.00"))

}
