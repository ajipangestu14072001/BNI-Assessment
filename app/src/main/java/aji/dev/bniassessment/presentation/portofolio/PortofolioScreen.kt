package aji.dev.bniassessment.presentation.portofolio

import aji.dev.bniassessment.data.local.LineChartData
import aji.dev.bniassessment.data.local.TransactionData
import aji.dev.bniassessment.data.local.TransactionItem
import aji.dev.bniassessment.data.local.Utils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortofolioScreen(
    navController: NavHostController,
    ) {
    val chunkedData = Utils.portofolio.filterIsInstance<TransactionData>()
        .flatMap { it.data }
        .chunked(2)
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    var selectedTransactionItem by remember { mutableStateOf<TransactionItem?>(null) }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            },
        ) {
            selectedTransactionItem?.let { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 26.dp)
                ) {
                    Text(
                        text = item.label,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Percentage: ${item.percentage}%",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    item.data.forEach { transactionDetail ->
                        Text(
                            text = "Transaction Date: ${transactionDetail.trx_date}",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Amount: ${transactionDetail.nominal}",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                }
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Portofolio", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
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
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp),
            contentPadding = it
        ) {
            items(chunkedData) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { transactionItem ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .clickable {
                                        scope.launch {
                                            selectedTransactionItem = transactionItem
                                            isSheetOpen = !isSheetOpen
                                        }
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = transactionItem.label,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "${transactionItem.percentage}%",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 35.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
            item {
                LineGraph(
                    xAxisData = Utils.portofolio
                        .filterIsInstance<LineChartData>()
                        .firstOrNull()
                        ?.data
                        ?.month
                        ?.map { month ->
                            GraphData.String(month.toString())
                        } ?: emptyList(),
                    yAxisData = listOf(200, 40, 60, 450, 500, 30, 50, 35, 50, 100, 30, 10),
                )

            }

        }
    }

}
