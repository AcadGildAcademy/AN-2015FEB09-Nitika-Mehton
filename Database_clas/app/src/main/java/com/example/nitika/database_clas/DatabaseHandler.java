package com.example.nitika.database_clas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

            private static final int DATABASE_VERSION=1;
            private static final String DATABASE_NAME="contactsManger";
            private  static final String TABLE_CONTACTS ="contacts";

    //column
            private  static final String KEY_ID ="id";
            private  static final String KEY_NAME ="name";
           private  static final String KEY_DIS = "dis";
           private  static final String KEY_DATE = "date";
          private  static final String KEY_STATUS ="status";

    //constructor imp
            public DatabaseHandler(Context context)
        {
          super(context,DATABASE_NAME,null,DATABASE_VERSION);

        }

    @Override
    public void onCreate(SQLiteDatabase db)
                {
                    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                            + KEY_ID + " INTEGER PRIMARY KEY,"
                            + KEY_NAME + " TEXT,"
                            + KEY_DIS + " TEXT,"
                            +KEY_DATE + " TEXT ,"
                            +KEY_STATUS + " INTEGER "
                            + ")";
                    db.execSQL(CREATE_CONTACTS_TABLE);
                 }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);

    }

    //adding new contacts
    void addContact(Contact contact)
        {
            // open database
           SQLiteDatabase db = this.getWritableDatabase();//onCreate method will be called
            ContentValues values =new ContentValues();
            values.put(KEY_NAME,contact.getNAME());
            values.put(KEY_DIS,contact.getDIS());
            values.put(KEY_DATE,contact.getDATE());
            values.put(KEY_STATUS,contact.getStatus());
            db.insert(TABLE_CONTACTS,null,values);
            db.close();

        }

    //update

    public int updateContact(Contact contact)
        {
            SQLiteDatabase db = this.getWritableDatabase();//onCreate method will be called
            ContentValues values =new ContentValues();
            values.put(KEY_STATUS,contact.getStatus());

          return db.update(TABLE_CONTACTS,values,KEY_ID+"=?",new String[] { String.valueOf(contact.getID())});


        }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, null, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(id,
                cursor.getString(1), cursor.getString(2),cursor.getString(3),
                Integer.parseInt(cursor.getString(4)));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setNAME(cursor.getString(1));
                 contact.setDIS(cursor.getString(2));
                contact.setDATE(cursor.getString(3));
                contact.setStatus(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
