package TMSv3.SpedX.di

import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import TMSv3.SpedX.data.repository.AuthRepositoryImpl
import TMSv3.SpedX.data.repository.CmrRepositoryImpl
import TMSv3.SpedX.data.repository.MainRepositoryImpl
import TMSv3.SpedX.data.repository.OrderRepositoryImpl
import TMSv3.SpedX.domain.repository.AuthRepository
import TMSv3.SpedX.domain.repository.CmrRepository
import TMSv3.SpedX.domain.repository.MainRepository
import TMSv3.SpedX.domain.repository.OrderRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.hilt.components.SingletonComponent


@Module
//@InstallIn(ViewModelComponent::class)
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
    @Provides
    fun provideFirebaseDatabase() = Firebase.database


    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideFirebaseStorage() = Firebase.storage


    @Provides
    fun provideAuthRepository(auth: FirebaseAuth, db: FirebaseFirestore): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        db = db
    )

    @Provides
    fun provideMainRepository(auth: FirebaseAuth, db: FirebaseFirestore,
    ): MainRepository = MainRepositoryImpl(
        auth = auth,
        db = db
    )


    @Provides
    fun provideOrdersRepository(auth: FirebaseAuth, db: FirebaseFirestore,
    ): OrderRepository = OrderRepositoryImpl(
        auth = auth,
        db = db
    )


    @Provides
    fun provideCmrRepository(auth: FirebaseAuth, db: FirebaseFirestore, storage: FirebaseStorage
    ): CmrRepository = CmrRepositoryImpl(
        auth = auth,
        db = db,
        storage = storage,
    )




}