package com.example.itemcheckout.placeholder;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itemcheckout.SQLiteOperations;
import com.example.itemcheckout.ViewItems;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent extends AppCompatActivity {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();
    private static SQLiteOperations sqliteOperations;

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

//    {
//        System.out.println("Testing sql...");
//        sqliteOperations = new SQLiteOperations(PlaceholderContent.this);
//        int myCount = getCount();
//        // Add some sample items.
//        for (int i = 1; i <= myCount; i++) {
//            addItem(createPlaceholderItem(i));
//        }
//    }

    private int getCount() {
        Log.d("DEBUG", "Checking getCount()...");
        int dbCount = this.sqliteOperations.dbSize();

        Log.d("DEBUG", "dbCount is at "+dbCount);
        return dbCount;
    }

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(String.valueOf(position), "ItemDEBUG " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        //builder.append("Details about Item: ").append(position);
        builder.append("Item at slot: ").append(position);
        builder.append("\n");
        for (int i = 0; i < position; i++) {
            //builder.append("\nMore details information here.");
            builder.append(i);
            if (i + 1 != position) {
                builder.append(", ");

            }
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final String content;
        public final String details;

        public PlaceholderItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}