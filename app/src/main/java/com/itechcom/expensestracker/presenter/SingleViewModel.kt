package com.itechcom.expensestracker.presenter

import androidx.lifecycle.ViewModel
import com.itechcom.expensestracker.data.room.RoomDBManager
import com.itechcom.expensestracker.data.sharedpref.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleViewModel @Inject constructor(
    val roomDBManager: RoomDBManager,
    val sharedPrefManager: SharedPrefManager
) : ViewModel() {

}