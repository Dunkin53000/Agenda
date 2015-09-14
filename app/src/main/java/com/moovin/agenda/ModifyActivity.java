package com.moovin.agenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.moovin.agenda.Adapter.CardArrayAdapterJour;
import com.moovin.agenda.Utils.SharedPreference;

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

    private static final String TAG = "CardListActivity";
    private CardArrayAdapterJour cardArrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_activity);


        sharedPreference = new SharedPreference();
        listView = (ListView) findViewById(R.id.card_listViewCard);

        listView.addHeaderView(new View(this));
        View footerView = ((LayoutInflater) ModifyActivity.this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_modify, null, false);
        listView.addFooterView(footerView);

        cardArrayAdapter = new CardArrayAdapterJour(this, R.layout.list_item_cardjour);






        listView.setAdapter(cardArrayAdapter);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doask();




            }
        });





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



        CardJour card = new CardJour(classtext, "Debut : " + horairetextdebut + " Fin : " + horairetextfin, salletext);
        cardArrayAdapter.add(card);
        cardArrayAdapter.notifyDataSetChanged();
        sharedPreference.addFavorite(ModifyActivity.this,card,"Jour");

    }
}
