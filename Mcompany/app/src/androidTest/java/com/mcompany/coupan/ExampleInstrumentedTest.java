package com.mcompany.coupan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    Context mMockContext;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        mMockContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mcompany.coupan", mMockContext.getPackageName());
    }

    private DatabaseReference mockedDatabaseReference;


    @Before
    public void before() {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:763160128750:android:dbd3fe90591edf8e") // Required for Analytics.
                .setApiKey("AIzaSyAtigxG7mQWkS9saiKMZG4pCo5K-SUoik0") // Required for Auth.
                .setDatabaseUrl("https://mcompanyproject-1528190529662.firebaseio.com") // Required for RTDB.
                .build();
        FirebaseApp.initializeApp(mMockContext /* Context */, options, "secondary");

// Retrieve secondary app.
        FirebaseApp secondary = FirebaseApp.getInstance("secondary");
// Get the database for the other app.
        FirebaseDatabase secondaryDatabase = FirebaseDatabase.getInstance(secondary);
    }

    @Before
    public void setUp() {
    }

    @Test
    public void isDataAvailable() {

//        Mockito.when(mockedDatabaseReference.child("merchants")).thenReturn(mockedDatabaseReference);
//
//        Mockito.doAnswer(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
//
//                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
//                //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)
//
//                valueEventListener.onDataChange(mockedDataSnapshot);
//                //valueEventListener.onCancelled(...);
//
//                return null;
//            }
//        }).when(mockedDatabaseReference).addListenerForSingleValueEvent(Mockito.any(ValueEventListener.class));

//        Utility.availableDealSize(new AvailableDealSizeListener() {
//            @Override
//            public void countDealAvailable(int size) {
//                int actual = size;
//                float expected = 0;
//                // expected value is 100
//                if (actual > 1) {
//                    expected = 1;
//                } else {
//                    expected = -1;
//                }
//
//
//                // use this method because float is not precise
//                assertEquals("Data For App is not available", expected, actual, 0.001);
//            }
//        });
    }
}
