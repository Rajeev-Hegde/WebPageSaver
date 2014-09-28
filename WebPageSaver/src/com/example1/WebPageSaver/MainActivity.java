package com.example1.WebPageSaver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
 
@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
public class MainActivity extends Activity {
		public static int limit=1;
	 private File folder1;
	    private File folder2;
	    public static String mainlink="";
	    private DrawerLayout mDrawerLayout;
	    private ActionBarDrawerToggle mDrawerToggle;
	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    private static String my_path;
	    private static int choice;
	    private static String my_url;
	    private static String f_name;
 
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
      
      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        File root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/");
       /* if (!root.exists()) {
            root.mkdirs();
        }*/
       // root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/Folder1/");
        /////////////////// url intent filter ////////////////
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
       
        if (Intent.ACTION_SEND.equals(action) && type != null) {
        	if (savedInstanceState != null) {
                return;
            }
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } 
        } 
        else{  
        	choice=3; 
        	 my_url="file:///android_res/drawable/index.html";
        	    Fragment fragment = new WebViewFragment();
             FragmentManager fragmentManager = getFragmentManager();
            // my_path = "file:///" + Rfolder1 + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "/index.html";
             fragmentManager.beginTransaction().replace(R.id.content_frame1, fragment).commit();
                             
          	                       
        }   
         
       ////////////////////////////////////////////////////////
        
        if (!root.exists()) {
            root.mkdirs();
        }
       
        mTitle="Web Page Saver";
        mDrawerTitle="Web Page Saver";
       // getActionBar().setDisplayHomeAsUpEnabled(true);
       /////////// open and close drawer with app icon  ///////////////
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
       
         mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            @Override
			public void onDrawerClosed(View view) {
               // super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            @Override
			public void onDrawerOpened(View drawerView) {
                //super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                
                invalidateOptionsMenu();
            }
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
       
        /////////////////////

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
        	 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 
            @Override
            public void onGroupExpand(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
            }
        });
 
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/
 
            }
        });
       
        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()  {
			
        	 @Override
             public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                 long packedPosition = expListView.getExpandableListPosition(position);
                 if (ExpandableListView.getPackedPositionType(packedPosition) == 
                         ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                     // get item ID's
                     final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                     final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

                     // handle data 
                     ///code to delete file and respective folders 
              //////////////////////////////////////
                     final Dialog myDialog = new Dialog(MainActivity.this);
             		
             		myDialog.setContentView(R.layout.custom_dialogue);
             		myDialog.setTitle("Action" );
                 	Button b_cancel = (Button) myDialog.findViewById( R.id.button1);
                 	TextView tv1 = (TextView) myDialog.findViewById(R.id.textView1);
             		b_cancel.setOnClickListener( 
             			new View.OnClickListener()
             			{
             				@Override
             				public void onClick(View arg0) 
             				{
             				
             					myDialog.dismiss();
             				
             				}
             			} 
             		);
             		tv1.setOnClickListener( 
                 			new View.OnClickListener()
                 			{
                 				@Override
                 				public void onClick(View arg0) 
                 				{
                 					 /////////////////////////////////////////////////
                             	/*	File Remfolder1 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "");
                                	File Remfolder2 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/Folder2/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "");
                                      if(listDataHeader.get(groupPosition)=="Full websites"){
                                    	  recursiveDelete(Remfolder1);
                                    	  updateExpandableListView();
                                      }
                                      else{
                                    	  //listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                                    	  recursiveDelete(Remfolder2);
                                    	  updateExpandableListView();
                                      }*/
                 					choice=1;
                                      File Remfolder1 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/" + listDataHeader.get(groupPosition) + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "");
                                      recursiveDelete(Remfolder1);
                                      updateExpandableListView();
                                      File childFolder =new File(Environment.getExternalStorageDirectory(),"WebPageSaver/" + listDataHeader.get(groupPosition) );
                                  	   String[] childFoldersList= childFolder.list();
                                  	   if(childFoldersList.length==0)
                                  	   {
                                  		   
                                  		 recursiveDelete(childFolder);
                                  		updateExpandableListView();

                                  	   }
                                  	
                                      mDrawerLayout.closeDrawer(expListView);
                                      Toast.makeText(getApplicationContext(),
                                               "Deleted sussessfully",
                                               Toast.LENGTH_SHORT).show();
                                      
                                  /////////////////////////////////////
                 					myDialog.dismiss();
                 				
                 				}
                 			} 
                 		);

        		myDialog.show();

                     
                     
             /////////////////////////////////////////
                    /* Toast.makeText(
                             getApplicationContext(),
                             listDataHeader.get(groupPosition)
                                     + " : "
                                     + listDataChild.get(
                                             listDataHeader.get(groupPosition)).get(
                                             childPosition)+ " LongClicked" , Toast.LENGTH_SHORT)
                             .show();*/
        		        	 
                     // return true as we are handling the event.
                     return true;
                 }
                 return false;
             }	});
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
              ///////////////////////////
             /*  Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                        listDataHeader.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT)
                        .show();*/
            	/////////////////////

            	//File Rfolder1 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/Folder1");
            	//File Rfolder2 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/Folder2");
                  File Rfolder = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/");
                	  choice = 1;
                	  //listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                	  Fragment fragment = new WebViewFragment();
                      FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                       my_path = "file:///" + Rfolder + "/" + listDataHeader.get(groupPosition) + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "/index.html";
                      fragmentManager.replace(R.id.content_frame1, fragment);
                      fragmentManager.addToBackStack(null);
                      fragmentManager.commit();
                      mTitle=listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                	 // WebView webView = (WebView) findViewById(R.id.content_frame);
                	 // WebSettings webSettings = webView.getSettings();
                	 // webSettings.setJavaScriptEnabled(true);
                	 // webView.setWebViewClient(new WebViewClient());
                	 // webView.loadUrl("file:///" + Rfolder2 + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "/index.html");
                	  mDrawerLayout.closeDrawer(expListView);
                	 
                  
                  


            	
            	/////////////////////
                return true;
            }
        });
       // createNotification();
   
        
        
 }
    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
        	//EditText box =(EditText)findViewById(R.id.input_url);
        	//box.setText("sldmsld");
        	/*Fragment fragment = getFragmentManager().findFragmentById(R.id.webFragment);
        	if(fragment instanceof WebViewFragment)
        		((WebViewFragment)fragment).updateWebView(sharedText);
        	 */
        	choice=2;
        	Fragment fragment = new WebViewFragment();
    		//Bundle args = new Bundle();
    		//args.putString(WebViewFragment.ARG_URL, myUrl);
            my_url = sharedText;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame1, fragment).commit();
             Toast.makeText(getApplicationContext(), sharedText,Toast.LENGTH_SHORT).show();
        
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
    	WebView webView = (WebView)findViewById(R.id.content_frame);
    	
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
           webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(expListView);
        menu.findItem(R.id.action_dropdown).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    ///////// Handle Menu Items //////////////////
    @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
    	Button b_ok;
    	Button b_cancel;
    	final EditText et;
    	final EditText et1;
		switch (item.getItemId()) {
		case R.id.menu_save_existing:
			 
		   /* List list = new ArrayList();
		    list.add("Item 1");
		    list.add("Item 2");
		    list.add("Item 3");
		    list.add("Item 4");*/
			    
			if(choice!=2){
				 Toast.makeText(getApplicationContext(), "Cannot download already downloaded page.",Toast.LENGTH_SHORT).show();
				return true;	 
			 }	
			//////////////////////////////////////////////////
			folder2 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/");
	        String[] file2Childs = folder2.list();
	        if(file2Childs.length==0){
	        	 Toast.makeText(getApplicationContext(), "There are no folders to save. Save in new folder.",Toast.LENGTH_SHORT).show();
	        	 return true;	
	        }
	        Arrays.sort(file2Childs);
	       /* List<String> folder2Childs=new ArrayList<String>();
			
			for(int i=0;i<file2Childs.length;i++){
				folder2Childs.add(file2Childs[i]);
			}*/
			final Dialog myDialog0 = new Dialog(this);
			
			myDialog0.setContentView(R.layout.saveinexisting);
			myDialog0.setTitle("Save This Page" );
			et = (EditText) myDialog0.findViewById( R.id.input_file_name );
			
			//spinner.setOnItemSelectedListener((OnItemSelectedListener) this);
			
			    //for(int i=0;i<file2Childs.length;i++){
			    	//dataAdapter.add(folder2Childs);
				//}
			   // spinner.setAdapter(dataAdapter);
			Button b_ok_ex = (Button) myDialog0.findViewById( R.id.button_save_existing);
			Button b_cancel_ex = (Button) myDialog0.findViewById( R.id.button_cancel_existing);
			//final EditText et = (EditText) myDialog.findViewById( R.id.input_folder_name );
			final Spinner spinner = (Spinner) myDialog0.findViewById(R.id.my_spinner);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			         android.R.layout.simple_spinner_item,file2Childs);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
			
			b_cancel_ex.setOnClickListener( 
				new View.OnClickListener()
				{
					@Override
					public void onClick(View arg0) 
					{
						myDialog0.dismiss();
						//et.setText("");
					}
				} 
			);
			
			b_ok_ex.setOnClickListener( 
					new View.OnClickListener()
					{
						@Override
						public void onClick(View arg0) 
						{
							myDialog0.dismiss();
							//Toast.makeText(getBaseContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
							f_name=spinner.getSelectedItem().toString() + "/" + et.getText().toString();
							doNetworkCompuation();
							//Toast.makeText(getBaseContext(), "Page has been saved to " + et.getText().toString(), Toast.LENGTH_LONG).show();
						}
					} 
				);
			
			
			
			myDialog0.show();
			///////////////////////////////////////////////
			
			
			return true;
		case R.id.menu_save_create:
			if(choice!=2){
				 Toast.makeText(getApplicationContext(), "Cannot download already downloaded page.",Toast.LENGTH_SHORT).show();
				return true;	 
			 }	
		     final Dialog myDialog = new Dialog(this);
				
				myDialog.setContentView(R.layout.saveinnew);
				myDialog.setTitle("Save This Page" );
				 b_ok = (Button) myDialog.findViewById( R.id.button_save);
			     b_cancel = (Button) myDialog.findViewById( R.id.button_cancel);
				 et = (EditText) myDialog.findViewById( R.id.input_file_name );
				 et1 = (EditText) myDialog.findViewById( R.id.input_folder_name );
					
				b_cancel.setOnClickListener( 
					new View.OnClickListener()
					{
						@Override
						public void onClick(View arg0) 
						{
							myDialog.dismiss();
							et.setText("");
						}
					} 
				);
				
				b_ok.setOnClickListener( 
						new View.OnClickListener()
						{
							@Override
							public void onClick(View arg0) 
							{
								myDialog.dismiss();
								String dirname=et1.getText().toString(); 
								// f_name = et.getText().toString();
								
	        		                f_name = dirname + "/" + et.getText().toString();
								 try{
								doNetworkCompuation();
								 }
								 catch(Exception e)
								 {
									 Toast.makeText(getApplicationContext(), "Could not download the page", Toast.LENGTH_SHORT).show();
								 }
								//Toast.makeText(getBaseContext(), "Page has been saved to " + et.getText().toString(), Toast.LENGTH_LONG).show();
							}
						} 
					);
				
				
				
				myDialog.show();
				
			return true;	
		
		case R.id.menu_help:
			choice=3;
			 my_url="file:///android_res/drawable/index.html";
     	    Fragment fragment = new WebViewFragment();
          FragmentManager fragmentManager = getFragmentManager();
         // my_path = "file:///" + Rfolder1 + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "/index.html";
          fragmentManager.beginTransaction().replace(R.id.content_frame1, fragment).commit();
            
          return true;
		} 
		
		return false; 
		//return super.onMenuItemSelected(featureId, item);
	}

    //////// to update expandablelist view////////
    private void updateExpandableListView(){
    	
    	expListView = (ExpandableListView) findViewById(R.id.lvExp);
    	 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
    	
    }
 
    //// To delete folder/////////
    private void recursiveDelete(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                recursiveDelete(child);

        fileOrDirectory.delete();
    }
    ///////////// handle button Go //////////
    public void handleInputUrl(View view) {
	    // Do something in response to button
    	choice = 2;
		String myUrl = "";
		EditText urlBox = (EditText) findViewById(R.id.input_url);
		myUrl = urlBox.getText().toString();
		
		Fragment fragment = new WebViewFragment();
		//Bundle args = new Bundle();
		//args.putString(WebViewFragment.ARG_URL, myUrl);
        my_url = myUrl;
        
            Pattern p = Patterns.WEB_URL;
            Matcher m = p.matcher(myUrl);
            if(m.matches()){
            	try {
					URI var1 = new URI(my_url);
					if(!var1.isAbsolute()){
						my_url = "http://" + my_url;
					}
					
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }                
            else
            {
            	my_url = "http://google.com";
            }
        
           // FragmentManager fragmentManager = getFragmentManager();
           // fragmentManager.beginTransaction().replace(R.id.content_frame1, fragment).commit();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame1, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
		
	}
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
	 private class LoadWebPageASYNC extends AsyncTask<String, Void, String> {
		  
		 
   	 String FileName="index.html";
        String Dir1=f_name;
		         @Override
		        protected String doInBackground(String... urls) {
		        	
		        	 //urls[0]="http://www.google.co.in/events/gcdc2013/css/default.css";
		        	//urls[0]="http://en.wikipedia.org/wiki/Engine";
		        	 
		        	 mainlink=urls[0];
		        	 HttpGet httpRequest = new HttpGet(urls[0]);

		        		HttpClient httpclient = new DefaultHttpClient();
		        		
		        			String result="dsmdsk";
		        		try {
		        			org.apache.http.HttpResponse response = httpclient.execute(httpRequest);
		        			
		        			 HttpEntity entity = response.getEntity();
		        		        // If the response does not enclose an entity, there is no need
		        		        // to worry about connection release

		        		        if (entity != null) {
		        		        	Log.i("Output", "inside if");
		        		            // A Simple JSON Response Read
		        		            InputStream instream = entity.getContent();
		        		             result= convertStreamToString(instream);
		        		            // now you have the string representation of the HTML request
		        		            ///Log.i("Output1", result);
		        		       
		        		            instream.close();
		        		            
		        		         
		        		           result= EctractLink(result,Dir1,urls[0]);
		        		            
		        		            //////////store the string into separate file/////////
		        		           
		        		            try
		        		            {
		        		                File root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/"+Dir1);
		        		                if (!root.exists()) {
		        		                    root.mkdirs();
		        		                }
		        		                File gpxfile = new File(root, FileName);
		        		                FileWriter writer = new FileWriter(gpxfile);
		        		                writer.append(result);
		        		                writer.flush();
		        		                writer.close();
		        		                //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
		        		            }
		        		            catch(IOException e)
		        		            {
		        		                 e.printStackTrace();
		        		                 //importError = e.getMessage();
		        		                 //iError();
		        		            }

		        		            /////////////////end///////////////
		        		        }
		        		} catch (ClientProtocolException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		} catch (IOException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}


		        	 
		        	 	
		        	//Toast.makeText(this, "dfkjdshfjkds", Toast.LENGTH_SHORT).show(); 
		        		//Log.i("Output2", result);
		        	 return result;
		         }
		         
		         		@Override
		                 protected void onPostExecute(String result) {
		         			
		         		////extract the link from the string content and download those link contents 
		         			
		         			 
		         		
		         			 //Log.i("Output2", result);
		         			Toast.makeText(getApplicationContext(), "Download complete!!",Toast.LENGTH_SHORT).show();
		         			updateExpandableListView();
		         			createNotification(); 
		        	        }
	 }
	 
	 private void doNetworkCompuation()
		{
		 
		 WebView current_web_view = (WebView) findViewById(R.id.content_frame);
		 String current_webUrl = current_web_view.getUrl();
		// EditText edt = (EditText) findViewById(R.id.input_url);
		// String urlstr = edt.getText().toString();
		 Toast.makeText(this, "This may take some time to download files!!",Toast.LENGTH_SHORT).show();
		 LoadWebPageASYNC task = new LoadWebPageASYNC();
		 Log.i("final_chk","Inside doNetworkComp...");
		 task.execute(new String[] { current_webUrl });
		 
		}
	 
	 
	 
	 public static String EctractLink(String result, final String dir,String currentpageurl) {
			//String uniqueId="img5a.flixcart.com.css/www/promos/new/20140328-120445-routers-p1.jpg";
			//uniqueId=uniqueId.replace(uniqueId, ".20140328-120445-routers-p1.jpg");
			
			//Log.i("poiu",uniqueId);
			//String cssname="";
			 String finalres=new String(result);
			 
			  
			  String linkfinal="";
			    // String id="1";
			     String name="";
			//int i=0;
	
			 Document doc=Jsoup.parse(result);
			// Elements links = doc.select("a[href]");
			 Elements media = doc.select("[src]");
		     Elements imports = doc.select("link[href]");
		    	 ////this is for handling each css links///
			   
		     for(Element csslink: imports){
		    	//int z=0;
		    	 // i++;
		    	// uniqueId=uniqueId+id;
		    	 linkfinal="";
		    	 if(csslink.attr("href").contains("android-app:"))continue;
		    	 if(!csslink.attr("href").endsWith("/"))
		    	 {   
		    		         
		    		///call getlink function to get proper link format////
		    		linkfinal= getlink(csslink.attr("href"),currentpageurl);
		    	 
		    	
		    	 }
		    	 
		    	 
		    	// Toast.makeText(getApplicationContext(),csslink.attr("href")+":"+uniqueId,Toast.LENGTH_SHORT).show();
		    	
		    	 if(linkfinal!="")
		    	{		
		    		 
		    		 //extract the name of the css file from the link
		    		 int indexname = csslink.attr("href").lastIndexOf("/");
		    		 if(indexname==-1){
		    			 
		    			 name=csslink.attr("href");
		    		 }
		    		 else{
		    			 
			 	         name =csslink.attr("href").substring(indexname+1, csslink.attr("href").length());
			 	        if(name.lastIndexOf('?')!=-1)
			 	         {
			 	        	 name=name.substring(0,name.lastIndexOf('?') );
			 	        	 Log.i("sdfg",csslink.attr("href")+":"+name);
			 	         }
			 	          Log.i("qwerty",name);
		    		 }
		    		 
		    		 Log.i("Out1",csslink.attr("href")+"  :  "+indexname+"  :  "+name);
		    	// Toast.makeText(getApplicationContext(),csslink.attr("href")+":"+name,Toast.LENGTH_SHORT).show();	
		    		 
		    		 
		    		 if(name.contains(".jpg")||name.contains(".png")||name.contains(".gif")||name.contains(".bmp")||name.contains(".webp")||name.contains(".svg")){
		    			 saveImg(linkfinal,dir,name);
		    		 }
		    		 else{
		    			 if(name.contains(".css")==false){
			 	        	//finalres= finalres.replaceFirst(name, name+".css");
		    				// z++;
		    				 if(name.contains(".php"))
		    					 {
		    					 name=name.replace("php", "css");
		    					
		    					 finalres=finalres.replaceAll(Pattern.quote(csslink.attr("href")), name);
		    					 //String var="//bits.wikimedia.org/en.wikipedia.org/load.php?debug=false&amp;lang=en&amp;modules=site&amp;only=styles&amp;skin=vector&amp;*";
		    					 if(csslink.attr("href").contains("load.php")){
		    						 /*
		    						 Pattern pattern=Pattern.compile(Pattern.quote(csslink.attr("href")));
		    						 Matcher m =pattern.matcher(finalres);
		    						 finalres=m.replaceAll(name);
		    						 */
		    						 
		    						String[] var= csslink.attr("href").split("\\?");
		    						 finalres=finalres.replaceFirst(var[0], name);
		    						 //finalres=finalres.replace(var[1], "");
			    					 //finalres=finalres.replace(var, name);
		    					 }
		    					 }
		    				 else{
			 	        	 name=name+".css";
		    				 }
			 	         }
		    	
		    //if the link is of type 
		    //  //bits.wikimedia.org/en.wikipedia.org/load.php?debug=false&amp;lang=en&amp;modules=site&amp;only=styles&amp;skin=vector&amp;*
		   		// linkfinal="//bits.wikimedia.org/en.wikipedia.org/load.php?debug=false&amp;lang=en&amp;modules=site&amp;only=styles&amp;skin=vector&amp;*";
		    		if(linkfinal.contains("&amp;"))
		    			linkfinal=linkfinal.replace("&amp;", "&");
		    		
		    		
		    		
		    		Log.i("phpcss",csslink.attr("href")+" : "+ name);
		    			 savelink(linkfinal,dir,name); 
		    			 
		    			
		    			 
		    		 }
		    	}
		    	 
		    	 //Pattern pattern=Pattern.compile(Pattern.quote(csslink.attr("href")));
				// Matcher m =pattern.matcher(finalres);
				// finalres=m.replaceAll(name);
		    	 finalres= finalres.replaceFirst(Pattern.quote(csslink.attr("href")), name);
		    	
		 	     
		     }

		     
		     
		     
		 /////this is for handling media files which contains both links to images as well as script////    
		     for(Element src : media){
				 ////////this is for img tag data-src////
			    	if(src.attr("data-src") != null){
			    		linkfinal="";
			    		 if(!src.attr("data-src").endsWith("/"))
				    	 {   
				    		        
				    		///call getlink function to get proper link format////
				    		linkfinal= getlink(src.attr("data-src"),currentpageurl);
				    		Log.i("name",linkfinal+":"+src.attr("data-src"));	 
				    	
				    	 }
			    		 if(src.attr("data-src")!="")
			    		{
			    			 int indexname = src.attr("data-src").lastIndexOf("/");
				    		 if(indexname==-1){
				    			 
				    			 name=src.attr("data-src");
				    		 }
				    		 else{
				    			 
					 	         name =src.attr("data-src").substring(indexname+1, src.attr("data-src").length());
					 	        
					 	        if(name.lastIndexOf('?')!=-1)
					 	         {
					 	        	 name=name.substring(0,name.lastIndexOf('?') );
					 	        	// Log.i("sdfg",csslink.attr("href")+":"+name);
					 	         }
					 	       Log.i("disp3",src.attr("data-src"));
					 	      Log.i("disp4",name);
					 	        //finalres=finalres.replace("data-src=", "src=");
					 	       
				    		 }
			    			//call the saveImg function(present below) to save images 
				    		 final String linkfinal1=linkfinal;
				    		 final String name1=name;
				    		 new Thread(new Runnable() {
				 				@Override
				 	            public void run() {
		 
				    		 saveImg(linkfinal1,dir,name1);
				 				}
				    		 }).start();
				    		 
				    			 finalres= finalres.replaceFirst(Pattern.quote(src.attr("data-src")), name);
				    		 
				    		 
				    		
			    		}
			    	}
			    		
		
		    	 
		    	 
		    	 
		    	 linkfinal="";
		    	 if (src.tagName().equals("img")){
		    		 Log.i("datasrc",src.attr("data-src"));
		    		 if(!src.attr("src").endsWith("/"))
			    	 {   
			    		        
			    		///call getlink function to get proper link format////
			    		linkfinal= getlink(src.attr("src"),currentpageurl);
			    		Log.i("name",linkfinal+":"+src.attr("src"));	 
			    	
			    	 }
		    		 if(src.attr("src")!="")
		    		{
		    			 int indexname = src.attr("src").lastIndexOf("/");
			    		 if(indexname==-1){
			    			 
			    			 name=src.attr("src");
			    		 }
			    		 else{
			    			 
				 	         name =src.attr("src").substring(indexname+1, src.attr("src").length());
				 	         
				 	        
				 	        finalres= finalres.replace(src.attr("src"), name);
				 	        
				 	        //finalres=finalres.replace("data-src=", "src=");
				 	       
			    		 }
			    		 
			    		 
			    		 final String linkfinal1=linkfinal;
			    		 final String name1=name;
			    		 new Thread(new Runnable() {
			 				@Override
			 	            public void run() {
	 
			    		 saveImg(linkfinal1,dir,name1);
			 				}
			    		 }).start();
			    		 
			    		
		    		}
		    		 
		    		
		    	    	 }
		    	 else{
		    		 //code to manage script files
		    		 if(!src.attr("src").endsWith("/"))
			    	 {   
			    		        
			    		///call getlink function to get proper link format////
			    		linkfinal= getlink(src.attr("src"),currentpageurl);
			    		//Log.i("out1",src.attr("src")+":"+linkfinal);
			    		
			    		 if(src.attr("src")!="")
				    		{ 	Log.i("name",src.attr("src"));
				    			 int indexname = src.attr("src").lastIndexOf("/");
					    		 if(indexname==-1){
					    			 
					    			 name=src.attr("src");
					    		 }
					    		 else{
					    			 
						 	         name =src.attr("src").substring(indexname+1, src.attr("src").length());
						 	        if(name.lastIndexOf('?')!=-1)
						 	         {
						 	        	 name=name.substring(0,name.lastIndexOf('?') );
						 	         }
						 	       
		    						 
						 	        finalres= finalres.replaceFirst(Pattern.quote(src.attr("src")),name);
						 	        //Log.i("name",src.attr("src")+":"+name);
					    		 }
				    			 
				    			
					    		 if(linkfinal.contains("&amp;"))
						    			linkfinal=linkfinal.replace("&amp;", "&");
					    		 
					    		 if(name.contains(".php"))
					    			 {
					    			 name=name.replace("php", "js");
					    			 String[] var= src.attr("src").split("\\?");
					    			 finalres=finalres.replaceFirst(var[0], name);
					    			 }
					    		// Log.i("phpjs1", name);
				    		      	savelink(linkfinal,dir,name);
				    		}
			    	
			    	 }
		    	 }

		     }
		    
		     finalres=finalres.replaceAll("src=\"\"", "");
		     	    	 
			 return finalres;
			 
		 }
		 
	 private static void extractLink_From_Css(String result,String curlink,String css_name,String dir)
	 {		
		 limit--;
		  String REGEX1 = "url\\( *'([^)':]*)' *\\)";
	      String REGEX2 = "url\\( *\"([^)':]*)\" *\\)";
		  String REGEX3 = "url\\(([^):]*)\\)";
		  String REGEX4 = "@import '(.*)'";
		  String REGEX5 = "@import \"(.*)\"";
		  
		  try
		    {
			  Log.i("qwerty33",css_name);
		      BufferedReader in = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory()+"/WebPageSaver/"+dir+"/"+css_name));
		      String line = null;
		      while((line = in.readLine()) != null)
		      {
		    	 
		       result=matchParts(result,line, REGEX1,curlink,dir,css_name);
		       result=matchParts(result,line, REGEX2,curlink,dir,css_name);
		       result=matchParts(result,line, REGEX3,curlink,dir,css_name);
		       result=matchParts(result,line, REGEX4,curlink,dir,css_name);
		       result=matchParts(result,line, REGEX5,curlink,dir,css_name);
		      }
		      in.close();
		    } catch (Exception e)
		    { e.printStackTrace(); } 
		 
						  try
				          {
						    	Log.i("count333", dir);
						    	
				              File root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/"+dir);
				              if (!root.exists()) {
				                  root.mkdirs();
				              }
				              File modified_cssfile = new File(root,css_name);
				              FileWriter writer = new FileWriter(modified_cssfile,false);
				              writer.write(result);
				              writer.flush();
				              writer.close();
				              //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
				          }
				          catch(IOException e)
				          {
				               e.printStackTrace();
				               //importError = e.getMessage();
				               //iError();
				          }
		
		    
		    

	 }
	 
	 private static String matchParts(String result, String aText , String regex,String curlink,String dir,String css_name ){
		    Pattern pattern = Pattern.compile( regex );
		    Matcher matcher = pattern.matcher( aText );
		    while ( matcher.find() ) {
		    	
		    	//EctractLink(result,dir,curlink);
		    	
		    	
		      if(matcher.group(1).contains(".css")==true){
		    	  Log.i("reg3333", matcher.group(1));
		    	String name="";
		    	String linkfinal="";
		    	 if(!matcher.group(1).endsWith("/"))
		    	 {   
		    		         
		    		///call getlink function to get proper link format////
		    		//linkfinal= getlink(matcher.group(1),curlink);
		    		//Log.i("init",linkfinal);
		    		
				    		 URI u;
				 			try {
				 				 u = new URI(matcher.group(1));
				 				if((matcher.group(1).indexOf('/')==0)&&(!(matcher.group(1).indexOf("//")==0))){
					    			linkfinal=mainlink+"/"+matcher.group(1);
					    			Log.i("link1","linktype1::"+linkfinal);
				    			 }
					    		else if((matcher.group(1).indexOf("//")==0)||((u.isAbsolute()))){
					    			if(u.isAbsolute()){
					 					linkfinal=matcher.group(1);
					 					}
					    			else{
					    			linkfinal="http:"+matcher.group(1);
					    			}
					    			Log.i("link2","linktype2::"+linkfinal);
					    		}
				    			 else{
				    				 
				    				 int innerindex=curlink.lastIndexOf("/");
				    				 linkfinal=curlink.substring(0,innerindex)+"/"+matcher.group(1);
				    				 Log.i("link3","linktype2::"+linkfinal);
				    			 }
					    			
				 			}
				 			catch (URISyntaxException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
								linkfinal="";
							}
				 		
		    		
		    		
		    	 }
		    	 
		    	 
		    	// Toast.makeText(getApplicationContext(),csslink.attr("href")+":"+uniqueId,Toast.LENGTH_SHORT).show();
		    	
		    	 if(linkfinal!="")
		    	{		
		    		 
		    		 //extract the name of the css file from the link
		    		 int indexname = matcher.group(1).lastIndexOf("/");
		    		 if(indexname==-1){
		    			 
		    			 name=matcher.group(1);
		    		 }
		    		 else{

			 	         name =matcher.group(1).substring(indexname+1, matcher.group(1).length());
			 	        if(name.lastIndexOf('?')!=-1)
			 	         {
			 	        	 name=name.substring(0,name.lastIndexOf('?') );
			 	        	// Log.i("sdfg",csslink.attr("href")+":"+name);
			 	         }
			 	          Log.i("qwerty333",name);
		    		 }
		    		 
		    		// Log.i("Out1",csslink.attr("href")+"  :  "+indexname+"  :  "+name);
		    	// Toast.makeText(getApplicationContext(),csslink.attr("href")+":"+name,Toast.LENGTH_SHORT).show();	
		    		 
		    		 
		    		 	 
		    			 savelink(linkfinal,dir,name); 
		    			 
		    			 
		    			 
		    		 
		    	}
		    	 
		    		 
		    	 result= result.replaceFirst(Pattern.quote(matcher.group(1)), name);	
		    	 
		    	//Log.i("zxz",result);
		    	
		 
		    	  
		    	 
		    	 
		      }
		    }
		    return result;
		    
		    /*
		    try
            {
		    	Log.i("count333", dir);
		    	
                File root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/Folder2/"+dir);
                if (!root.exists()) {
                    root.mkdirs();
                }
                File modified_cssfile = new File(root,css_name);
                FileWriter writer = new FileWriter(modified_cssfile,false);
                writer.write(result);
                writer.flush();
                writer.close();
                //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }
            catch(IOException e)
            {
                 e.printStackTrace();
                 //importError = e.getMessage();
                 //iError();
            }
		
		    
		    
		    
		    */
		    
		  }
			 
	 /////this is for saving css links into seperate files///////////////
	 private static  boolean savelink(String link,String dir,String uniqueId){
		 /////////////////
		
		 HttpGet httpRequest = new HttpGet(link);

		HttpClient httpclient = new DefaultHttpClient();
			String result="dsmdsk";
		try {
			org.apache.http.HttpResponse response = httpclient.execute(httpRequest);
			
			 HttpEntity entity = response.getEntity();
		        // If the response does not enclose an entity, there is no need
		        // to worry about connection release

		        if (entity != null) {
		        	//Log.i("Output", "inside if");
		            // A Simple JSON Response Read
		            InputStream instream = entity.getContent();
		             result= convertStreamToString(instream);
		            // now you have the string representation of the HTML request
		            ///Log.i("Output1", result);
		       
		            instream.close();
		            
		         
		            
		            
		            //////////store the string into separate file/////////
		           
		            try
		            {
		                File root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/"+dir);
		                if (!root.exists()) {
		                    root.mkdirs();
		                }
		                File gpxfile = new File(root, uniqueId);
		                FileWriter writer = new FileWriter(gpxfile);
		                writer.append(result);
		                writer.flush();
		                writer.close();
		                //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
		            }
		            catch(IOException e)
		            {
		                 e.printStackTrace();
		                 //importError = e.getMessage();
		                 //iError();
		            }

		            /////////////////end///////////////
		        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((limit!=0)&&(!(uniqueId.contains(".js")))){
			extractLink_From_Css(result,link, uniqueId,dir);
			limit++;
			}
		return true;

////////////////////////////////
		 	
		 }
	 
	 

	 /////////////////this is for saving images///////////////////////
	// @SuppressWarnings({ "deprecation", "resource" })
	private static  void saveImg(String src,String dir,String name){
		// String folderPath = dir;
		/* int indexname = src.lastIndexOf("/");
		 if (indexname == src.length()) {
			 	            src = src.substring(1, indexname);
			 	        }
		 
		 indexname = src.lastIndexOf("/");
		 	        String name = src.substring(indexname, src.length()); */
		       File root = new File(Environment.getExternalStorageDirectory(), "WebPageSaver/"+dir);
			  if (!root.exists()) {
			        root.mkdirs();
			    }
			    File  file = new File(root, name);
			   
			   // Bitmap img;
			    InputStream is;
			   // Scanner sc = null;
			    try {    
			        // Make sure the Pictures directory exists.
			    	//  File file2;
			    	    
			    	    
			    // src= URLEncoder.encode(src,"UTF-8");
			    	 Log.i("out9",src);   
			        URL imageurl = new URL(src);
			  
			        /* Open a connection to that URL. */
			        URLConnection ucon = (HttpURLConnection)imageurl.openConnection();
			       // ucon.setDoOutput(true);
			        ((HttpURLConnection) ucon).setRequestMethod("GET");
			        ucon.setDoOutput(false);
                    ucon.connect();
			        /*
			         * Define InputStreams to read from the URLConnection.
			         */
			        is =new BufferedInputStream(ucon.getInputStream()) ;
			        ByteArrayOutputStream out = new ByteArrayOutputStream();
			        OutputStream os = new FileOutputStream(file);
			        byte[] data = new byte[1024];
			         ////if able to read data then execute if(fast implementation) else execute else(slower method)
			        int n = 0;
			        //if(n==0)
			        //{
			        	while (-1!=(n=is.read(data)))
			        	{
			        	   out.write(data, 0, n);
			        	}
			        	
			        	byte[] response = out.toByteArray();
			        	FileOutputStream fos = new FileOutputStream(file);
			        	fos.write(response);
			        	fos.close();
			       
			        	// Log.i("byte1","inside if");
			        //}
			        //else{
			        	
			        	//img=BitmapFactory.decodeStream(is);
			        	//img.compress(Bitmap.CompressFormat.JPEG,100, os);
			        
			        	
			        	/*
			        	 for (int b; (b = is.read()) != -1;) {
				  	            os.write(b);
				  	        } 
			        	 /*
			        	 Log.i("byte","inside else");
			        	 sc = new Scanner(is, "UTF-8");
			        	    while (sc.hasNextLine()) {
			        	        byte[] line = sc.nextLine().getBytes();
			        	        os.write(line);
			        	        // System.out.println(line);
			        	    }
			        	    // note that Scanner suppresses exceptions
			        	    if (sc.ioException() != null) {
			        	        throw sc.ioException();
			        	    }
			        	    */
			        //}
			       
			        is.close();
			        os.close();
			       	
			    } catch (IOException e) {
			        Log.d("ImageManager", "Error: " + e);
			    }
			 
					
	 }
	
	 
	 public static String getlink(String requiredlink ,String currentpageurl ){
		 
		 Log.i("out5",requiredlink);
		 String csslinkfinal="";
		 URI u;
			try {
				 u = new URI(requiredlink);
				if((u.isAbsolute())||(requiredlink.indexOf("//")==0)){
				
					if(u.isAbsolute()){
					csslinkfinal=requiredlink;
					}
					else{
						csslinkfinal="http:"+requiredlink;
					}
				}
				else
				{
					if(currentpageurl.endsWith("/"))
					{
						currentpageurl = currentpageurl.substring(0, currentpageurl.length() - 1);
					}
					
					csslinkfinal=currentpageurl+"/./"+requiredlink;
					
				}
			
			}
			
			
			catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				csslinkfinal="";
			}
			return csslinkfinal;
			
	 }
	  
	 @SuppressWarnings("deprecation")
		public void createNotification() {
		    // Do something in response to button
			
			//reference to the NotificationManager
	        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	        int notifId = 12 ;

	        String MyText = "OfflineView Reminder " ;
	        Notification mNotification = new Notification(R.drawable.ic_launcher, MyText, System.currentTimeMillis() );
	        //The three parameters are: 1. an icon, 2. a title, 3. time when the notification appears

	        String MyNotificationTitle = "Download Status";
	        String MyNotificationText  = "Download complete!";

	        Intent myIntent = new Intent( this, MainActivity.class );
	        myIntent.putExtra( "NotifIdn", notifId);
	        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        //Log.d ( "MyLog", "Just put int notifId = " + notifId );
	        
	        PendingIntent StartIntent = PendingIntent.getActivity(getApplicationContext(),0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
	        //A PendingIntent will be fired when the notification is clicked. The FLAG_CANCEL_CURRENT flag cancels the pending intent

	        mNotification.setLatestEventInfo(getApplicationContext(), MyNotificationTitle, MyNotificationText, StartIntent);

	        notificationManager.notify(notifId , mNotification);  
	        //We are passing the notification to the NotificationManager with a unique id.
		
		}
private static String convertStreamToString(InputStream is) {

     BufferedReader reader = new BufferedReader(new    
                             InputStreamReader(is));
       StringBuilder sb = new StringBuilder();
        String line = null;
        try {
               while ((line = reader.readLine()) != null) {
                       sb.append(line + "\n");
               }
          } catch (IOException e) {
               e.printStackTrace();
          } finally {
               try {
                       is.close();
               } catch (IOException e) {
                       e.printStackTrace();
               }
           }
       return sb.toString();
 }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Preparing the list data
     */
    
    private void prepareListData() {
    	
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
       // listDataHeader.add("Full websites");
       // listDataHeader.add("Groups");
        //listDataHeader.add("Coming Soon..");
        
        File webViewFolder =new File(Environment.getExternalStorageDirectory(),"WebPageSaver");
        String[] webViewChilds=webViewFolder.list();
        Arrays.sort(webViewChilds);
        int i,j;
        String[] childFoldersList;
        for(i=0;i<webViewChilds.length;i++)
        {
        	listDataHeader.add(webViewChilds[i]);
        	File childFolder =new File(Environment.getExternalStorageDirectory(),"WebPageSaver/" + webViewChilds[i] );
        	childFoldersList= childFolder.list();
        	Arrays.sort(childFoldersList);
        	List<String> childFiles=new ArrayList<String>();
        	for(j=0;j<childFoldersList.length;j++)
        	{
        		childFiles.add(childFoldersList[j]);
            	
        	}
        	listDataChild.put(listDataHeader.get(i), childFiles); // Header, Child data
        }
        
        
        
      /*  folder1 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/Folder1");
        folder2 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/Folder2");
        
        String[] file1Childs = folder1.list();
        String[] file2Childs = folder2.list();

        Arrays.sort(file1Childs);
        Arrays.sort(file2Childs);
        
        
        List<String> folder1Childs=new ArrayList<String>();
        List<String> folder2Childs=new ArrayList<String>();
		for(i=0;i<file1Childs.length;i++){
        	folder1Childs.add(file1Childs[i]);
        	
        	
        }
		for(i=0;i<file2Childs.length;i++){
			folder2Childs.add(file2Childs[i]);
		}
        
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
 
        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");
 
        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");
    
        listDataChild.put(listDataHeader.get(0), folder1Childs); // Header, Child data
        listDataChild.put(listDataHeader.get(1), folder2Childs);
       // listDataChild.put(listDataHeader.get(2), comingSoon);*/
    }
   // @JavascriptInterface
    public static class WebViewFragment extends Fragment {
        //public static final String ARG_PLANET_NUMBER = "planet_number";
    	final static String ARG_PATH = "";
    	final static String ARG_URL = "";
        public WebViewFragment() {
            // Empty constructor required for fragment subclasses
        }

        //@SuppressWarnings("deprecation")
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.webviewfragment, container, false);
         //  WebView webView = (WebView) rootView.findViewById(R.id.content_frame);
      	 // WebSettings webSettings = webView.getSettings();
      	 // webSettings.setJavaScriptEnabled(true);
      	 // webView.setWebViewClient(new WebViewClient());
      	 // webView.loadUrl("file:///" + Rfolder2 + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + "/index.html");
      	//File Rfolder2 = new File (Environment.getExternalStorageDirectory(),"WebPageSaver/Folder2");
      	//  webView.loadUrl("file:///" + Rfolder2 + "/sample3/index.html"); 
      	//Bundle args = getArguments();
      	  
      	
      	  return rootView;
        }
		@JavascriptInterface
        @Override
        public void onStart() {
            super.onStart();

            // During startup, check if there are arguments passed to the fragment.
            // onStart is a good place to do this because the layout has already been
            // applied to the fragment at this point so we can safely call the method
            // below that sets the article text.
           // Bundle args = getArguments();
           // updateWebView(args.getString(ARG_PATH));
            /*if (args != null) {
                // Set article based on argument passed in
                updateArticleView(args.getInt(ARG_POSITION));
            } else if (mCurrentPosition != -1) {
                // Set article based on saved instance state defined during onCreateView
                updateArticleView(mCurrentPosition);
            }*/
            if(choice == 1){
          		//webView.clearView();
          		//webView.loadUrl(my_path);
            	updateWebView(my_path);
          	  }
            else if(choice == 2)
            {
            	//String url_str = getArguments().getString(ARG_URL);
            	EditText edt = (EditText) getActivity().findViewById(R.id.input_url);
            	edt.setText(my_url);
            	WebView webView = (WebView) getActivity().findViewById(R.id.content_frame);
            	WebSettings webSettings = webView.getSettings();
            	  webSettings.setJavaScriptEnabled(true);
            	  webView.setWebViewClient(new WebViewClient());
            	  webSettings.setBuiltInZoomControls(true);
            	  webView.setVerticalScrollBarEnabled(false);
            	  webView.setHorizontalScrollBarEnabled(false);
            	  webView.loadUrl(my_url);
            	  
            	
            }
            else {
            	WebView webView = (WebView) getActivity().findViewById(R.id.content_frame);
            	WebSettings webSettings = webView.getSettings();
            	  webSettings.setJavaScriptEnabled(true);
            	  webView.setWebViewClient(new WebViewClient());
            	  webSettings.setBuiltInZoomControls(true);
            	  webView.setVerticalScrollBarEnabled(false);
            	  webView.setHorizontalScrollBarEnabled(false);
            	  webView.loadUrl(my_url);
            	  
            }
            
            
        }
		
        public void updateWebView(String file_path) {
        	EditText edt = (EditText) getActivity().findViewById(R.id.input_url);
        	edt.setText(my_url);
        	WebView webView = (WebView) getActivity().findViewById(R.id.content_frame);
        	WebSettings webSettings = webView.getSettings();
        	  webSettings.setJavaScriptEnabled(true);
        	  webView.setWebViewClient(new WebViewClient());
        	  webSettings.setBuiltInZoomControls(true);
        	  webView.setVerticalScrollBarEnabled(false);
        	  webView.setHorizontalScrollBarEnabled(false);
        	  webView.loadUrl(file_path);
            
        }
        
       
    }
   
   
}