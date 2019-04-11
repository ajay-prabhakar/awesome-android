/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.data.petContract;
import com.example.android.pets.data.petDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {


    private petDbHelper mPetDbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mPetDbhelper = new petDbHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                InsertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void InsertPet() {
        SQLiteDatabase db = mPetDbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(petContract.petEntry.COLUMN_PET_NAME, "Toto");
        values.put(petContract.petEntry.COLUMN_PET_BREED, "Terrier");
        values.put(petContract.petEntry.COLUMN_PET_GENDER, petContract.petEntry.GENDER_MALE);
        values.put(petContract.petEntry.COLUMN_PET_WEIGHT, 7);

        db.insert(petContract.petEntry.TABLE_NAME, null, values);

    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        petDbHelper mDbHelper = new petDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        String[] projection = new String[]{petContract.petEntry._ID, petContract.petEntry.COLUMN_PET_BREED, petContract.petEntry.COLUMN_PET_GENDER,
        petContract.petEntry.COLUMN_PET_NAME, petContract.petEntry.COLUMN_PET_WEIGHT};

        Cursor cursor =db.query(petContract.petEntry.TABLE_NAME,projection,null,null,
                null,null,null);
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(petContract.petEntry._ID + " - " +
                    petContract.petEntry.COLUMN_PET_NAME + " "+petContract.petEntry.COLUMN_PET_BREED+petContract.petEntry.COLUMN_PET_GENDER+petContract.petEntry.COLUMN_PET_WEIGHT+"\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(petContract.petEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(petContract.petEntry.COLUMN_PET_NAME);
            int BreedColumn=cursor.getColumnIndex(petContract.petEntry.COLUMN_PET_BREED);
            int genderColumn =cursor.getColumnIndex(petContract.petEntry.COLUMN_PET_GENDER);
            int weightColumn = cursor.getColumnIndex(petContract.petEntry.COLUMN_PET_WEIGHT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String curremtBreed =cursor.getString(BreedColumn);
                String curremtGender =cursor.getString(genderColumn);
                String curremtWeight =cursor.getString(weightColumn);



                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName +" "+curremtBreed+" "+curremtGender+"  "+curremtWeight));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }
}