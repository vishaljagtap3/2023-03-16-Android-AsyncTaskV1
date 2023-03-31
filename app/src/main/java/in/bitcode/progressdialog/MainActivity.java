package in.bitcode.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;
    private SeekBar seekBar;
    private TextView txtSeekBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void setupListeners() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String localPath = Util.download("https://bitcode.in/file1.zip");

                String[] fileUrls = {
                        "https://bitcode.in/file1",
                        "https://bitcode.in/file2",
                        "https://bitcode.in/file3",
                };

                DownloadThread downloadThread =
                        new DownloadThread(MainActivity.this);
                downloadThread.setOnProgressListener(new DownloadProgressListener());
                downloadThread.execute(fileUrls);
            }
        });

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                        txtSeekBarValue.setText(value + "");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    private void initViews() {
        btnDownload = findViewById(R.id.btnDownload);
        seekBar = findViewById(R.id.seekBar);
        txtSeekBarValue = findViewById(R.id.txtSeekBarValue);
    }

    private class DownloadProgressListener implements DownloadThread.OnProgressListener {
        @Override
        public void onProgress(int progress) {
            btnDownload.setText(progress + "%");
        }
    }

}