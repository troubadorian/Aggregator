package com.troubadorian.mobile.android.aggregator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.troubadorian.mobile.android.aggregator.RSSFeed;
import com.troubadorian.mobile.android.aggregator.RSSHandler;

import android.R.string;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

public class TodaysNewsSmallProvider extends AppWidgetProvider {
	public static final String TAG = "main";

	// declare button for refresh
	private Button mRefreshButton;

	// declare button for logo
	private Button mLogoButton;

	private TextView newstv;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews updateView = buildUpdate(context);
		appWidgetManager.updateAppWidget(appWidgetIds, updateView);
		super.onUpdate(context, appWidgetManager, appWidgetIds);

	}

	private String[] months = { "January", "Februrary", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };

	private String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" };

	private RSSFeed myRssFeed = null;

	private RemoteViews buildUpdate(Context context) {
		RemoteViews updateView = null;
		Time time = new Time();
		time.setToNow();

		String monthDay = new Integer(time.monthDay).toString();

		String month = months[time.month];

		int createdyear = 0;

		int createdday = 1;

		String createdmonth = null;

		TextView newstv;

		String timelinestring = null;

		String rssliststring = " ";

		String rsslinksstring = " ";

		updateView = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout_small);

		/*
		 * Twitter twitter = new Twitter("cbcsyndication", "partner5896");
		 * 
		 * Status twitterstatus = twitter.getStatus("cbcnews");
		 * 
		 * List<Status> listoftwitterstatus = twitter.getUserTimeline();
		 */

		/* List<Status> listoftwitterstatus = twitter.getUserTimeline(); */

		/* List<Status> listoftwitterstatus = twitter.getPublicTimeline(); */

		/*
		 * for (Status obj : listoftwitterstatus) { timelinestring =
		 * timelinestring + obj.toString();
		 * 
		 * }
		 * 
		 * Date dateofupdate = twitterstatus.getCreatedAt();
		 */
		// updateView.setTextViewText(R.id.Date,
		// "This is an example of a news headline from cbc.ca which refreshes at regular intervals");

		// Date twitterstatusdate = twitter.getDate(createdyear, createdmonth,
		// createdday);

		// updateView.setTextViewText(R.id.Date, twitterstatus.toString() +
		// " Updated At " + twitterstatusdate.toString());

		/*
		 * updateView.setTextViewText(R.id.NewsScroller,
		 * twitterstatus.toString());
		 * 
		 * updateView.setTextViewText(R.id.WeekDay, "...last updated at...");
		 * 
		 * updateView.setTextViewText(R.id.Month, month + " " + monthDay + " " +
		 * time.year);
		 * 
		 * updateView .setTextViewText(R.id.NewsText,
		 * dateofupdate.toLocaleString());
		 * 
		 * updateView.setTextViewText(R.id.NewsText, "Nov 16, 2:43 PM");
		 */

		// updateView.setTextViewText(R.id.WeekDay, days[time.weekDay]);

		// updateView.setTextViewText(R.id.NewsText, "This is the news");

		// launchIntent.setComponent(new ComponentName("com.android.calendar",
		// "com.android.calendar.LaunchActivity"));

		// launchIntent.setComponent(new
		// ComponentName("com.cbc.mobile.android.cbcnews",
		// "com.cbc.mobile.android.cbcnews.CBCNews"));

		/* start of read RSS */

		try {

			/*
			 * URL rssUrl = new URL(
			 * "http://feeds.bbci.co.uk/news/rss.xml?edition=int");
			 */

			/*
			 * URL rssUrl = new
			 * URL("http://www.cbc.ca/m/rss/sports-hockey.xml");
			 */

			URL rssUrl = new URL(
					"http://reviews.cnet.com/8300-19736_7-251.xml?tag=rtcol;about");

			SAXParserFactory mySAXParserFactory = SAXParserFactory
					.newInstance();
			SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
			XMLReader myXMLReader = mySAXParser.getXMLReader();
			RSSHandler myRSSHandler = new RSSHandler();
			myXMLReader.setContentHandler(myRSSHandler);
			InputSource myInputSource = new InputSource(rssUrl.openStream());
			myXMLReader.parse(myInputSource);

			myRssFeed = myRSSHandler.getFeed();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* end of read RSS */

		/* start of display RSS */

		List<RSSItem> listofrssitems = myRssFeed.getList();

		for (RSSItem obj : listofrssitems) {
			rssliststring = rssliststring + "   " + obj.toString();

			rsslinksstring = rsslinksstring + "<a href='"
					+ obj.getLink().toString()

					+ "'>" + obj.toString() + "</a>";

		}

		if (myRssFeed != null) {
			Calendar c = Calendar.getInstance();
			String strCurrentTiime = "\n(Time of Reading - "
					+ c.get(Calendar.HOUR_OF_DAY) + " : "
					+ c.get(Calendar.MINUTE) + ")\n";

			/*
			 * updateView.setTextViewText(R.id.NewsScroller,
			 * myRssFeed.getTitle() + myRssFeed.getDescription() + rssliststring
			 * + strCurrentTiime);
			 */

			updateView.setTextViewText(R.id.Month, month + " " + monthDay + " "
					+ time.year);

			updateView.setTextViewText(R.id.NewsScroller, rssliststring
					+ strCurrentTiime);

			updateView.setTextViewText(R.id.NewsLinkScroller,
					myRssFeed.getTitle());

			updateView.setTextViewText(R.id.WeekDay, "...last updated at...");

			updateView.setTextViewText(R.id.NewsText, month + " " + monthDay
					+ " " + time.year);

			/*
			 * (myRssFeed.getDescription());
			 * 
			 * (myRssFeed.getPubdate());
			 * 
			 * (myRssFeed.getLink());
			 */

		}

		/* end of display RSS */

		Intent launchIntent = new Intent();
		/*
		 * launchIntent.setComponent(new ComponentName("com.android.browser",
		 * "com.android.browser.BrowserActivity"));
		 * launchIntent.setAction("android.intent.action.VIEW");
		 * launchIntent.addCategory("android.intent.category.BROWSABLE");
		 * launchIntent.setData(Uri.parse("http://cbc.ca/m/touch"));
		 */

		launchIntent.setComponent(new ComponentName(
				"com.troubadorian.mobile.android.fandroid",
				"com.troubadorian.mobile.android.fandroid.Fandroid"));
		launchIntent.setAction("android.intent.action.VIEW");

		Intent launchIntent2 = new Intent();
		launchIntent2.setComponent(new ComponentName(
				"com.cbc.mobile.android.cbcnews",
				"com.cbc.mobile.android.cbcnews.CBCNews"));
		launchIntent2.setAction("android.intent.action.VIEW");

		/*
		 * launchIntent.setAction(Intent.ACTION_MAIN);
		 * launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		 * launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		 * Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 */

		PendingIntent intent = PendingIntent.getActivity(context, 0,
				launchIntent, 0);
		updateView.setOnClickPendingIntent(R.id.navigmaske, intent);

		/*
		 * PendingIntent intent2 = PendingIntent.getActivity(context, 0,
		 * launchIntent2, 0); updateView.setOnClickPendingIntent(R.id.Base,
		 * intent2);
		 */

		PendingIntent intentRightButton = PendingIntent.getActivity(context, 0,
				launchIntent2, 0);

		updateView.setOnClickPendingIntent(R.id.nextarrow, intentRightButton);

		/* attach and handle intent for prevarrow */
		Intent launchIntent7 = new Intent();
		launchIntent7.setComponent(new ComponentName(
				"com.cbc.mobile.android.cbcnews",
				"com.cbc.mobile.android.cbcnews.CBCNews"));
		launchIntent7.setAction("android.intent.action.VIEW");
		PendingIntent intentLeftButton = PendingIntent.getActivity(context, 0,
				launchIntent7, 0);

		updateView.setOnClickPendingIntent(R.id.prevarrow, intentLeftButton);

		/* attach and handle intent for CBC radio */
		Intent launchIntent3 = new Intent();
		launchIntent3.setComponent(new ComponentName(
				"com.cbc.mobile.android.cbcradiothree",
				"com.cbc.mobile.android.cbcradiothree.CBCRadioThree"));
		launchIntent3.setAction("android.intent.action.VIEW");
		PendingIntent intentcbcradio = PendingIntent.getActivity(context, 0,
				launchIntent3, 0);
		updateView.setOnClickPendingIntent(R.id.cbcradio_ico_for_widget,
				intentcbcradio);

		/* attach and handle intent for CBC news */
		Intent launchIntent4 = new Intent();
		launchIntent4.setComponent(new ComponentName(
				"com.cbc.mobile.android.cbcnews",
				"com.cbc.mobile.android.cbcnews.CBCNews"));
		launchIntent4.setAction("android.intent.action.VIEW");
		PendingIntent intentcbcnews = PendingIntent.getActivity(context, 0,
				launchIntent4, 0);
		updateView.setOnClickPendingIntent(R.id.cbcnews_ico_for_widget,
				intentcbcnews);

		/* attach and handle intent for CBC hockey */
		Intent launchIntent5 = new Intent();
		launchIntent5.setComponent(new ComponentName("com.cbc.mobile.hockey",
				"com.cbc.mobile.hockey.CBCHockey"));
		launchIntent5.setAction("android.intent.action.VIEW");
		PendingIntent intentcbchockey = PendingIntent.getActivity(context, 0,
				launchIntent5, 0);
		updateView.setOnClickPendingIntent(R.id.cbchockey_ico_for_widget,
				intentcbchockey);

		/* attach and handle intent for CBC */
		Intent launchIntent6 = new Intent();
		launchIntent6
				.setComponent(new ComponentName("com.cbc.mobile.android.cbc",
						"com.cbc.mobile.android.cbc.CBC"));
		launchIntent6.setAction("android.intent.action.VIEW");
		PendingIntent intentcbc = PendingIntent.getActivity(context, 0,
				launchIntent6, 0);
		updateView.setOnClickPendingIntent(R.id.cbc_ico_for_widget, intentcbc);

		return updateView;
	}
}