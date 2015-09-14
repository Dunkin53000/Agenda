package com.moovin.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.moovin.agenda.Adapter.CardArrayAdapterJour;
import com.moovin.agenda.Utils.SharedPreference;

import java.util.List;

/**
 * Created by pierre on 13/09/2015.
 */
public class JourActivity extends ActionBarActivity {

    private static final String TAG = "CardListActivity";
    private CardArrayAdapterJour cardArrayAdapter;
    private ListView listView;

    SharedPreference sharedPreference;
    List<CardJour> favorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jour_activity);


        sharedPreference = new SharedPreference();
        final Bundle extras = getIntent().getExtras();



        favorites = sharedPreference.getFavorites(JourActivity.this,extras.getString("JOUR"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        if (favorites == null) {

        } else {

            if (favorites.size() == 0) {


            }


            listView = (ListView) findViewById(R.id.card_listViewCard);




            listView.addHeaderView(new View(this));
            listView.addFooterView(new View(this));
            if (favorites != null) {
                cardArrayAdapter = new CardArrayAdapterJour(this, R.layout.list_item_cardjour, favorites);
                listView.setAdapter(cardArrayAdapter);


            }
        }



        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), ModifyActivity.class);
                myIntent.putExtra("JOUR",extras.getString("JOUR"));
                startActivity(myIntent);
            }
        });


    }



}
