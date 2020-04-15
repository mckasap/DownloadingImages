package org.kasapbasi.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class MyImageDownloader extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivImage.setImageBitmap(bitmap);
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url =new URL(strings[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.connect(); //Satır satır okunmayacak o yüzden Input Stream REader a ihtiyaç yok.

                InputStream is= con.getInputStream(); // bir büyün halinde okunur.
                Bitmap myBitmap= BitmapFactory.decodeStream(is);  //BitmapFactory inputstream okur ve Bitmapa çevirir.
                return  myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    ImageView ivImage;
    public void DowloadImage(View v){
//http://www.agaclar.net/forum/attachments/sukulent/7290d1171654782-pasa-008.jpg
// Yüklenecek Resim

        MyImageDownloader id= new MyImageDownloader();

      id.execute("http://www.agaclar.net/forum/attachments/sukulent/7290d1171654782-pasa-008.jpg");


        Log.d("Button","Button Tıklandı");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ivImage =(ImageView) findViewById(R.id.imageView);

    }
}
