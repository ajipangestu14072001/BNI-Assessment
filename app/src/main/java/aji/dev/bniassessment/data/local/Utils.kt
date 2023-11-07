package aji.dev.bniassessment.data.local

object Utils {
    val portofolio = listOf(
        TransactionData(
            type = "donutChart",
            data = listOf(
                TransactionItem(
                    label = "Tarik Tunai",
                    percentage = "55",
                    data = listOf(
                        TransactionDetail("21/01/2023", 1000000),
                        TransactionDetail("20/01/2023", 500000),
                        TransactionDetail("19/01/2023", 1000000)
                    )
                ),
                TransactionItem(
                    label = "QRIS Payment",
                    percentage = "31",
                    data = listOf(
                        TransactionDetail("21/01/2023", 159000),
                        TransactionDetail("20/01/2023", 35000),
                        TransactionDetail("19/01/2023", 1500)
                    )
                ),
                TransactionItem(
                    label = "Topup Gopay",
                    percentage = "7.7",
                    data = listOf(
                        TransactionDetail("21/01/2023", 200000),
                        TransactionDetail("20/01/2023", 195000),
                        TransactionDetail("19/01/2023", 5000000)
                    )
                ),
                TransactionItem(
                    label = "Lainnya",
                    percentage = "6.3",
                    data = listOf(
                        TransactionDetail("21/01/2023", 1000000),
                        TransactionDetail("20/01/2023", 500000),
                        TransactionDetail("19/01/2023", 1000000)
                    )
                )
            )
        ),
        LineChartData(
            type = "lineChart",
            data = LineChartDataDetail(
                month = listOf(3, 7, 8, 10, 5, 10, 1, 3, 5, 10, 7, 7)
            )
        )
    )
}

