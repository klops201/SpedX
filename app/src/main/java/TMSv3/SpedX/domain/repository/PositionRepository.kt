package TMSv3.SpedX.domain.repository

import TMSv3.SpedX.domain.model.Position
import TMSv3.SpedX.domain.model.Response

typealias SavePositionResponse = Response<Boolean>
typealias GetPositionResponse = Response<Position?>


interface PositionRepository {


    suspend fun savePosition(position: Position, vehicleId: String): SavePositionResponse

    suspend fun getPosition(vehicleId: String): GetPositionResponse

}