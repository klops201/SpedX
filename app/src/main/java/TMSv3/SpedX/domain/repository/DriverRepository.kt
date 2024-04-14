package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Driver
import TMSv3.SpedX.domain.model.Order
import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response


typealias getDriverInfoResponse = Response<Driver?>
typealias getDriversListResponse = Response<List<Driver>>
typealias getDriverTruckResponse = Response<Position?>
typealias deleteDriverResponse = Response<Boolean>
typealias editDriverResponse = Response<Boolean>


interface DriverRepository {

    suspend fun getDriverInfo(firebaseID: String): getDriverInfoResponse

    suspend fun getDriversList(): getDriversListResponse

    suspend fun getDriverTruck(vehicleId: String): getDriverTruckResponse

    suspend fun deleteDriver(firebaseID: String): deleteDriverResponse

    suspend fun editDriver(
        firebaseID: String,
        driverName: String,
        driverPhoneNr: Int,
        driverId: String,
        vehicleId: String
    ): editDriverResponse

}