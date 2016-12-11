package com.roka.mvptest.impl;

import android.app.Activity;
import android.widget.Toast;

import com.roka.mvptest.interactor.GetInteractor;
import com.roka.mvptest.model.GetModel;
import com.roka.mvptest.presenter.DataNonePresent;
import com.roka.mvptest.presenter.PerentPresent;

/**
 * Created by roka on 2016. 12. 11..
 */

public class DataNoneImpl implements DataNonePresent, GetInteractor.Interact{

    private Activity mActivity;
    private PerentPresent.View mView;
    private GetInteractor mGetInteractor;


    public DataNoneImpl(Activity activity, PerentPresent.View view) {
        this.mActivity = activity;
        this.mView = view;
        this.mGetInteractor = new GetModel(this);
    }


    @Override
    public void successCallBackGetData(String data) {
        // TODO : (Model -> )받은 데이터를 가공한 후 보여준다.
        mView.setData(data, "noneData");
    }

    @Override
    public void failCallBackGetData(String data) {
        // TODO : (Model -> ) 실패시에 토스트 메세
        Toast.makeText(mActivity, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getNoneParserData(String httpAddress) {
        // TODO : (View ->) 뷰에서 호출된 메서드
        String returnUrl = httpAddress;
        if (!httpAddress.contains("http://")) {
            returnUrl = new StringBuilder("http://").append(httpAddress).toString();
        }

        mGetInteractor.getData(returnUrl, "");
    }
}
