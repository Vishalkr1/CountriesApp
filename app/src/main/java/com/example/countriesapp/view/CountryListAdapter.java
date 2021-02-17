package com.example.countriesapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countryList;

    public CountryListAdapter(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public void updateCountries(List<CountryModel> newCountries){
        countryList.clear();
        countryList.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_country, parent, false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countryList.get(position));

    }


    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.flag)
        ImageView countryFlag;

//        @BindView(R.id.countryName)
        TextView CountryName;

//        @BindView(R.id.capitalName)
        TextView CapitalName;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.flag);
            CountryName = itemView.findViewById(R.id.countryName);
            CapitalName = itemView.findViewById(R.id.capitalName);
        }

        void bind(CountryModel country)
        {
            CountryName.setText(country.getCountryName());
            CapitalName.setText(country.getCapital());
            Util.loadImage(countryFlag, country.getFlag(), Util.getProgressDrawable(countryFlag.getContext()));
        }
    }

}
