package com.orca.kam.androidrxmvvmarchitect.ui.view;

import android.content.Context;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orca.kam.androidrxmvvmarchitect.R;
import com.orca.kam.androidrxmvvmarchitect.model.LANGUAGE;

import java.util.List;

import rx.Observable;
import rx.subscriptions.Subscriptions;

/**
 * Created by Kang Young Won on 2016-05-20.
 */
public class RxDialog {
    public static Observable<String> showLanguageDialog(Context context) {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            List<String> languageList = LANGUAGE.languageNameList();
            MaterialDialog dialog = new MaterialDialog.Builder(context)
                    .items(languageList)
                    .itemsCallback((dialog1, itemView, which, text) -> subscriber.onNext(languageList.get(which)))
                    .canceledOnTouchOutside(true)
                    .dismissListener(dialog1 -> subscriber.unsubscribe())
                    .build();
            subscriber.add(Subscriptions.create(dialog::dismiss));
            dialog.show();
        });
    }


    public static Observable<String> showLocationDialog(Context context) {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            MaterialDialog dialog = new MaterialDialog.Builder(context)
                    .title(R.string.dialog_location_title)
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input(R.string.dialog_location_hint, R.string.dialog_location_pre_fill, (dialog1, input) -> subscriber.onNext(input.toString()))
                    .canceledOnTouchOutside(true)
                    .dismissListener(dialog1 -> subscriber.unsubscribe())
                    .build();
            subscriber.add(Subscriptions.create(dialog::dismiss));
            dialog.show();
        });
    }
}