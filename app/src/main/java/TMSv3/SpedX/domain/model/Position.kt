package TMSv3.SpedX.domain.model

data class Position(
    val counter: Int,
    val driverId1: String,
    val fuelLevel: Double,
    val fuelUsage: String,
    val ignitionState: Boolean,
    val latitude: Double,
    val longitude: Double,
    val speed: Int,
    val timeStampUnix: Int,
    val vehicleId: String,
    val vehicleName: String
)