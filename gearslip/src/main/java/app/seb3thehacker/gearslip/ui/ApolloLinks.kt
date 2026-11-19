package app.seb3thehacker.gearslip.ui

import android.content.Context
import android.content.Intent
import android.net.Uri

internal object ApolloLinks {
    const val PRIVACY = "https://github.com/Apollo-40418/Gearslip/blob/main/PRIVACY.md"

    fun open(context: Context, url: String) {
        runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}
