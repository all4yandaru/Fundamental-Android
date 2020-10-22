package com.project.myunittesting.model


// TODO 6: alt + enter pada nama class, terus create test, pilih JUnit4 terus centang semua functionnya -> ok, terus pilih yg test
class MainViewModel(private val cuboidModel: CuboidModel) {
    // TODO 3: buat MainViewModel
    fun getCircumference(): Double = cuboidModel.getCircumference()

    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()

    fun getVolume(): Double = cuboidModel.getVolume()

    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(l, w, h)
    }
}