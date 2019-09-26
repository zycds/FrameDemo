package com.zhangyc.framedemo.serverapi;


import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaHelper {

    public static final String TAG = RxJavaHelper.class.getSimpleName();

    public static <T> ObservableTransformer<BaseServerResult<T>, T> handlerServerResult() {
        return new ObservableTransformer<BaseServerResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseServerResult<T>> upstream) {

                return handlerRequestIOThread(handlerServerResult(upstream));
            }
        };
    }

    private static <T> Observable<T> handlerServerResult(Observable<BaseServerResult<T>> baseServerResult) {
        return baseServerResult.flatMap(new Function<BaseServerResult<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(BaseServerResult<T> baseServerResult) {
                int errorCode = baseServerResult.getErrorCode();
                if (errorCode == 0) {
                    Log.i(TAG, "apply: " + baseServerResult.getData());
                    return createObservable(baseServerResult.getData());
                } else {
                    return Observable.error(new ApiException(baseServerResult.getErrorCode(), baseServerResult.getErrorMsg()));
                }
            }
        });
    }


    private static <T> ObservableSource<T> handlerRequestIOThread(Observable<T> tObservable) {
        return tObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                emitter.onNext(t);
                emitter.onComplete();
            }
        });
    }

}
