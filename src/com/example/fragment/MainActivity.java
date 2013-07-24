package com.example.fragment;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;

import java.net.URL;

import org.xmlpull.v1.XmlPullParser;



public class MainActivity extends Activity {

	static final String PUB_DATE = "pubDate";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LINK = "link";
	static final String TITLE = "title";
	static final String ITEM = "item";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parse("http://rss.cnn.com/rss/edition.rss");
	}
	
	public void parseRss(View view){
		parse("http://rss.cnn.com/rss/edition.rss");
		TextView textView = (TextView) findViewById(R.id.textViewCount);
		
	}

	
	public void parse(String rssFeed){
		XmlPullParser parser = Xml.newPullParser();
		try{
			parser.setInput(new URL(rssFeed).openConnection().getInputStream(),null);
			int eventType = parser.getEventType();
			boolean done = false;
			while (eventType != XmlPullParser.END_DOCUMENT && !done) {
				String name = null;
				switch (eventType) {
				
				case XmlPullParser.START_DOCUMENT:
					
					// Create new List<RssMessage>
					break;
					
				case XmlPullParser.START_TAG:
					
					name = parser.getName();
					if (name.equalsIgnoreCase(ITEM)) {
						// create new RssMessage object
					} else {
						if (name.equalsIgnoreCase(LINK)) {
							Log.i("link", parser.nextText());
						} else if (name.equalsIgnoreCase(DESCRIPTION)) {
							Log.i("descr", parser.nextText());
						} else if (name.equalsIgnoreCase(PUB_DATE)) {
							Log.i("date", parser.nextText());
						} else if (name.equalsIgnoreCase(TITLE)) {
							Log.i("title", parser.nextText());
						}
					}
					break;
					
				case XmlPullParser.END_TAG:
					
					name = parser.getName();
					if (name.equalsIgnoreCase(ITEM)) {
						// Add item
					} else if (name.equalsIgnoreCase(CHANNEL)) {
						done = true;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}