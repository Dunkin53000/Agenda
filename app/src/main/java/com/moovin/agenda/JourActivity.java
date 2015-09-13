package com.moovin.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.moovin.agenda.Adapter.CardArrayAdapterJour;

/**
 * Created by pierre on 13/09/2015.
 */
public class JourActivity extends ActionBarActivity {

    private static final String TAG = "CardListActivity";
    private CardArrayAdapterJour cardArrayAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jour_activity);

        listView = (ListView) findViewById(R.id.card_listViewCard);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);

       fab.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Intent myIntent = new Intent(getBaseContext(), ModifyActivity.class);
               startActivity(myIntent);
           }
       });

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new CardArrayAdapterJour(this, R.layout.list_item_cardjour);


        CardJour card = new CardJour("Maths", "10h00-15h00", "Salle: 206");
        cardArrayAdapter.add(card);




        listView.setAdapter(cardArrayAdapter);
    }



}
