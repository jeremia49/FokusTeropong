package my.id.jeremia.fokusteropong.init

import coil.Coil
import coil.ImageLoader
import javax.inject.Inject


class CoilInitializer @Inject constructor(
    private val image : ImageLoader
) : Initializer {
    override fun init() {
        Coil.setImageLoader(image)
    }

}