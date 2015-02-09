package com.andrewduchi.phonemetronome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ShowBeat extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.andrewduchi.phonemetronome.MESSAGE";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_beat);
        mediaPlayer = MediaPlayer.create(this,R.raw.beat1);
        mediaPlayer.setLooping(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_beat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeBpm(View view){
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        if(isValidBPM(message)) {
            Intent intent = new Intent(this, DisplayBPMActivity.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        } else {
            editText.setText("",TextView.BufferType.EDITABLE);
            alertBadBPM();
        }
    }

    private boolean isValidBPM(String bpmString){
        try{
            int i = Integer.parseInt(bpmString);
            if(i<=0 || i>400){
                return false;
            }
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public void alertBadBPM(){
        Context context = getApplicationContext();
        CharSequence text = "Enter BPM from 1 to 400";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    public void inplaceBpmUpdate(View view){
        EditText editText = (EditText) findViewById(R.id.bpm_message);
        String message = editText.getText().toString();
        TextView bpmText = (TextView) findViewById(R.id.bpmDisplay);
        BeatBall beatBall = (BeatBall) findViewById(R.id.bpm_ball);
        beatBall.setBPM(Integer.parseInt(message));
        bpmText.setText(message);
        mediaPlayer = MediaPlayer.create(this,R.raw.beat1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

}
