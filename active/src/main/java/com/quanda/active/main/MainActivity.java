package com.quanda.active.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.quanda.active.Data.Constants;
import com.quanda.active.R;
import com.quanda.active.changelanguage.ChangeLanguageActivity;
import com.quanda.active.databinding.ActivityMainBinding;
import com.quanda.active.utils.LanguageUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageUtils.loadLocale();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMain(MainActivity.this);
    }

    public void openLanguageScreen() {
        Intent intent = new Intent(MainActivity.this, ChangeLanguageActivity.class);
        startActivityForResult(intent, Constants.RequestCode.CHANGE_LANGUAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_LANGUAGE:
                if (resultCode == RESULT_OK) {
                    updateViewByLanguage();
                }
                break;
        }
    }

    private void updateViewByLanguage() {
        mBinding.buttonChangeLanguage.setText(getString(R.string.main_button_change_language));
        mBinding.textContent.setText(getString(R.string.main_content));
        mBinding.textNews.setText(getString(R.string.main_news));
    }
}
