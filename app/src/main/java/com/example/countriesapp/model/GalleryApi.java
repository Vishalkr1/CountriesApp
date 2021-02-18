package com.example.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface GalleryApi {
    public static final String BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=2&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";

    @GET(BASE_URL)
    Single<Response> getPhotos();
}
