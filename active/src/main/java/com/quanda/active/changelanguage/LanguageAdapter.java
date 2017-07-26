package com.quanda.active.changelanguage;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.quanda.active.data.ItemClickListener;
import com.quanda.active.R;
import com.quanda.active.databinding.ItemLanguageBinding;
import com.quanda.active.model.Language;
import com.quanda.active.utils.LanguageUtils;
import java.util.ArrayList;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageHolder> {

    private List<Language> mLanguageList = new ArrayList<>();
    private ItemClickListener<Language> mListener;
    private Language mCurrentLanguage = LanguageUtils.getCurrentLanguage();

    public LanguageAdapter(List<Language> languageList) {
        mLanguageList = languageList;
    }

    public void setListener(ItemClickListener<Language> listener) {
        mListener = listener;
    }

    public void setCurrentLanguage(Language language) {
        mCurrentLanguage = language;
        notifyDataSetChanged();
    }

    @Override
    public LanguageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLanguageBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_language, parent, false);
        return new LanguageHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(LanguageHolder holder, int position) {
        holder.mBinding.radioItemLanguage.setChecked(mCurrentLanguage.getId() == position);
        holder.bindLanguage(mLanguageList.get(position));
    }

    @Override
    public int getItemCount() {
        return mLanguageList.size();
    }

    public class LanguageHolder extends RecyclerView.ViewHolder {
        public ObservableField<String> name = new ObservableField<>();
        private ItemLanguageBinding mBinding;
        private Language mLanguage;

        LanguageHolder(ItemLanguageBinding binding, final ItemClickListener<Language> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setHolder(this);
            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClickItem(getAdapterPosition(), mLanguage);
                    }
                }
            });
        }

        void bindLanguage(Language language) {
            mLanguage = language;
            name.set(language.getName());
        }
    }
}
