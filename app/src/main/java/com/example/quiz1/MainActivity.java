package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etnama, etkode, etjumlah;
    private RadioGroup tipemember;
    private Button btnproses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnama = findViewById(R.id.etnama);
        etkode = findViewById(R.id.etkode);
        etjumlah = findViewById(R.id.etjumlah);
        tipemember = findViewById(R.id.tipemember);
        btnproses = findViewById(R.id.btnproses);

        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosestransaksi();
            }
        });
    }
    private void prosestransaksi(){
        String NamaCustomer = etnama.getText().toString();
        String KodeBarang = etkode.getText().toString();
        int Jumlah = Integer.parseInt(etjumlah.getText().toString());

        long harga = getHarga(KodeBarang);
        if (harga == -1) {
            Toast.makeText(MainActivity.this, "Kode Tidak Valid", Toast.LENGTH_SHORT).show();
            return;
        }

        long subtotal = harga * Jumlah;
        long diskonharga = DiskonHarga(subtotal);
        long diskonmember = DiskonMember(subtotal);

        long total = subtotal - diskonharga - diskonmember;

        Intent intent = new Intent(MainActivity.this, HasilProses.class);
        intent.putExtra("Nama Customer", NamaCustomer);
        intent.putExtra("Kode Barang", KodeBarang);
        intent.putExtra("Nama Barang", getNamaBarang(KodeBarang));
        intent.putExtra("Harga", harga);
        intent.putExtra("Jumlah", Jumlah);
        intent.putExtra("Sub Total", subtotal);
        intent.putExtra("Diskon Harga", diskonharga);
        intent.putExtra("Diskon Member", diskonmember);
        intent.putExtra("Total", total);
        startActivity(intent);

    }
    private long getHarga(String KodeBarang){
        switch (KodeBarang){
            case "IPX" :
                return 5725300;
            case "LV3" :
                return 6666666;
            case "MP3" :
                return 28999999;
            default:
                return -1;
        }
    }
    private String getNamaBarang(String KodeBarang){
        switch (KodeBarang) {
            case "IPX" :
                return "Iphone X";
            case "LV3" :
                return "Lenovo V14 Gen 3";
            case "MP3" :
                return "Macbook Pro M3";
            default:
                return "";
        }
    }
    private long DiskonHarga(long subtotal){
        if (subtotal > 10000000){
            return 100000;
        }
        return 0;
    }
    private long DiskonMember(long subtotal){
        RadioButton radioButton = findViewById(tipemember.getCheckedRadioButtonId());
        String tipemember = radioButton.getText().toString();
        switch (tipemember){
            case "Biasa" :
                return (long) (subtotal * 0.02);
            case "Silver" :
                return (long) (subtotal * 0.05);
            case "Gold" :
                return (long) (subtotal * 0.10);
            default:
                return 0;
        }
    }

}






