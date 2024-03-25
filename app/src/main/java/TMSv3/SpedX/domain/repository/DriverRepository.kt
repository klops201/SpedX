package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response


typealias GetDriverInfoResponse = Response<Driver?>
typealias getDriversListResponse = Response<List<Driver>>
typealias GetDriverTruckResponse = Response<Position?>
typealias DeleteDriverResponse = Response<Boolean>
typealias EditDriverResponse = Response<Boolean>


interface DriverRepository {

    suspend fun getDriverInfo(firebaseID: String): GetDriverInfoResponse

    suspend fun getDriversList(): getDriversListResponse

    suspend fun getDriverTruck(vehicleId: String): GetDriverTruckResponse

    suspend fun deleteDriver(firebaseID: String): DeleteDriverResponse

    suspend fun editDriver(
        driverId: String,
        driverName: String,
        driverPhoneNr: Int,
        firebaseID: String,
        vehicleId: String
    ): EditDriverResponse

}