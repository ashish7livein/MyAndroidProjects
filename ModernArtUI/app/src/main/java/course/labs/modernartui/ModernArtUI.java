package course.labs.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ModernArtUI extends Activity {


    private SeekBar seekBar;
    private TextView colorView1, colorView2, colorView3, colorView5;
    private static final String TAG = "ModernArtUI";
    private ColorDrawable cd;
    private int defaultColor1, defaultColor2, defaultColor3, defaultColor5;
    private DialogFragment mDialog;
    static private final String URL = "http://www.moma.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_ui);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        colorView1 = (TextView) findViewById(R.id.colorView1);
        defaultColor1 = ((ColorDrawable) colorView1.getBackground()).getColor();
        colorView2 = (TextView) findViewById(R.id.colorView2);
        defaultColor2 = ((ColorDrawable) colorView2.getBackground()).getColor();
        colorView3 = (TextView) findViewById(R.id.colorView3);
        defaultColor3 = ((ColorDrawable) colorView3.getBackground()).getColor();
        colorView5 = (TextView) findViewById(R.id.colorView5);
        defaultColor5 = ((ColorDrawable) colorView5.getBackground()).getColor();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorView1.setBackgroundColor(defaultColor1 + progress * 200);
                colorView2.setBackgroundColor(defaultColor2 + progress * 200);
                colorView3.setBackgroundColor(defaultColor3 + progress * 200);
                colorView5.setBackgroundColor(defaultColor5 + progress * 200);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_art_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.moreInformation) {
            showDialogFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void showDialogFragment() {
        mDialog = CustomDialogFragment.newInstance();

        // Show AlertDialogFragment
        mDialog.show(getFragmentManager(), "MoMA");

    }

    public static class CustomDialogFragment extends DialogFragment {

        LayoutInflater inflater;
        View v;
        public static CustomDialogFragment newInstance() {
            return new CustomDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            inflater = getActivity().getLayoutInflater();
            v = inflater.inflate(R.layout.custom_moma_dialog, null);
            Button notNow = (Button) v.findViewById(R.id.cancel);
            Button visitMoma = (Button) v.findViewById(R.id.goAhead);

            notNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });
            visitMoma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri webpage = Uri.parse(URL);
                    Intent baseIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(baseIntent);
                    getDialog().dismiss();
                }
            });

            Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(v);
            return dialog;
        }
    }

}
