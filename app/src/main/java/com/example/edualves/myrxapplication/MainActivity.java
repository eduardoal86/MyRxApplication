package com.example.edualves.myrxapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);

                //Once the Observable has emitted all items in the sequence, call onComplete//
                e.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Observer", "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {

                if (integer >= 2)
                    Log.d("Observer", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Observer", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d("Observer", "onComplete: All Done!");
            }
        };

        observable.subscribe(observer);


        Observable<Boolean> obsBool = Observable.just(true);

        Observer<Boolean> booleanObserver = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean)
                    Log.d("Observer bool", "value " + aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        obsBool.subscribe(booleanObserver);
    }

}
