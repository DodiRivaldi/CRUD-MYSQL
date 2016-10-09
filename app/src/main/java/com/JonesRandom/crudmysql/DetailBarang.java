package com.JonesRandom.crudmysql;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.JonesRandom.crudmysql.API.ApiService;
import com.JonesRandom.crudmysql.Model.ModelData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailBarang extends AppCompatActivity {

    EditText et_nama;
    EditText et_jenis;
    EditText et_keterangan;

    Button btn_simpan;
    Button btn_hapus;

    String ID_BARANG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_barang);

        ID_BARANG = getIntent().getStringExtra(ModelData.id_barang);

        et_nama = (EditText) findViewById(R.id.et_nama);
        et_jenis = (EditText) findViewById(R.id.et_jenis);
        et_keterangan = (EditText) findViewById(R.id.et_keterangan);

        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_hapus = (Button) findViewById(R.id.btn_hapus);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getNama = et_nama.getText().toString();
                String getJenis = et_jenis.getText().toString();
                String getKeterangan = et_keterangan.getText().toString();

                if (getNama.equals("") || getJenis.equals("") || getKeterangan.equals("")) {
                    Toast.makeText(DetailBarang.this, "Pastikan Semua Field Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    editData(getNama, getJenis, getKeterangan);
                }
            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = et_nama.getText().toString();
                final AlertDialog.Builder builder = new AlertDialog.Builder(DetailBarang.this);
                builder.setCancelable(false);
                builder.setTitle("Hapus Data");
                builder.setMessage("Apakah Kamu Yakin Ingin Menghapus Semua " + nama + " ?");
                builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });
                builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusData();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        bindData();
    }

    public void bindData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelData>> call = service.getSingleData(ID_BARANG);
        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().size(); i++) {

                        et_nama.setText(response.body().get(i).getNama());
                        et_keterangan.setText(response.body().get(i).getKeterangan());
                        et_jenis.setText(response.body().get(i).getJenis());

                    }

                }

            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {

            }
        });
    }

    public void editData(String Nama, String Jenis, String Keterangan) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.editData(ID_BARANG, Nama, Jenis, Keterangan);
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

                Toast.makeText(DetailBarang.this, respon, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void hapusData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.hapusData(ID_BARANG);
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

                Toast.makeText(DetailBarang.this, respon, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}
