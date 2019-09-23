package com.hivian.keasy

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.jakewharton.threetenabp.AndroidThreeTen
import android.content.pm.ProviderInfo


class ThreeTenProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        AndroidThreeTen.init(this.context)
        return false
    }

    override fun attachInfo(context: Context, providerInfo: ProviderInfo?) {
        if (providerInfo == null) {
            throw NullPointerException("YourLibraryInitProvider ProviderInfo cannot be null.")
        }
        // So if the authorities equal the library internal ones, the developer forgot to set his applicationId
        check("<your-library-applicationid>.yourlibraryinitprovider" != providerInfo.authority) {
            "Incorrect provider authority in manifest. Most likely due to a " + "missing applicationId variable in application\'s build.gradle."
        }
        super.attachInfo(context, providerInfo)
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

}