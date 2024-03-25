package TMSv3.SpedX.domain.model

data class Position(
    val counter: Int? = null,
    val driverId1: String? = null,
    val fuelLevel: Double? = null,
    val fuelUsage: String? = null,
    val ignitionState: Boolean? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val speed: Int? = null,
    val timeStampUnix: Int? = null,
    val vehicleId: String? = null,
    val vehicleName: String? = null
)