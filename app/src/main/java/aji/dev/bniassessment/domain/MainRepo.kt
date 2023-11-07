package aji.dev.bniassessment.domain

import kotlinx.coroutines.flow.Flow

interface MainRepo {

    fun startScanning(): Flow<String?>
}