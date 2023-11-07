package aji.dev.bniassessment.presentation.qris

import aji.dev.bniassessment.presentation.promo.Loading
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QrisScreen(
    viewModel: QrisViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    if (state.value.details.isEmpty()) Loading()
    LaunchedEffect(Unit) {
        viewModel.startScanning()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(0.5f), contentAlignment = Alignment.Center) {
            Text(text =  state.value.details )
        }

    }


}