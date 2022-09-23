package com.disneycodechallenge_alexhall.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.disneycodechallenge_alexhall.R
import com.disneycodechallenge_alexhall.custom.StickyHeaderItemDecorator
import com.disneycodechallenge_alexhall.databinding.ActivityGuestReservationBinding
import com.disneycodechallenge_alexhall.databinding.LayoutSnackbarBinding
import com.disneycodechallenge_alexhall.presentation.adapter.GuestSelectAdapter
import com.disneycodechallenge_alexhall.presentation.viewmodels.GuestReservationViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class GuestReservationActivity : DaggerAppCompatActivity() {
    private lateinit var guestHaveAdapter: GuestSelectAdapter
    private lateinit var binding: ActivityGuestReservationBinding


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: GuestReservationViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initAdapter()
        initListener()
    }

    private fun initListener() {
        binding.buttonContinueClick.setOnClickListener {
            viewModel.checkGuestEnableSelect({
                showSnackBar()
            }, {
                startActivity(Intent(this, ConfirmationActivity::class.java))
            }, {
                startActivity(Intent(this, ConflictActivity::class.java))
            })
        }
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun showSnackBar() {
        val snackBar = Snackbar.make(binding.snackBarView, "", Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view as SnackbarLayout
        val snackView = LayoutSnackbarBinding.inflate(LayoutInflater.from(this), null, false)
        snackView.closeBtn.setOnClickListener {
            snackBar.dismiss()
        }
        val parentParams = snackBarView.layoutParams as FrameLayout.LayoutParams
        parentParams.setMargins(0, 0, 0, 0)
        snackBarView.layoutParams = parentParams
        snackBarView.setPadding(0, 0, 0, 0)
        snackBarView.addView(snackView.root, 0)
        snackBar.show()
    }

    private fun initData() {
        viewModel.getGuestHaveList(this)
    }

    private fun initAdapter() {
        guestHaveAdapter = GuestSelectAdapter(this, viewModel.guestList) {
            if (viewModel.checkGuestSelect()) {
                binding.buttonContinue.background =
                    ContextCompat.getDrawable(this, R.drawable.continue_btn_back)
            } else {
                binding.buttonContinue.background =
                    ContextCompat.getDrawable(this, R.drawable.continue_dis_btn_back)
            }
        }
        binding.guestRecycler.apply {
            layoutManager = LinearLayoutManager(this@GuestReservationActivity)
            adapter = guestHaveAdapter

        }
        val decorator = StickyHeaderItemDecorator(guestHaveAdapter)
        decorator.attachToRecyclerView(binding.guestRecycler)
    }
}