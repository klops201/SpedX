package TMSv3.SpedX.domain.model

data class Order(
    val orderId: String? = null,
    val userId: String? = null,
    val position: String? = null,
    val finaldestination: String? = null,
    val startdestination: String? = null,
    val cargoName: String? = null,
    val cargoWeight: Int? = null,
    val driverId: String? = null,
    val cmrId: String? = null
)
