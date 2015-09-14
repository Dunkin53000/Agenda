package com.moovin.agenda.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.moovin.agenda.CardJour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pierre on 14/09/2015.
 */
public class SharedPreference {

    public static final String PREFS_NAME = "CARDJOUR";
    public static final String FAVORITES = "CARDJOURDUJOUR";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<CardJour> favorites,String FAVNAME) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVNAME, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, CardJour product,String favname) {
        List<CardJour> favorites = getFavorites(context, favname);
        if (favorites == null)
            favorites = new ArrayList<CardJour>();
        favorites.add(product);
        saveFavorites(context, favorites, favname);
    }

    public void removeFavorite(Context context, CardJour product, String favname) {
        ArrayList<CardJour> favorites = getFavorites(context, favname);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites, favname);
        }
    }

    public ArrayList<CardJour> getFavorites(Context context,String favname) {
        SharedPreferences settings;
        List<CardJour> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(favname)) {
            String jsonFavorites = settings.getString(favname, null);
            Gson gson = new Gson();
            CardJour[] favoriteItems = gson.fromJson(jsonFavorites,
                    CardJour[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<CardJour>(favorites);
        } else
            return null;

        return (ArrayList<CardJour>) favorites;
    }
}
