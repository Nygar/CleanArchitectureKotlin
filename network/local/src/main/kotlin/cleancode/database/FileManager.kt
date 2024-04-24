package cleancode.database

import android.content.Context

object FileManager {
    private const val SETTINGS_FILE_NAME = "com.nygar.android.CACHE"
    private const val SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update"

    /**
     * Write a value to a user preferences file.
     *
     * @param context [Context] to retrieve android user preferences.
     * @param preferenceFileName A file name represented where data will be written to.
     * @param value A long representing the value to be inserted.
     */
    fun writeToPreferences(
        context: Context,
        preferenceFileName: String,
        value: Long,
    ) {
        val sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME + preferenceFileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(SETTINGS_KEY_LAST_CACHE_UPDATE, value)
        editor.apply()
    }

    /**
     * Get a value from a user preferences file.
     *
     * @param context [Context] to retrieve android user preferences.
     * @param preferenceFileName A file name representing where data will be get from.
     * @return A long representing the value retrieved from the preferences file.
     */
    fun getFromPreferences(
        context: Context,
        preferenceFileName: String,
    ): Long {
        val sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME + preferenceFileName, Context.MODE_PRIVATE)
        return sharedPreferences.getLong(SETTINGS_KEY_LAST_CACHE_UPDATE, 0)
    }
}
