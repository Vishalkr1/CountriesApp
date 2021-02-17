package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.CountriesService;
import com.example.countriesapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewmodel extends ViewModel {
    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private CountriesService countriesService = CountriesService.getInstance();
    public void referesh()
    {
        fetchData();
    }
    private CompositeDisposable disposable = new CompositeDisposable();

    private void fetchData(){
        disposable.add(
                countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>()
                {

                    @Override
                    public void onSuccess(@NonNull List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        loading.setValue(false);
                        countryLoadError.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loading.setValue(false);
                        countryLoadError.setValue(true);
                        e.printStackTrace();

                    }
                })
        );
        loading.setValue(true);

    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }
}
