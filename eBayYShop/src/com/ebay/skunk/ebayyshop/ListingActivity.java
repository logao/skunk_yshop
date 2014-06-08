package com.ebay.skunk.ebayyshop;

import java.util.ArrayList;
import java.util.List;


import com.ebay.skunk.ebayyshop.SlideView.OnSlideListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListingActivity extends ActionBarActivity implements OnItemClickListener, OnSlideListener {

    private static ListViewCompat mListView;
	private static List<Item> GroupItem = new ArrayList<ListingActivity.Item>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listing);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.listing, menu);
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
    	mListView = (ListViewCompat)findViewById(R.id.list);
    	
    	GroupItem.clear();
        AddItem (R.drawable.item_image_1, "man clothing", "Worsted poplin shirt", "$50"); 
        AddItem (R.drawable.item_image_2, "woman clothing", "Emilio Pucci", "$53"); 
        AddItem (R.drawable.item_image_3, "woman clothing", "striped T-shirt", "$30");
        AddItem (R.drawable.item_image_4, "woman clothing", "High elastic jeans", "$40");
        
        mListView.setAdapter(new ItemAdapter());
        mListView.setOnItemClickListener(this);
    }

	public void AddItem(int image, String title, String desc, String price) {
		Item item = new Item();
        item.image = image;  
        item.title = title;
        item.desc = desc;  
        item.price = price;  
        GroupItem.add(item);
	}
    
    public class Item {
        public int image;
        public String title;
        public String desc;
        public String price;
    }

	@Override
	public void onSlide(View view, int status) {
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		
	}
	private class ItemAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        ItemAdapter() {
        	super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return GroupItem.size();
        }

        @Override
        public Object getItem(int position) {
            return GroupItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            
        	ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            
            if (slideView == null) {
            	View itemView = mInflater.inflate(R.layout.list_item, null);
                slideView = new SlideView(ListingActivity.this);
                slideView.setContentView(itemView);
                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(ListingActivity.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
        	
            Item item = GroupItem.get(position);
        	holder.image.setImageResource(item.image);
        	holder.title.setText(item.title);
        	holder.desc.setText(item.desc);
        	holder.price.setText(item.price);
            return slideView;
        }

    }
	
    private class ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView desc;
        public TextView price;

        ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.desc);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
	
}

