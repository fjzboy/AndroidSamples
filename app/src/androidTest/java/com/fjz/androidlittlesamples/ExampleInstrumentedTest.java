package com.fjz.androidlittlesamples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fjz.androidlittlesamples.unittest.UnitTestService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.fjz.androidlittlesamples", appContext.getPackageName());
    }


    @Test
    public void setSharePreference() {
        Context context = InstrumentationRegistry.getTargetContext();

        SharedPreferences sp = context.getSharedPreferences("test_com_fjz_androidlittlesamples", Context.MODE_PRIVATE);

        sp.edit().putString("test_sp_key", "test_sp_value").commit();

        String test = sp.getString("test_sp_key", "hah");

        System.out.println("test from sp is = " + test);

        assertEquals("test_sp_value", test);
    }

    @Test
    public void testService() {
        Context context = InstrumentationRegistry.getTargetContext();
        context.startService(new Intent(context, UnitTestService.class)
                .putExtra("service_action", "This is from testing"));
    }

}
