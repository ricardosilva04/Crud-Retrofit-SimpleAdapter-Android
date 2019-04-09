package br.com.retrofitusuariocrud.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuarioApi {

    private static Retrofit retrofit = null;

    private static final String URL ="http://jsonplaceholder.typicode.com/";

    private static Retrofit getUsuario(){

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

}
