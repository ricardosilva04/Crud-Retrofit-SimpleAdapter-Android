package br.com.retrofitusuariocrud.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    static String LOG="LOG: ";

    EditText txtId;
    EditText txtNome;
    EditText txtUsername;

    UsuarioResource apiUserResource;
    ListView listViewUser;
    List<User> listUser;
    List<HashMap<String, String>> colecao = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listarUsuarios();

    }

    public void listarUsuarios(){
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

    public void addUsuario(View v){

        txtId = findViewById(R.id.txtId);
        txtNome = findViewById(R.id.txtNome);
        txtUsername = findViewById(R.id.txtUsername);
        Integer id = Integer.valueOf(txtId.getText().toString());
        String nome = txtNome.getText().toString();
        String username = txtUsername.getText().toString();


        final User user = new User().builder().id(id).name(nome).username(username).build();

        apiUserResource = UsuarioApi.getUsuario().create(UsuarioResource.class);

        Call<User> post = apiUserResource.post(user);

        post.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                String dados = String.format("O %s foi inserido com sucesso.", user.getName());

                Toast.makeText(getApplicationContext(),dados, Toast.LENGTH_SHORT).show();
                Log.i(LOG,u.toString());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(LOG,t.getMessage());
            }
        });

        listarUsuarios();


    }

    public void editaUsuario(View v) {

        txtId = findViewById(R.id.txtId);
        txtNome = findViewById(R.id.txtNome);
        txtUsername = findViewById(R.id.txtUsername);
        Integer id = Integer.valueOf(txtId.getText().toString());
        String nome = txtNome.getText().toString();
        String username = txtUsername.getText().toString();

        final User user = new User().builder().id(id).name(nome).username(username).build();

        apiUserResource = UsuarioApi.getUsuario().create(UsuarioResource.class);

        Call<User> put = apiUserResource.post(user);

        put.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                String dados = String.format("O %s foi atualizado com sucesso.", user.getName());

                Toast.makeText(getApplicationContext(),dados, Toast.LENGTH_SHORT).show();
                Log.i(LOG,u.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(LOG,t.getMessage());
            }
        });
        listarUsuarios();
    }

    public void excluirUsuario(View v){

        txtId = findViewById(R.id.txtId);
        txtNome = findViewById(R.id.txtNome);
        txtUsername = findViewById(R.id.txtUsername);
        Integer id = Integer.valueOf(txtId.getText().toString());
        String nome = txtNome.getText().toString();
        String username = txtUsername.getText().toString();

        final User user = new User().builder().id(id).name(nome).username(username).build();

        apiUserResource = UsuarioApi.getUsuario().create(UsuarioResource.class);

        Call<User> delete = apiUserResource.post(user);

        delete.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                String dados = String.format("O %s foi deletado com sucesso.", user.getName());

                Toast.makeText(getApplicationContext(),dados, Toast.LENGTH_SHORT).show();
                Log.i(LOG,u.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(LOG,t.getMessage());
            }
        });
        listarUsuarios();
    }

}