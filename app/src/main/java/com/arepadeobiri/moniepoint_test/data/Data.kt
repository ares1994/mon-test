package com.arepadeobiri.moniepoint_test.data

data class Shipment(
    val title: String,
    val info: String,
    val amount: String,
    val date: String,
    val state: ShipmentState
)

enum class ShipmentState {
    InProgress,
    Pending,
    Loading,
    Completed,
    Cancelled,

}


