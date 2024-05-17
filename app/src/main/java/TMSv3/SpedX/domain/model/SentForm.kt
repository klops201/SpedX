package TMSv3.SpedX.domain.model

data class SentForm(
    val gate: String? = null,
    val type: String? = null,
    val log: String? = null,
    val NIP: String? = null,
    val address: String? = null,
    val borderPosition: String? = null,
    val borderRoad: String? = null,
    val cargoPermit: String? = null,
    val companyName: String? = null,
    val emailConfirmation: String? = null,
    val gpsID: String? = null,
    val trailerID: String? = null,
    val truckID: String? = null,

)
