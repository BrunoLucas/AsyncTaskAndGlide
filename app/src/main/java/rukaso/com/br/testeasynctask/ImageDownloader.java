package rukaso.com.br.testeasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by lucas on 01/05/17.
 */

public class ImageDownloader extends AsyncTask<String, String, Bitmap> {

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
            
        }
    }
}
