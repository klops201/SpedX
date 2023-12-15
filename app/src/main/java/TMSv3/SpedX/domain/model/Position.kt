package TMSv3.SpedX.domain.model

class Position (
    val vehicleId: String? = null,
    val vehicleName: String? = null,
    val driverId: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val speed: Int? = null,
    val timestamp : Int? = null,
    val fuelPercentage: Int? = null,
)