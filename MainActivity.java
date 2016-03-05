package com.example.cicctreads.midtermexam2;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private static final String BOOK_NAME = "title";
    private TextView mBookName;
    private static final String AUTHOR = "author";

    ArrayList<HashMap<String, String>> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());

        bookList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
        new FetchBooks().execute();
    }

    public void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    public void handleIntent(Intent intent){
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query =
                    intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String queryStr) {
        // get a Cursor, prepare the ListAdapter
        // and set it
    }


    public class FetchBooks extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... params) {


            try {
                //String query  = URLEncoder.encode("Victory Hugo", "UTF-8");
                String json = HttpUtils.getResponse("http://joseniandroid.herokuapp.com/api/books/","GET");
                Log.d("Response: ", json);
                if(json != null){
                    try {
                        JSONArray jArray = new JSONArray(json);


                        for(int i = 0;i < jArray.length();i++)
                        {
                            JSONObject b = jArray.getJSONObject(i);
                            String title = b.getString(BOOK_NAME);
                            //String author = b.getString(AUTHOR);
                            HashMap<String, String> book = new HashMap<String,String>();

                            book.put(BOOK_NAME,title);
                            // book.put("author",author);
                            bookList.add(book);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,bookList,R.layout.list_item,new String[]{"title"},new int[]{R.id.bookName});
            setListAdapter(adapter);

        }
    }
}