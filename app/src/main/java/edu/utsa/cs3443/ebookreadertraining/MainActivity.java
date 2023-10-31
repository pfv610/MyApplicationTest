package edu.utsa.cs3443.ebookreadertraining;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.utsa.cs3443.ebookreadertraining.model.EBook;
import edu.utsa.cs3443.ebookreadertraining.model.Library;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String libraryName;
    private static Library library;
    private TextView libTextView;

    //Define a static final key that can be used for passing arguments to other views
    private static final String bookName = "bookName";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up the library name
        this.libraryName = "Library1";
        createLibrary();
        updateTextView();
        setupButtons();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String bookNameClicked = clickedButton.getText().toString().toLowerCase();
        //find the EBook object associated with the bookName?
        //Create a new Intent and pass the EBook objcet to the new view, which is BookView
        Intent intent = new Intent(MainActivity.this, BookReader.class);
        //When passing values to new Activities, you can only pass String objects
        //we need to pass EBook object, but we cannot!
        intent.putExtra(bookName, bookNameClicked);
        startActivity(intent);
    }

    private void createLibrary(){
        library = new Library(this.libraryName);
        library.loadBooks(this);
    }

    private void updateTextView(){
        //wire the textView to this controller class
        this.libTextView = findViewById(R.id.library_name);
        libTextView.setText(this.libraryName);
    }

    private void setupButtons(){
        int[] buttonIDs = {R.id.book1, R.id.book2};
        for(int i = 0; i < buttonIDs.length; i++){
            Button button = findViewById(buttonIDs[i]);
            button.setText(this.library.getBook(i).getTitle());
            button.setOnClickListener(this);
        }
    }
    public static String getKey()
    {
        return bookName;
    }

    public static EBook getClickedBook(String booktitle){
        return library.getBook(booktitle);
    }
}