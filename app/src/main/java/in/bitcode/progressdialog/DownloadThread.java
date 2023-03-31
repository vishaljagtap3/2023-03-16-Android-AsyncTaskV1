package in.bitcode.progressdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadThread extends AsyncTask<String, Integer, Float> {
    ProgressDialog progressDialog;
    Context context;

    public interface OnProgressListener {
        void onProgress(int progress);
    }

    private OnProgressListener onProgressListener;

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    public DownloadThread(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);//***
        progressDialog.setTitle("BitCode");
        progressDialog.setMessage("Downloading...");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }

    @Override
    protected Float doInBackground(String[] input) {

        for (String url : input) {
            progressDialog.setMessage("Downloading -> " + url);
            for (int i = 0; i <= 100; i++) {

                Integer[] progress = new Integer[1];
                progress[0] = i;
                publishProgress(progress);

                progressDialog.setProgress(i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return 12.12f;
    }

    @Override
    protected void onPostExecute(Float res) {
        progressDialog.dismiss();
        super.onPostExecute(res);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (onProgressListener != null) {
            onProgressListener.onProgress(values[0]);
        }
    }
}