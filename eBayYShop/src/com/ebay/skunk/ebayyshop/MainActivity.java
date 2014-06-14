package com.ebay.skunk.ebayyshop;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	public static final String OCCASION = "com.ebay.skunk.ebayyshop.OCCASION";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initView() {
		ListenView(R.id.occasion_birthday);
		ListenView(R.id.occasion_relocation);
		ListenView(R.id.occasion_holiday);
		ListenView(R.id.occasion_special);
		ListenView(R.id.occasion_bcoz);
		ListenView(R.id.occasion_latest);
		ListenView(R.id.occasion_trip);
	}
	
	private void ListenView(int occasion) {
		View v = (View) findViewById(occasion);
		v.setOnClickListener(MainActivity.this);	
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.occasion_birthday:
			openCategoryActivity("birthday");
			break;
		case R.id.occasion_relocation:
			openCategoryActivity("relocation");
			break;
		case R.id.occasion_holiday:
			openCategoryActivity("holiday");
			break;
		case R.id.occasion_special:
			openCategoryActivity("special");
			break;
		case R.id.occasion_bcoz:
			openCategoryActivity("bcoz");
			break;
		case R.id.occasion_latest:
			openCategoryActivity("latest");
			break;
		case R.id.occasion_trip:
			openCategoryActivity("trip");
			break;
		}
	}

	private void openCategoryActivity(String occasion) {
		Intent intent = new Intent(this, CategoryActivity.class);
		intent.putExtra(OCCASION, occasion);
		startActivity (intent);		
	}

}
