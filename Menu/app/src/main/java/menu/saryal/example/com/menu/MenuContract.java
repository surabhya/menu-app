package menu.saryal.example.com.menu;

import android.provider.BaseColumns;

public class MenuContract {

    public MenuContract(){

    }

    public static abstract class MenuEntry implements BaseColumns{
        public static final String TABLE_NAME = "menuitem";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_RATING= "rating";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_TOTAL_COST = "totalcost";
        public static final String COLUMN_NAME_TOTAL_ORDER= "totalorder";
    }

    public static abstract class MenuDB implements BaseColumns{
        public static final String TABLE_NAME = "mainDatabase";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_TOTAL_COST = "totalcost";
        public static final String COLUMN_NAME_TOTAL_ORDER= "totalorder";
    }
}
