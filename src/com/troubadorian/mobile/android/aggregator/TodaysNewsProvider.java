package com.troubadorian.mobile.android.aggregator;

import java.util.Date;
import java.util.List;

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

public class TodaysNewsProvider extends AppWidgetProvider {
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

		updateView = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		// updateView.setTextViewText(R.id.Date, monthDay);

		/*
		 * Twitter twitter = new Twitter("cbcsyndication", "partner5896");
		 */
		/* Status twitterstatus = twitter.getStatus("cbcnews"); */

		// Status twitterstatus = twitter.getStatus("troubadorian");

		/* List<Status> listoftwitterstatus = twitter.getUserTimeline(); */

		/* List<Status> listoftwitterstatus = twitter.getPublicTimeline(); */

		/*
		 * for (Status obj : listoftwitterstatus) { timelinestring =
		 * timelinestring + obj.toString();
		 * 
		 * }
		 */

		/* Date dateofupdate = twitterstatus.getCreatedAt(); */

		// updateView.setTextViewText(R.id.Date,
		// "This is an example of a news headline from cbc.ca which refreshes at regular intervals");

		/*
		 * Date twitterstatusdate = twitter.getDate(createdyear, createdmonth,
		 * createdday);
		 */

		/*
		 * updateView.setTextViewText(R.id.Date, twitterstatus.toString() +
		 * " Updated At " + twitterstatusdate.toString());
		 */

		/*
		 * updateView.setTextViewText(R.id.NewsScroller,
		 * twitterstatus.toString());
		 */

		/*
		 * updateView.setTextViewText(R.id.NewsScroller,
		 * " Ont. probes dropped charges in alleged Ponzi scheme " + "     " +
		 * "Tories confirm Afghan mission details Read more:" + "     " +
		 * "Conflicting theories on Mexican blast" + "     " + "" +
		 * "New frog, toads found in Colombia" + "     " +
		 * "Pickton prosecution cost $103M" + "     " +
		 * "Homeowners out thousands despite warranty" + "     " +
		 * "Not told of Parliament reno problems: Fortier" + "     " +
		 * "Asteroid dust captured by spacecraft" + "     " +
		 * "S. Korea teen kills mom, self over Internet games" + "     " +
		 * "Gov. Gen. Literary Awards hail 1st-time winners");
		 */

		/*
		 * updateView.setTextViewText(R.id.WeekDay, "...last updated at...");
		 * 
		 * updateView.setTextViewText(R.id.Month, month + " " + monthDay + " " +
		 * time.year);
		 */

		/*
		 * updateView .setTextViewText(R.id.NewsText,
		 * dateofupdate.toLocaleString());
		 */

		/*
		 * updateView .setTextViewText(R.id.NewsText, "Nov 16, 2:43 PM");
		 */

		// updateView.setTextViewText(R.id.WeekDay, days[time.weekDay]);

		// updateView.setTextViewText(R.id.NewsText, "This is the news");

		// launchIntent.setComponent(new ComponentName("com.android.calendar",
		// "com.android.calendar.LaunchActivity"));

		// launchIntent.setComponent(new
		// ComponentName("com.cbc.mobile.android.cbcnews",
		// "com.cbc.mobile.android.cbcnews.CBCNews"));

		Intent launchIntent = new Intent();
		launchIntent.setComponent(new ComponentName("com.android.browser",
				"com.android.browser.BrowserActivity"));
		launchIntent.setAction("android.intent.action.VIEW");
		launchIntent.addCategory("android.intent.category.BROWSABLE");
		launchIntent.setData(Uri.parse("http://cbc.ca/m/touch"));
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				launchIntent, 0);
		updateView.setOnClickPendingIntent(R.id.navigmaske, intent);

		Intent launchIntent2 = new Intent();
		launchIntent2.setComponent(new ComponentName(
				"com.cbc.mobile.android.cbcnews",
				"com.cbc.mobile.android.cbcnews.CBCNews"));
		launchIntent2.setAction("android.intent.action.VIEW");
		PendingIntent intentRightButton = PendingIntent.getActivity(context, 0,
				launchIntent2, 0);

		updateView.setOnClickPendingIntent(R.id.nextarrow, intentRightButton);

		Intent launchIntent7 = new Intent();
		launchIntent7.setComponent(new ComponentName(
				"com.cbc.mobile.android.cbcnews",
				"com.cbc.mobile.android.cbcnews.CBCNews"));
		launchIntent7.setAction("android.intent.action.VIEW");
		PendingIntent intentLeftButton = PendingIntent.getActivity(context, 0,
				launchIntent7, 0);

		updateView.setOnClickPendingIntent(R.id.prevarrow, intentLeftButton);

		/*
		 * launchIntent.setAction(Intent.ACTION_MAIN);
		 * launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		 * launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		 * Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 */

		/*
		 * PendingIntent intent2 = PendingIntent.getActivity(context, 0,
		 * launchIntent2, 0); updateView.setOnClickPendingIntent(R.id.Base,
		 * intent2);
		 */

		return updateView;
	}
}