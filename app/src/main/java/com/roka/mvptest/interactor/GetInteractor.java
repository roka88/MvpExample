package com.roka.mvptest.interactor;

/**
 * Created by roka on 2016. 12. 11..
 */

public interface GetInteractor {
    void getData(String url, String params);

    interface Interact {
        void successCallBackGetData(String data);
        void failCallBackGetData(String data);
    }
}
