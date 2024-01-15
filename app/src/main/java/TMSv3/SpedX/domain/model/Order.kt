package TMSv3.SpedX.domain.model

data class Order(
    val firestoreID: String? = null,
    val orderTitle: String? = null,
    val orderId: String? = null,
    val position: String? = null,
    val finaldestination: String? = null,
    val startdestination: String? = null,
    val cargoName: String? = null,
    val cargoWeight: Int? = null,
//    val distance: Double? = null,
    val driverId: String? = null,
    val cmrId: String? = null,
    val createAt: String? = null,
    val done: Boolean = false,
)
