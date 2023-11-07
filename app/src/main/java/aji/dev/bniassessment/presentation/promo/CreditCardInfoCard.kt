package aji.dev.bniassessment.presentation.promo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreditCardInfoCard() {
    val expanded = remember { mutableStateOf(false) }
    val cardInfo = CreditCardInfo(
        cardNumber = "**** **** **** 1234",
        customerName = "Dwi Aji Pangestu",
        cvv = "123",
        expiry = "12/24",
        amount = "200.000.000"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded.value = !expanded.value }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Card Number: ${cardInfo.cardNumber}")
            AnimatedVisibility(
                visible = expanded.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Customer Name: ${cardInfo.customerName}")
                    Text(text = "CVV: ${cardInfo.cvv}")
                    Text(text = "Expiry: ${cardInfo.expiry}")
                    Text(
                        text = "Rp. ${cardInfo.amount}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

data class CreditCardInfo(
    val cardNumber: String,
    val customerName: String,
    val cvv: String,
    val expiry: String,
    val amount: String
)