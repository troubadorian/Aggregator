package com.troubadorian.mobile.android.aggregator;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class USNational extends ListActivity {

	static final String[] RSSFEEDS = new String[] { "Top Stories",
			"US National", "World", "Politics", "Business", "Technology",
			"Entertainment", "Health", "Science", "Op/Ed", "Sports",
			"Odd News", "Obituaries", "Most Emailed", "Most Viewed",
			"Most Recommended"

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setListAdapter(ListAdapter) automatically adds
		// a ListView to fill the entire screen of the ListActivity

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				RSSFEEDS));

		/*
		 * String[] countries =
		 * getResources().getStringArray(R.array.countries_array);
		 */

		/*
		 * setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
		 * RSSFEEDS));
		 */

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text

				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}
}
