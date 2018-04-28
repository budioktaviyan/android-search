package id.kotlin.android.deps

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.kotlin.android.views.MainActivity
import id.kotlin.android.views.MainModule

@Module
abstract class ViewModule {

    @ViewScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    @Suppress("unused")
    abstract fun mainActivity(): MainActivity
}