package br.com.retrofitusuariocrud.activity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import br.com.retrofitusuariocrud.R;
import br.com.retrofitusuariocrud.model.User;
import br.com.retrofitusuariocrud.resource.UsuarioResource;
import br.com.retrofitusuariocrud.service.UsuarioApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UsuarioResource apiUserResource;
    ListView listViewUser;
    List<User> listUser;
    List<HashMap<String, String>> colecao = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiUserResource = UsuarioApi.getUsuario().create(UsuarioResource.class);

        Call<List<User>> get = apiUserResource.get();

        get.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                listViewUser = findViewById(R.id.listView);

                listUser = response.body();

                for (User user : listUser) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Id", String.valueOf(user.getId()));
                    map.put("Username", user.getUsername());
                    map.put("Nome", user.getName());

                    colecao.add(map);
                }

                String[] from = {"Id", "Nome", "Username"};
                int[] to = {R.id.txtId, R.id.txtName, R.id.txtUsername};

                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        getApplicationContext(),
                        colecao,
                        R.layout.usuario_list_view,
                        from,
                        to);

                listViewUser.setAdapter(simpleAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println("Erro:" + t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }


}