package com.ebay.skunk.ebayyshop;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CategoryActivity extends ActionBarActivity implements OnClickListener {

	public static final String CATEGORY = "com.ebay.skunk.ebayyshop.CATEGORY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initView() {
		Intent intent = getIntent();
		String occasion = intent.getStringExtra(MainActivity.OCCASION);
		TextView textView = (TextView) findViewById(R.id.category_occasion);
		textView.setText(occasion);
		
		ListenView(R.id.category_cloth);
		ListenView(R.id.category_shoes);
		ListenView(R.id.category_home);
		ListenView(R.id.category_collect);
		ListenView(R.id.category_crafts);
		ListenView(R.id.category_sport);
	}
	
	private void ListenView(int ctg) {
		View v = (View) findViewById(ctg);
		v.setOnClickListener(CategoryActivity.this);	
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.category_cloth:
			openCategoryActivity("cloth");
			break;
		case R.id.category_shoes:
			openCategoryActivity("shoes");
			break;
		case R.id.category_home:
			openCategoryActivity("home");
			break;
		case R.id.category_collect:
			openCategoryActivity("collect");
			break;
		case R.id.category_crafts:
			openCategoryActivity("crafts");
			break;
		case R.id.category_sport:
			openCategoryActivity("sport");
			break;
		}
	}

	private void openCategoryActivity(String ctg) {
		Intent intent = new Intent(this, ListingActivity.class);
		intent.putExtra(CATEGORY, ctg);
		startActivity (intent);		
	}
}
