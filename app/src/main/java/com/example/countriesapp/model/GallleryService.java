package com.example.countriesapp.model;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GallleryService {
    public static final String BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=2&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";
    private static GallleryService instance;

    private GalleryApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GalleryApi.class);
    private GallleryService(){

    }

    public static GallleryService getInstance(){
        if(instance==null)
        {
            instance = new GallleryService();
        }
        return instance;
    }
    
    public Single<Response> getPhotos(){
        return api.getPhotos();
    }
}
