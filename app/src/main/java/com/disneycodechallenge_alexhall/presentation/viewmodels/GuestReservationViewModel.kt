package com.disneycodechallenge_alexhall.presentation.viewmodels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.disneycodechallenge_alexhall.R
import com.disneycodechallenge_alexhall.data.model.GuestHeaderModel
import com.disneycodechallenge_alexhall.data.model.GuestModel
import com.disneycodechallenge_alexhall.data.model.InfoModel
import com.disneycodechallenge_alexhall.data.model.Section
import javax.inject.Inject

class GuestReservationViewModel @Inject constructor(app: Application) :
    AndroidViewModel(app) {

    var guestList = ArrayList<Section>()

    fun getGuestHaveList(activity: Activity) {
        val guestHaveNameList = listOf(
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
        )
        val guestNeedNameList = listOf(
            "Linda Gibson",
            "Margaret Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
            "Dale Gibson",
            "Jeremy Gibson",
        )
        guestList = ArrayList()
        var section = 0
        guestList.add(
            GuestHeaderModel(
                activity.resources.getString(R.string.these_guests_have_reservations),
                section = section
            )
        )
        for (name in guestHaveNameList) {
            guestList.add(
                GuestModel(
                    name = name,
                    isSelect = false,
                    guestType = 1,
                    section = section
                )
            )
        }
        section = guestList.size
        guestList.add(
            GuestHeaderModel(
                activity.resources.getString(R.string.these_guests_need_reservations),
                section = section
            )
        )
        for (name in guestNeedNameList) {
            guestList.add(
                GuestModel(
                    name = name,
                    isSelect = false,
                    guestType = 2,
                    section = section
                )
            )
        }
        guestList.add(InfoModel(name = activity.resources.getString(R.string.at_least_msg),section=section))
    }

    fun checkGuestSelect(): Boolean {
        for (guest in guestList) {
            if (guest is GuestModel) {
                if (guest.isSelect) {
                    return true
                }
            }
        }
        return false
    }

    fun checkGuestEnableSelect(onError: () -> Unit, onConfirm: () -> Unit, onConflict: () -> Unit) {
        var checkHave = false
        var checkNeed = false
        for (guest in guestList) {
            if (guest is GuestModel) {
                if (guest.isSelect) {
                    if (guest.guestType == 1) {
                        checkHave = true
                    } else if (guest.guestType == 2) {
                        checkNeed = true
                    }
                }
            }
        }
        if (checkHave && checkNeed) {
            onConflict()
        } else if (checkHave && !checkNeed) {
            onConfirm()
        } else if (!checkHave && checkNeed) {
            onError()
        }
    }
}