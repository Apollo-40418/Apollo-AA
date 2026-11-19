package app.seb3thehacker.gearslip

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

internal object PlatformPermissions {
    fun hasProjectMediaOp(context: Context): Boolean {
        val ops = context.getSystemService(AppOpsManager::class.java) ?: return false
        val mode = runCatching {
            ops.unsafeCheckOpNoThrow("android:project_media", android.os.Process.myUid(), context.packageName)
        }.getOrDefault(AppOpsManager.MODE_IGNORED)
        return mode == AppOpsManager.MODE_ALLOWED
    }

    fun hasBluetoothConnect(context: Context): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.S ||
            context.checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED

    fun hasNotificationPermission(context: Context): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
}
