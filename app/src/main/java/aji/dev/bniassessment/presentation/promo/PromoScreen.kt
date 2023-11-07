package aji.dev.bniassessment.presentation.promo

import aji.dev.bniassessment.presentation.navigation.Screen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PromoScreen(
    navController: NavHostController,
    promoViewModel: PromoViewModel = hiltViewModel(),
) {
    val promo = promoViewModel.promo.value.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
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
