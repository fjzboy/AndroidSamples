package com.fjz.androidlittlesamples;

import android.app.Application;
import android.content.Intent;
import android.widget.Button;

import com.fjz.androidlittlesamples.backgroundjobscheduler.JobSchedulerActivity;
import com.fjz.androidlittlesamples.backgroundjobscheduler.MyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.assertj.core.api.Java6Assertions.assertThat;

//import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleUnitTest {

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
    }

    @Test
    public void addition_isCorrect() throws Exception {

        Application application = RuntimeEnvironment.application;
        MyService service = new MyService();
        service.onHandleIntent(new Intent());
        System.out.println("addiction is correct:" + 2);
        assertThat(2).isEqualTo(2);
    }

    @Test
    public void test_button_onJobSchedulerActivity() throws Exception{

        JobSchedulerActivity activity = Robolectric.setupActivity(JobSchedulerActivity.class);
        Button btn = (Button) activity.findViewById(R.id.btn_start_job);

        System.out.println("btn.getText().toString() = " + btn.getText().toString());
        assertThat("Start").isEqualTo(btn.getText().toString());
    }
}