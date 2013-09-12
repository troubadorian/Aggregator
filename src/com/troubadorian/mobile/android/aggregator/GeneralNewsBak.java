package com.troubadorian.mobile.android.aggregator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import com.troubadorian.mobile.android.aggregator.RSSFeed;
import com.troubadorian.mobile.android.aggregator.RSSHandler;
import com.troubadorian.mobile.android.aggregator.RSSItem;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GeneralNewsBak extends ListActivity
{
    private static final String TAG = "General News";

    static final String[] RSSFEEDS = new String[]
    { "Top Stories", "World", "Canada", "Politics", "Business", "Health",
            "Arts", "Technology", "Consumer"

    };

    private MyContentFeed myContentFeed = null;

    String rssliststring = " ";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.generalnews_main);

        // setListAdapter(ListAdapter) automatically adds
        // a ListView to fill the entire screen of the ListActivity

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
                RSSFEEDS));

        /* start of read opml */

        try
        {
            /* Create a URL we want to load some xml-data from. */
            URL url = new URL(
                    "http://www.anddev.org/images/tut/basic/parsingxml/example.xml");
            
            

            /* Get a SAXParser from the SAXPArserFactory. */
            SAXParserFactory spf = SAXParserFactory.newInstance();
            
            SAXParser sp = spf.newSAXParser();

            /* Get the XMLReader of the SAXParser we created. */
            XMLReader xr = sp.getXMLReader();
            /* Create a new ContentHandler and apply it to the XML-Reader */
            MyContentHandler myContentHandler = new MyContentHandler();
            xr.setContentHandler(myContentHandler);

            /* Parse the xml-data from our URL. */
            xr.parse(new InputSource(url.openStream()));
            /* Parsing has finished. */

            /* Our ExampleHandler now provides the parsed data to us. */
            MyParsedDataSet parsedExampleDataSet = myContentHandler
                    .getParsedData();

            /* Set the result to be displayed in our GUI. */
           // tv.setText(parsedExampleDataSet.toString());
            Log.d(TAG, "----------------------" + parsedExampleDataSet.toString() + " what");

        } catch (Exception e)
        {
            /* Display any Error to the GUI. */
            /*tv.setText("Error: " + e.getMessage());*/
            Log.e(TAG, "Parsing error", e);
        }

        Log.d(TAG, "----------------------" + rssliststring + " what");

        /* end of read opml */

        ListView lv = getListView();

        lv.setTextFilterEnabled(true);
        final Intent intent0 = new Intent(this, GeneralAndroidAtlas.class)
                .putExtra("feedname", "topstories");

        final Intent intent1 = new Intent(this, GeneralNewYorkTimes.class).putExtra("feedname",
                "world");

        final Intent intent2 = new Intent(this, GeneralCanada.class).putExtra(
                "feedname", "canada");
        
        final Intent intent3 = new Intent(this, GeneralPolitics.class).putExtra(
                "feedname", "politics");
        
        final Intent intent4 = new Intent(this, GeneralBusiness.class).putExtra(
                "feedname", "business");

        final Intent intent5 = new Intent(this, GeneralHealth.class).putExtra(
                "feedname", "health");
        
        final Intent intent6 = new Intent(this, GeneralArts.class).putExtra(
                "feedname", "arts");
        
        final Intent intent7 = new Intent(this, GeneralTechnology.class).putExtra(
                "feedname", "technology");
        
        final Intent intent8 = new Intent(this, GeneralConsumer.class).putExtra(
                "feedname", "consumer");
        

        
        
        lv.addStatesFromChildren();

        lv.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                // When clicked, show a toast with the TextView text
                switch (position)
                {
                case 0:

                    startActivity(intent0);
                    break;

                case 1:

                    startActivity(intent1);
                    break;

                case 2:
                    startActivity(intent2);
                    break;
                    
                case 3:
                    startActivity(intent3);
                    break;
                    
                case 4:
                    startActivity(intent4);
                    break;

                }
            }
        });

    }
}
