package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HasilProses extends AppCompatActivity {
    private TextView namacustomerText, kodebarangText, namabarangText, hargaText, jumlahText, subtotalText, diskonhargaText, diskonmemberText, totalText;
    public Button btnshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_proses);

        namacustomerText = findViewById(R.id.namacustomer);
        kodebarangText = findViewById(R.id.kodebarang);
        namabarangText = findViewById(R.id.namabarang);
        hargaText = findViewById(R.id.harga);
        jumlahText = findViewById(R.id.jumlah);
        subtotalText = findViewById(R.id.subtotal);
        diskonhargaText = findViewById(R.id.diskonharga);
        diskonmemberText = findViewById(R.id.diskonmember);
        totalText = findViewById(R.id.total);
        btnshare = findViewById(R.id.btnshare);

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData();
            }
        });

        Intent intent = getIntent();
        String namacustomer = intent.getStringExtra("Nama Customer");
        String kodebarang = intent.getStringExtra("Kode Barang");
        String namabarang = intent.getStringExtra("Nama Barang");
        long harga = intent.getLongExtra("Harga", 0);
        int jumlah = intent.getIntExtra("Jumlah", 0);
        long subtotal = intent.getLongExtra("Sub Total", 0);
        long diskonharga = intent.getLongExtra("Diskon Harga", 0);
        long diskonmember = intent.getLongExtra("Diskon Member", 0);
        long total = intent.getLongExtra("Total", 0);

        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedHarga = "Rp" + formatter.format(harga);
        String formattedsubtotal = "Rp" + formatter.format(subtotal);
        String formatteddiskonharga = "Rp" + formatter.format(diskonharga);
        String formatteddiskonmember = "Rp" + formatter.format(diskonmember);
        String formattedtotal = "Rp" + formatter.format(total);

        namacustomerText.setText("Nama Customer : " + namacustomer);
        kodebarangText.setText("Kode Barang : " + kodebarang);
        namabarangText.setText("Nama Barang : " + namabarang);
        hargaText.setText("Harga : " + formattedHarga);
        jumlahText.setText("Jumlah : " + jumlah);
        subtotalText.setText("Sub Total : " + formattedsubtotal);
        diskonhargaText.setText("Diskon Harga : " + formatteddiskonharga);
        diskonmemberText.setText("Diskon Member : " + formatteddiskonmember);
        totalText.setText("Total : " + formattedtotal);

    }

    private void shareData() {
        String dataToShare = "Detail Transaksi\n" +
                namacustomerText.getText().toString() + "\n" +
                kodebarangText.getText().toString() + "\n" +
                namabarangText.getText().toString() + "\n" +
                hargaText.getText().toString() + "\n" +
                jumlahText.getText().toString() +"\n" +
                subtotalText.getText().toString() + "\n" +
                diskonhargaText.getText().toString() + "\n" +
                diskonmemberText.getText().toString() + "\n" +
                totalText.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, dataToShare);
        startActivity(Intent.createChooser(shareIntent, "Bagikan ke"));
    }

}