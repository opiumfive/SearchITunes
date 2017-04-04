package com.opiumfive.searchitunes.ui;


import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.opiumfive.searchitunes.R;


public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog mProgressDialog;

    protected void showMessage(int resId) {
        View v = getCurrentFocus();
        if (v != null && resId != 0) {
            Snackbar.make(v, resId, Snackbar.LENGTH_SHORT).show();
        }
    }

    protected void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.please_wait));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    protected void removeProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
