package avia.stockmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

/**
 * Created by GT70 on 08-Dec-15.
 */
public class RegistItems extends AppCompatActivity implements android.view.View.OnClickListener  {

    Button btnSave ;
    Button btnDelete ; // will make interface later on
    EditText editTextID ;  // itemIDInput
    EditText editTextName ; // itemNameInput
    EditText editTextComment ; // itemCommentInput

    NumberPicker numberPickerQT ; // itemQTInput

    private int _Order_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        btnSave = (Button) findViewById(R.id.saveButton);

        // Make new delete button and add it

        editTextID = (EditText) findViewById(R.id.itemIDInput);
        editTextName = (EditText) findViewById(R.id.itemNameInput);
        editTextComment = (EditText) findViewById(R.id.itemCommentInput);

        numberPickerQT = (NumberPicker) findViewById(R.id.itemQTInput);

        // SetOn Click listener to all buttons
        btnSave.setOnClickListener(this);

        _Order_ID = 0;
        Intent intent = getIntent();
        _Order_ID = intent.getIntExtra("order_Id",0);
        ItemsRepo repo = new ItemsRepo(this);
        Items item = new Items();
        item = repo.getItemByorderId(_Order_ID);


        // Make it for Comment , ID , date also

        editTextID.setText(String.valueOf(item.item_itemID));
        editTextName.setText(String.valueOf(item.item_name));
        editTextComment.setText(String.valueOf(item.item_comment));



        numberPickerQT.setValue(item.item_qt);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick (View v){

        if(v == findViewById(R.id.saveButton)){
            ItemsRepo repo = new ItemsRepo(this);
            Items item = new Items();
            item.item_itemID = editTextID.getText().toString();
            item.item_name = editTextName.getText().toString();
            item.item_comment = editTextComment.getText().toString();

            item.item_qt = numberPickerQT.getValue();
            item.item_orderID = _Order_ID ;

            if(_Order_ID == 0)
            {
                _Order_ID = repo.insert(item);

                Toast.makeText(this, "New Item Insert", Toast.LENGTH_SHORT).show();

            }
            else{
                repo.update(item);
                Toast.makeText(this, "Item Record Updated", Toast.LENGTH_SHORT).show();

            }

            // Make one for delete

            // Make one for edit
        }

    }
}
