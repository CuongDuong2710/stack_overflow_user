package cuongduong.developer.android.stackoverflow

import android.app.Application
import cuongduong.developer.android.stackoverflow.data.StackExchangeApiServices
import cuongduong.developer.android.stackoverflow.data.db.StackOverFlowDatabase
import cuongduong.developer.android.stackoverflow.data.network.ConnectivityInterceptor
import cuongduong.developer.android.stackoverflow.data.network.ConnectivityInterceptorImp
import cuongduong.developer.android.stackoverflow.data.network.StackExchangeNetworkDataSource
import cuongduong.developer.android.stackoverflow.data.network.StackExchangeNetworkDataSourceImpl
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepository
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class StackExchangeApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@StackExchangeApplication)) // supply application context

        // create singleton instance of database, DAO class, connectivity, api services class... for whole app
        bind() from singleton { StackOverFlowDatabase(instance()) } // instance in this case is application context - get from androidXModule
        bind() from singleton { instance<StackOverFlowDatabase>().itemListDao() } // get itemListDao from above instance of StackOverFlowDatabase
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImp(instance()) } // bind singleton interface ConnectivityInterceptor into StackExchangeApplication
        bind() from singleton { StackExchangeApiServices(instance()) } // instance in this case is above instance of ConnectivityInterceptor
        bind<StackExchangeNetworkDataSource>() with singleton { StackExchangeNetworkDataSourceImpl(instance()) } // instance in this case is StackExchangeApiServices
        bind<StackExchangeRepository>() with singleton { StackExchangeRepositoryImpl(instance(), instance()) } // itemListDao and StackExchangeNetworkDataSource
    }

}