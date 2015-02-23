/**
 * Created by wilfrid on 9/23/14.
 */

package com.rafaelsf80.d4w.manufacturing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ItemDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//enable window content transition
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

		//set the transition
		Transition ts = new Slide();  
		ts.setDuration(500);
		getWindow().setEnterTransition(ts);
		getWindow().setExitTransition(ts);

		setContentView(R.layout.itemdetails);

		// create variables to store the item details UI elements
		TextView itemDetailsTitle = (TextView) findViewById(R.id.title_DT);
		TextView manufacturerTextView = (TextView) findViewById(R.id.tv_manufacturer_DT);
        TextView modelTextView = (TextView) findViewById(R.id.tv_model_DT);
        Button instructionsManualView = (Button) findViewById(R.id.bt_manual_DT);
		Button videoGuideView = (Button) findViewById(R.id.bt_video_guide_DT);
		Button inventoryCountView = (Button) findViewById(R.id.inventoryCount_DT);
		TextView itemPriceView = (TextView) findViewById(R.id.itemPrice_DT);

		// gather data which was passed from the selected list item
		Intent fromListItem = getIntent();

		final String itemName = fromListItem.getStringExtra("itemName");
		String mainImage = fromListItem.getStringExtra("mainImage");
		final String manufacturer = fromListItem.getStringExtra("manufacturer");
		String model = fromListItem.getStringExtra("model");
		final String instructionsManualURL = fromListItem.getStringExtra("instructionsManual");
		final String instructionsVideoURL = fromListItem.getStringExtra("videoGuide");
		final String itemPrice = fromListItem.getStringExtra("itemPrice");
		final String inventoryCount = fromListItem.getStringExtra("inventoryCount");

		// set UI information to the data which has been parsed through
		setTitle(itemName + " for " + model);
		itemDetailsTitle.setText(itemName);
		manufacturerTextView.setText("Manufacturer: " + manufacturer);
        modelTextView.setText("Car Model: " + model);
		inventoryCountView.setText(inventoryCount + " in Stock");
		itemPriceView.setText("Item Price: E " + itemPrice);


		// download thumbnail
		ImageView iconView = (ImageView) findViewById(R.id.mainimage_DT);
		Picasso.with(this)
		.load(mainImage)
		.into(iconView	);


		videoGuideView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instructionsVideoURL));
				startActivity(intent);

				// create new toast to update the user what video is about to be played
				Toast.makeText(getApplicationContext(), "Playing " + itemName + " Video Guide",
						Toast.LENGTH_LONG).show();
			}

		});


        instructionsManualView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String googleDocsUrl = "http://docs.google.com/viewer?url=";

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse(googleDocsUrl + instructionsManualURL), "text/html");
				startActivity(intent);

				// create new toast to update the user what video is about to be played
				Toast.makeText(getApplicationContext(), "Opening Instructions Manual",
						Toast.LENGTH_LONG).show();

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.itemdetails, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}
}
