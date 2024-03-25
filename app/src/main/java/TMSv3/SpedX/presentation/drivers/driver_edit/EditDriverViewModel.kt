package TMSv3.SpedX.presentation.drivers.driver_edit

import TMSv3.SpedX.domain.repository.CmrRepository
import TMSv3.SpedX.domain.repository.OrderRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EditDriverViewModel @Inject constructor(
    private val repoOrder: OrderRepository,
    private val repoCmr: CmrRepository,

    ) : ViewModel() {






}