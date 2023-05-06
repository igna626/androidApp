package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.google.firebase.firestore.FirebaseFirestore;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class DietsActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button button;
    private FirebaseFirestore firestore;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets);

        // Find references to EditText components and button
        editText1 = findViewById(R.id.input_nombre_comida);
        editText2 = findViewById(R.id.input_kcal);
        button = findViewById(R.id.add_food);

        // Initialize Firestore database instance
        firestore = FirebaseFirestore.getInstance();

        // Set a click listener on the button
        button.setOnClickListener(view -> {
            // Retrieve text from EditText components
            String input1 = editText1.getText().toString();
            String input2 = editText2.getText().toString();

            // Store data in Firestore and perform other actions
            storeDataInFirestore(input1, input2);
        });
    }

    private void storeDataInFirestore(String input1, String input2) {
        // Create a new document reference
        String documentId = firestore.collection("comidas").document().getId();
        Map<String, Object> data = new HashMap<>();
        // Convert current timestamp to date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        data.put("Time", formattedDate);
        data.put("Food", input1);
        data.put("Kcals", input2);

        // Use the document reference to set the data in Firestore
        firestore.collection("comidas").document(documentId)
                .set(data)
                .addOnSuccessListener(aVoid -> {
                    // Data successfully stored in Firestore
                    // Perform any additional actions or show a success message
                })
                .addOnFailureListener(e -> {
                    // Error occurred while storing data in Firestore
                    // Handle the error appropriately (e.g., show an error message)
                });
    }
}
