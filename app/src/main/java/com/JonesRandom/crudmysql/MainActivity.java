package com.JonesRandom.crudmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.JonesRandom.crudmysql.API.ApiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText et_nama;
    EditText et_jenis;
    EditText et_keterangan;

    Button btn_tambah;
    Button btn_lihat;

    CheckBox cekBarang;

    public static final String ROOT_URL = "http://10.0.2.2/Android/API/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cekBarang = (CheckBox)findViewById(R.id.checkBarang);

        et_nama = (EditText) findViewById(R.id.et_nama);
        et_jenis = (EditText) findViewById(R.id.et_jenis);
        et_keterangan = (EditText) findViewById(R.id.et_keterangan);

        btn_tambah = (Button) findViewById(R.id.btn_tambah);
        btn_lihat = (Button) findViewById(R.id.btn_lihat);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama = et_nama.getText().toString();
                String getJenis = et_jenis.getText().toString();
                String getKeterangan = et_keterangan.getText().toString();

                if (getNama.equals("") || getJenis.equals("") || getKeterangan.equals("")) {
                    Toast.makeText(MainActivity.this, "Pastikan Semua Field Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    tambahData(getNama, getJenis, getKeterangan);
                    if (!cekBarang.isChecked()){
                        et_nama.requestFocus();
                        et_nama.setText("");
                        et_jenis.setText("");
                        et_keterangan.setText("");
                    }
                }
            }
        });

        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DaftarBarang.class));
            }
        });

    }

    public void tambahData(String nama, String jenis, String keterangan) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.tambahData(nama, jenis, keterangan);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                BufferedReader reader = null;

                String respon = "";

                try {
                    reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    respon = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, respon, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
