package ikhsan.com.coffeorder.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ikhsan on 20/06/17.
 */

public class CoffeeContract {

    private CoffeeContract(){}

    public static final String CONTENT_AUTHORITY = "ikhsan.com.coffeorder";

    public static final String PATH_COFFEE = "coffee_menu";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class coffeeEntry implements BaseColumns{


        //Table untuk login admin
        public final static String TABLE_NAME = "coffee_order";

        public final static String COLUMN_USERNAME = "username";
        public final static String COLUMN_PASSWORD = "password";

        //Table untuk menu
        public final static String TABLE_MENU = "coffee_menu";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_MENU_NAME = "name";
        public final static String COLUMN_MENU_PRICE = "price";
        public final static String COLUMN_MENU_TYPE = "jenis";

        public final static int TYPE_FOODS = 0;
        public final static int TYPE_DRINKS = 1;

        //Table untuk user/pemesan
        /*
        * o = order
        * to = total order
        *
        * ex : tofood_user = total order food for user*/
        public final static String TABLE_USER = "coffee_user";

        public final static String _USERID = BaseColumns._ID;
        public final static String COLUMN_USER_NAME = "name_user";
        public final static String COLUMN_USER_TABLE  = "table_user";
        public final static String COLUMN_USER_OFOOD  = "ofood_user";
        public final static String COLUMN_USER_TOFOOD = "tofood_user";
        public final static String COLUMN_USER_ODRINK  = "odrink_user";
        public final static String COLUMN_USER_TODRINK = "todrink_user";
        public final static String COLUMN_USER_TOTAL  = "total_user";

    }
}
