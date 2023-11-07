package aji.dev.bniassessment.presentation.promo

import aji.dev.bniassessment.presentation.navigation.Screen
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoScreen(
    navController: NavHostController,
    promoViewModel: PromoViewModel = hiltViewModel(),
) {
    val promo = promoViewModel.promo.value.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Halo Selamat Datang",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Portofolio.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.BarChart,
                            contentDescription = "",
                        )
                    }

                    BadgedBox(
                        badge = { Badge { Text("4") } },
                        modifier = Modifier
                            .padding(start = 5.dp, end = 20.dp)
                            .clickable {
                                navController.navigate(Screen.Qris.route)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.History,
                            contentDescription = null,

                            )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Qris.route)
                },

                ) {
                Icon(imageVector = Icons.Default.QrCode2, contentDescription = "add icon")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 80.dp),
        ) {
            item {
                CreditCardInfoCard()
            }
            item {
                Text(
                    text = "Promo Terbaru",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 5.dp, bottom = 10.dp, top = 16.dp)
                )
            }
            when {
                promo.loadState.append is LoadState.Loading || promo.loadState.refresh is LoadState.Loading -> {
                    item {
                        Loading()
                    }
                }

                promo.loadState.refresh is LoadState.Error -> {
                    val errorMessage =
                        (promo.loadState.refresh as LoadState.Error).error.localizedMessage
                    item {
                        Text(text = "Failed to load data: $errorMessage")
                    }
                }

                promo.loadState.append is LoadState.Error -> {
                    val errorMessage =
                        (promo.loadState.append as LoadState.Error).error.localizedMessage
                    item {
                        Text(text = "Failed to load more data: $errorMessage")
                    }
                }

                else -> {
                    items(promo.itemCount) {
                        val promos = promo[it]
                        PromoItem(data = promos, navController = navController)
                    }
                }
            }
        }
    }

}
