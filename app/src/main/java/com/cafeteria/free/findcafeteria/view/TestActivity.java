package com.cafeteria.free.findcafeteria.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import com.cafeteria.free.findcafeteria.R;
import com.cafeteria.free.findcafeteria.databinding.ActivityTestBinding;
import com.cafeteria.free.findcafeteria.model.ImageProvider;
import com.cafeteria.free.findcafeteria.util.Logger;
import com.cafeteria.free.findcafeteria.viewmodel.CafeteriaViewModel;


public class TestActivity extends AppCompatActivity {

    private CafeteriaViewModel viewModel;
    ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FIXME: 2019-01-01 데이터바인딩 추가

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setActivity(this);

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                Logger.d("text=" + arg0.toString());
                viewModel.onKeywordChanged(arg0.toString());
            }

        });


        viewModel = ViewModelProviders.of(this).get(CafeteriaViewModel.class);

        viewModel.getCafeteriaInfo().observe(this, data -> {
            binding.textView.setText(data);
        });

        ImageProvider imageProvider = new ImageProvider();
        imageProvider.get("고양이");
    }

}
