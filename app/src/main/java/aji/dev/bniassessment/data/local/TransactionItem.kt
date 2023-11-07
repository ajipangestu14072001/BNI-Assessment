package aji.dev.bniassessment.data.local

data class TransactionItem(
    val label: String,
    val percentage: String,
    val data: List<TransactionDetail>
)