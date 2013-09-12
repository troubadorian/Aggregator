package com.troubadorian.mobile.android.aggregator;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DonorsChoose extends ListActivity {

	static final String[] RSSFEEDS = new String[] { "General", "Sports",
			"Arts", "Regional"

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.donorschoose_main);

		// setListAdapter(ListAdapter) automatically adds
		// a ListView to fill the entire screen of the ListActivity

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				RSSFEEDS));

		ListView lv = getListView();

		lv.setTextFilterEnabled(true);
		final Intent generalintent = new Intent(this, GeneralNews.class)
				.putExtra("feedname", "general");

		final Intent sportsintent = new Intent(this, SportsNews.class)
				.putExtra("feedname", "sports");

		final Intent artsintent = new Intent(this, ArtsNews.class).putExtra(
				"feedname", "arts");

		final Intent regionalintent = new Intent(this, RegionalNews.class)
				.putExtra("feedname", "regional");

		lv.addStatesFromChildren();

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				switch (position) {
				case 0:

					startActivity(generalintent);
					break;

				case 1:

					startActivity(sportsintent);
					break;

				case 2:
					startActivity(artsintent);
					break;

				case 3:
					startActivity(regionalintent);
					break;
				}
			}
		});

	}
}
