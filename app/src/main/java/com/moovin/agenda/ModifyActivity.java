package com.moovin.agenda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.moovin.agenda.Adapter.CardArrayAdapterJour;
import com.moovin.agenda.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;

/**
 * Created by pierre on 13/09/2015.
 */
public class ModifyActivity extends ActionBarActivity implements TimeRangePickerDialog.OnTimeRangeSelectedListener {

    String classtext = "";
    String salletext = "";
    String horairetextdebut = "";
    String horairetextfin = "";
    public static final String TIMERANGEPICKER_TAG = "timerangepicker";
    SharedPreference sharedPreference;
    List<CardJour> favorites = new ArrayList<CardJour>();


    private static final String TAG = "CardListActivity";
    private CardArrayAdapterJour cardArrayAdapter;
    private ListView listView;
    String key ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_activity);


        sharedPreference = new SharedPreference();
        final Bundle extras = getIntent().getExtras();
        key = extras.getString("JOUR");
        favorites = sharedPreference.getFavorites(ModifyActivity.this,extras.getString("JOUR"));


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


                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                        sharedPreference.removeFavorite(ModifyActivity.this,favorites.get(position - 1),key);
                        Toast.makeText(ModifyActivity.this, "Supprimé des favoris", Toast.LENGTH_SHORT).show();
                        cardArrayAdapter.remove(favorites.get(position - 1));
                        sharedPreference.saveFavorites(ModifyActivity.this, favorites,key);
                        cardArrayAdapter.notifyDataSetChanged();

                        actualise();

                        return true;
                    }
                });


            }




        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.attachToListView(listView);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doask();
            }
        });

    }


    public void actualise(){

        cardArrayAdapter.notifyDataSetChanged();
        cardArrayAdapter = new CardArrayAdapterJour(this, R.layout.list_item_cardjour, favorites);
        cardArrayAdapter.notifyDataSetChanged();
        listView.setAdapter(cardArrayAdapter);

    }

    public void doask() {




        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
        builder.setTitle("Nom du cours");
        builder.setMessage("Choississez un nom de cours !");


        final EditText input = new EditText(ModifyActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classtext = input.getText().toString();



                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
                builder.setTitle("Salle du cours");
                builder.setMessage("Choississez le numéro de la salle de cours !");


                final EditText input = new EditText(ModifyActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        salletext = input.getText().toString();


                        TimeRangePickerDialog timePickerDialog = TimeRangePickerDialog.newInstance(
                                ModifyActivity.this, true);


                        timePickerDialog.show(ModifyActivity.this.getSupportFragmentManager(), TIMERANGEPICKER_TAG);



                    }
                });


                builder.show();


            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin) {
        horairetextdebut = startHour + " : " + startMin;
        horairetextfin = endHour + " : " + endMin;

        Log.d("Cours", classtext);
        Log.d("Salle", salletext);
        Log.d("Horaires", "Debut : " + horairetextdebut + " Fin : " + horairetextfin);



        save();


    }

    public void save(){




        CardJour card = new CardJour(classtext,"De " + horairetextdebut + " à " + horairetextfin, salletext);
        sharedPreference.addFavorite(ModifyActivity.this,card,key);
        actualise();


    }
}
