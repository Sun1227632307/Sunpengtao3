package com.example.app3;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app3", "table1", 0);
        uriMatcher.addURI("com.example.app3", "table2", 0);
        uriMatcher.addURI("com.example.app3", "table3", 0);
        uriMatcher.addURI("com.example.app3", "table4", 0);
        uriMatcher.addURI("com.example.app3", "table5", 0);
    }


    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case 0:
                //table1

                break;
            case 1:
                //table1
                break;
            case 2:
                //table1
                break;
            case 3:
                //table1
                break;

        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case 0:
                //table1
                return "table1";
            break;
            case 1:
                //table1
                return "table2";
            break;
            case 2:
                return "table3";
            //table1
            break;
            case 3:
                return "table4";
            //table1
            break;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case 0:
                //table1
                break;
            case 1:
                //table1
                break;
            case 2:
                //table1
                break;
            case 3:
                //table1
                break;

        }
        return null;

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case 0:
                //table1
                break;
            case 1:
                //table1
                break;
            case 2:
                //table1
                break;
            case 3:
                //table1
                break;

        }
        return 0;

    }
}
