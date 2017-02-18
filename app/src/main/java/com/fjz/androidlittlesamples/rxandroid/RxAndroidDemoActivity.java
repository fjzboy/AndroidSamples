package com.fjz.androidlittlesamples.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fjz.androidlittlesamples.R;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxAndroidDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android_demo);
    }

    public void just(View view) {

        Flowable<String> flowable = Flowable.just("Just...");
        flowable.subscribeOn(Schedulers.io())

                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        String mapStr = "map:"+s;
                        Logger.i("mapstr = %s", mapStr);
                        return mapStr;
                    }
                })

                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        String mapStr = "2 map:"+s;
                        Logger.i("2 mapstr = %s", mapStr);
                        return mapStr;
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.i("do on next:%s", s);
                    }
                })
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        Logger.i("doOnSubscribe:%s", subscription.toString());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String publisher) throws Exception {
                        Logger.i("from accept %s", publisher.toString());
                    }
                });
    }

    public void from(View view) {
    }

    public void map(View view) {
    }

    public void flatmap(View view) {
    }
}
