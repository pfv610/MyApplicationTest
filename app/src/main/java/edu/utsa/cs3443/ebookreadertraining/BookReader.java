package edu.utsa.cs3443.ebookreadertraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.utsa.cs3443.ebookreadertraining.model.EBook;

public class BookReader extends AppCompatActivity {

    private EBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reader);
        String bookName = getIntent().getStringExtra(MainActivity.getKey());
        book = MainActivity.getClickedBook(bookName);
        displayCover();
        displayTitle();
        displayBook();
    }
    private void displayTitle() {
        TextView tvTitle = (TextView) findViewById(R.id.book_title);
        tvTitle.setText(book.getTitle().toUpperCase());
    }
    private void displayBook(){
        TextView tvText = (TextView) findViewById(R.id.book_text);
        tvText.setText(book.readBook(this));
    }
    private void displayCover(){
        ImageView ivBookCover = (ImageView) findViewById(R.id.book_cover);
        int imageResource = getResources().getIdentifier(book.getBookCover(), "drawable", getPackageName());
        ivBookCover.setImageResource(imageResource);
    }

}