package id.kotlin.android

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.kotlin.android.deps.AppComponent
import id.kotlin.android.deps.DaggerAppComponent

class App : DaggerApplication() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        component = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component
}