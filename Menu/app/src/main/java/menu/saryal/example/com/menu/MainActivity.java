package menu.saryal.example.com.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    public  static int currentCount = 0;

    menuDescription item1 = new menuDescription("Momo",
            "Steamed dumplings of minced chicken seasoned with nepalese spices and herbs | served with himalayan tomato-cashew sauce",
            5.50,
            "momo");
    menuDescription item2 = new menuDescription("Panner Chilli Fry",
            "Home-made cottage cheese sautéed with diced bell peppers, onions and tomatoes in chef’s special blend of indian spices",
            6.50,
            "panner");
    menuDescription item3 = new menuDescription("Fish Tikka Masala",
            "Spice-marinated mahi mahi filets grilled in tandoor | sautéed with diced bell pepper, onion and tomatoes and simmered in rich creamy tomato sauce",
            4.50,
            "fish");
    menuDescription item4 = new menuDescription("Chicken Dum Biryani",
            "Home-made cottage cheese sautéed with diced bell peppers, onions and tomatoes in chef’s special blend of indian spices",
            6.50,
            "biryani");
    menuDescription item5 = new menuDescription("Aloo Paratha",
            "Paratha stuffed with seasoned mashed potatoes, green peas and indian spices",
            4.50,
            "alooparatha");
    menuDescription item6 = new menuDescription("Samosa",
            "Delectable duo of crisp cones filled with spice-seasoned potatoes and peas in a mild fragrant spice blend",
            5.50,
            "samosa");
    menuDescription item7 = new menuDescription("Chaat",
            "A simple mixture of boiled chickpeas, chopped onions, plain yogurt, chaat masala, tamarind-mint chutney over the deconstructed samosa or potatoes | garnished with cilantro, namkeen",
            6.00,
            "chaat");
    menuDescription item8 = new menuDescription("Chicken Curry",
            "Boneless dark chicken delectably cooked in indian spices and curry sauce",
            14.00,
            "chickencurry");
    menuDescription item9 = new menuDescription("Lamb Madras",
            "Boneless lamb cooked in a savory and exotic coconut-flavored creamy curry sauce with delicious indian spices and curry leaves served with basmati rice",
            20.00,
            "lambmadras");
    menuDescription item10 = new menuDescription("Mango Lassi",
            "A refreshing mango-yogurt drink",
            4.00,
            "mangolassi");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuDbHelper method = new MenuDbHelper(this);
        method.clearDatabase();
        method.addDataToALLMenu(item1);
        method.addDataToALLMenu(item2);
        method.addDataToALLMenu(item3);
        method.addDataToALLMenu(item4);
        method.addDataToALLMenu(item5);
        method.addDataToALLMenu(item6);
        method.addDataToALLMenu(item7);
        method.addDataToALLMenu(item8);
        method.addDataToALLMenu(item9);
        method.addDataToALLMenu(item10);
        setContentView(R.layout.activity_main);
        method.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMenu(View view){
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }
}
