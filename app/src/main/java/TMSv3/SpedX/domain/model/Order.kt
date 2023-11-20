package TMSv3.SpedX.domain.model

data class Order(
    val orderId: Int? = null,
    val userId: Int? = null,
    val position: String? = null,
    val finaldestination: String? = null,
    val startdestination: String? = null,
    val cargoName: String? = null,
    val cargoWeight: Int? = null,
    val driverId: Int? = null,
    val cmrId: Int? = null
)
