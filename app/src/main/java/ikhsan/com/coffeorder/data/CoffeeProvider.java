package ikhsan.com.coffeorder.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import ikhsan.com.coffeorder.data.CoffeeContract.coffeeEntry;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ikhsan on 21/06/17.
 */

public class CoffeeProvider extends ContentProvider {

    private static final int COFFEE_MENU = 100;

    private static final int COFFEE_MENU_ID = 101;

    public static final String LOG_TAG = CoffeeProvider.class.getSimpleName();

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(CoffeeContract.CONTENT_AUTHORITY, CoffeeContract.PATH_COFFEE, COFFEE_MENU);

        sUriMatcher.addURI(CoffeeContract.CONTENT_AUTHORITY, CoffeeContract.PATH_COFFEE + "/#", COFFEE_MENU);
    }

    private DatabaseHelper mDbHelper;
    @Override
    public boolean onCreate() {

        mDbHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match){
            case COFFEE_MENU:

                cursor = database.query(coffeeEntry.TABLE_MENU, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case COFFEE_MENU_ID:

                selection = coffeeEntry._ID+"=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };

                cursor = database.query(coffeeEntry.TABLE_MENU, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
