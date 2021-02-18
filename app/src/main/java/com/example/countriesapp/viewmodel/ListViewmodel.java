package com.example.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesapp.model.GallleryService;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.model.Response;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewmodel extends ViewModel {
    public MutableLiveData<Response> photos = new MutableLiveData<Response>();
    public MutableLiveData<Boolean> LoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private GallleryService gallleryService = GallleryService.getInstance();
    public void referesh()
    {
        fetchData();
    }
    private CompositeDisposable disposable = new CompositeDisposable();

    private void fetchData(){
        disposable.add(
                gallleryService.getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response>()
                {

                    @Override
                    public void onSuccess(@NonNull Response response) {
                        photos.setValue(response);
                        loading.setValue(false);
                        LoadError.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loading.setValue(false);
                        LoadError.setValue(true);
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
