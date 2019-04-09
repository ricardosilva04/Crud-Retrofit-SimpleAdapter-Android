package br.com.retrofitusuariocrud.resource;

import java.util.List;

import br.com.retrofitusuariocrud.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsuarioResource {

    @GET("users")
    Call<List<User>> get();

    @POST("users")
    Call<User> post(@Body User user);

    @PUT("users")
    Call<User> put(@Body User user);

    @DELETE("users")
    Call<Void> delete(@Body User user);

}
