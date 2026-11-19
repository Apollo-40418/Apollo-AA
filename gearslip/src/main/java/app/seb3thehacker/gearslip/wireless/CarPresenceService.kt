package app.seb3thehacker.gearslip.wireless

import android.companion.CompanionDeviceService
import android.os.Build
import androidx.annotation.RequiresApi

/** Android calls this when a car approved in [WirelessAuto] connects over Bluetooth. */
@RequiresApi(Build.VERSION_CODES.S)
class CarPresenceService : CompanionDeviceService() {

    @Deprecated("Deprecated in Java")
    override fun onDeviceAppeared(address: String) {
        WirelessAuto.carAppeared(applicationContext, address)
    }

    @Deprecated("Deprecated in Java")
    override fun onDeviceDisappeared(address: String) {}
}
