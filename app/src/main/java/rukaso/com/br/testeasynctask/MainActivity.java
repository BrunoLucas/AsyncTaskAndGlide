package rukaso.com.br.testeasynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button buttonAsynTask;
    Button buttonGlide;
    ImageView image;
//    String urlImage  = "http://www.hayvanlar.org/files/image/aslana.jpg";
    String urlImage  = "https://upload.wikimedia.org/wikipedia/commons/3/3f/Fronalpstock_big.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAsynTask = (Button) findViewById(R.id.buttonAsynTask);
        buttonGlide = (Button) findViewById(R.id.buttonGlide);
        image = (ImageView) findViewById(R.id.imageView);

        buttonAsynTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageDownloader imageDownloader = new ImageDownloader();
                imageDownloader.execute(urlImage);

            }
        });


        buttonGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(MainActivity.this).load(urlImage).into(image);

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioBotaoMenor:
                if (checked) {
                    urlImage  = "http://www.hayvanlar.org/files/image/aslana.jpg";
                    break;
                }
            case R.id.radioBotaoMaior:
                if (checked) {
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/3/3f/Fronalpstock_big.jpg";
                    break;
                }
        }
    }

    public class ImageDownloader extends AsyncTask<String, String, Bitmap> {

//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
//                .setMessage("Carregando imagem...").show();

        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            pdLoading.setMessage("Carregando...");
            pdLoading.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            try{
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
                return bitmap;
            }catch (Exception e){
                Log.e("log", "Erro: " + e);
            }
            return null;
        }



        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if(bitmap != null){
//                alertDialog.hide();
                pdLoading.hide();
                image.setImageBitmap(bitmap);
            }
        }
    }


}
