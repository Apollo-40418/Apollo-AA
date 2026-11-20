package app.seb3thehacker.gearslip.car

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CarLauncherGifSpeedTest {
    @Test
    fun playbackSpeedScalesGifTime() {
        assertEquals(500, gifPlaybackTime(1_000, 0.5f, 4_000))
        assertEquals(2_000, gifPlaybackTime(1_000, 2f, 4_000))
    }

    @Test
    fun playbackTimeWrapsAtLoopEndAndHandlesInvalidDurationsSafely() {
        assertEquals(1_000, gifPlaybackTime(2_500, 2f, 4_000))
        assertEquals(0, gifPlaybackTime(-1, 1f, 0))
    }

    @Test
    fun playbackSpeedMustBePositiveAndFinite() {
        assertThrows(IllegalArgumentException::class.java) {
            gifPlaybackTime(1_000, Float.NaN, 4_000)
        }
    }
}
