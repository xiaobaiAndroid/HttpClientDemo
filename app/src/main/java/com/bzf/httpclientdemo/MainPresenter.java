package com.bzf.httpclientdemo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private CompositeDisposable disposable = new CompositeDisposable();

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void testGet(final String url){
        disposable.add(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                HttpClientUtils httpClientUtils = new HttpClientUtils();
                String content = httpClientUtils.useGet(url);
                emitter.onNext(content);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                if(view!=null){
                    view.requestSuccess(s);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(view!=null){
                    view.requestFail();
                }
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    public void testPost(final String url, final String searchName){
        disposable.add(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                HttpClientUtils httpClientUtils = new HttpClientUtils();
                String content = httpClientUtils.usePost(url, searchName);
                emitter.onNext(content);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                if(view!=null){
                    view.requestSuccess(s);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(view!=null){
                    view.requestFail();
                }
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    public void destory(){
        disposable.clear();
        view = null;
    }

}
