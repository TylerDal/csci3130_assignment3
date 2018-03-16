package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

//i'm getting errors here with the testing but this was my best shot at it

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CRUDTests {
    //for help on figuring out how to bring activities into testing I used the following
    //link: https://stackoverflow.com/questions/34683956/how-to-get-a-view-from-within-
    // espresso-to-pass-into-an-idlingresource/34791642 (top answer)

    private MyApplicationData appState;
    private ActivityTestRule<MainActivity> activityTest = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    //test for adding an item
    @Test
    public void addTest() throws Exception {
        ListView list;
        list = (ListView) activityTest.getActivity().findViewById(R.id.listView);

        int listCount = list.getAdapter().getCount();

        String testID = appState.firebaseReference.push().getKey();

        String name = "test";
        String number = "123456789";
        String business = "Fisher";
        String address = "";
        String province = "";

        Contact contact = new Contact(testID, name, number, business, address, province);
        appState.firebaseReference.child(testID).setValue(contact);

        assertEquals(listCount+1, list.getAdapter().getCount());
    }

    //test for deleting an item
    @Test
    public void deleteTest() throws Exception {
        ListView list;
        list = (ListView) activityTest.getActivity().findViewById(R.id.listView);

        String testID = appState.firebaseReference.push().getKey();

        String name = "test";
        String number = "123456789";
        String business = "Fisher";
        String address = "";
        String province = "";

        Contact contact = new Contact(testID, name, number, business, address, province);
        appState.firebaseReference.child(testID).setValue(contact);

        int listCount = list.getAdapter().getCount();

        appState.firebaseReference.child(testID).setValue(null);

        assertEquals(listCount-1, list.getAdapter().getCount());
    }

    //test for updating an item
    public void updateTest() throws Exception {
        String testID = appState.firebaseReference.push().getKey();

        String name = "test";
        String number = "123456789";
        String business = "Fisher";
        String address = "";
        String province = "";

        Contact contact = new Contact(testID, name, number, business, address, province);
        appState.firebaseReference.child(testID).setValue(contact);

        String newName = "newname";
        appState.firebaseReference.child(testID).child("name").setValue(newName);

        assertEquals(newName, appState.firebaseReference.child(testID).child("name").toString());
    }

    //test for reading an item
    public void readItem() throws Exception {
        String testID = appState.firebaseReference.push().getKey();

        String name = "test";
        String number = "123456789";
        String business = "Fisher";
        String address = "";
        String province = "";

        Contact contact = new Contact(testID, name, number, business, address, province);
        appState.firebaseReference.child(testID).setValue(contact);

        assertEquals(name, appState.firebaseReference.child(testID).child("name").toString());
    }
}

