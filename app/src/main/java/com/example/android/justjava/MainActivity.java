/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;

    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity >= 100) {

            quantity = 100;
        }

        display(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;


        if (quantity <= 0) {
            quantity = 0;

        }
        display(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheakbox = (CheckBox) findViewById(R.id.whiped_cream);
        boolean haswhipedcream = whippedCreamCheakbox.isChecked();


        CheckBox choclatecreamcheakbox = (CheckBox) findViewById(R.id.choclate_cream);
        boolean haschoclatecream = choclatecreamcheakbox.isChecked();

        EditText text = (EditText) findViewById(R.id.name_id);
        String value = text.getText().toString();

        if (haswhipedcream == true) {
            price = price + quantity * 1;
        }

        if (haschoclatecream = true) {
            price = price + quantity * 2;

        }
        String me = createOrderSummary(quantity, haswhipedcream, haschoclatecream, value, price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "oder for coffe shop");
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(quantity,haswhipedcream,haschoclatecream,value,price));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        displayMessage(me);
    }


    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */

    int price = quantity * 5;

    private String createOrderSummary(int quantity, boolean haswhipedcream, boolean haschoclatecream, String value, int price) {
        String message = "name: " + value + "\n" + "quantity :" + quantity + "\n" + "add Whipped cream : " + haswhipedcream + "\n" + "has choclate cream" + haschoclatecream + "\n" + "total:" + price + "\n" + "thankyou";
        return message;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}