package menu.saryal.example.com.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A placeholder fragment containing a simple view.
 */
public class ItemOrderedFragment extends Fragment {

    public final static String EXTRA_TEXT = "menu.saryal.example.com.menu";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_ordered, container, false);

        final MenuDbHelper dbHandler = new MenuDbHelper(this.getActivity());
        ArrayList<menuDescription> data = dbHandler.readData();

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.fragment_item_ordered_list_view);

        final ArrayList<HashMap<String, String>> dataList;

        dataList = new ArrayList<HashMap<String, String>>();

        // adding each child node to HashMap key => value
        for (menuDescription x : data) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Title", x.getTitle());
            map.put("TotalOrder", "Total Item: " + x.getTotalOrder());
            map.put("TotalCost", "Total Cost: $" + x.getTotalCost());
            dataList.add(map);
        }

        ListAdapter adapter = new SimpleAdapter(
                this.getActivity(),
                dataList,
                R.layout.list_item_with_image,
                new String[]{"Title", "TotalOrder", "TotalCost"},
                new int[]{R.id.list_item_with_image_text_view, R.id.recepit_total_order_text_view, R.id.recepit_cost_text_view});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String title = dataList.get(position).get("Title");
                menuDescription item = dbHandler.findByTitle(title);
                String cost_string = "" + item.getCost();
                String totalCost_string = "" + item.totalCost;
                String totalOrder_string = "" + item.getTotalOrder();
                String ratingString = "" + item.getRatinng();
                String[] transfer_data = {item.getTitle(), item.getDescription(), cost_string, totalCost_string, totalOrder_string, item.getImage(),ratingString};
                Intent intent = new Intent(getActivity(), EditDetailPage.class);
                intent.putExtra(EXTRA_TEXT, transfer_data);
                startActivity(intent);
            }
        });
        dbHandler.close();
        return rootView;
    }
}
