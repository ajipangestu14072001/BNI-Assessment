package aji.dev.bniassessment.presentation.promo

import aji.dev.bniassessment.domain.model.Data
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoDetailScreen(
    navController: NavHostController?,
    data: Data
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Detail Promo", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController?.popBackStack() }
                    ) {
                        Icon(
                            Icons.Rounded.ArrowBackIos,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = data.attributes.nama.uppercase(),
                    fontSize = 16.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.weight(1f))
                Text(formatDate(data.attributes.createdAt))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                fontSize = 14.sp,
                text = data.attributes.lokasi,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = data.attributes.desc.replace("\\n-", ""))
        }
    }

}

@SuppressLint("SimpleDateFormat")
@Composable
fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormat = SimpleDateFormat("dd MMM yyyy")
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}