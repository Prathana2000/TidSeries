package org.classapp.tidseries1;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;



public class comuFragment extends Fragment {
    View rootView;
    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;

    float rateValue; String temp;

    private ComuViewModel mViewModel;


    public static comuFragment newInstance() {
        return new comuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.comu_fragment, container, false);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rateCount = getView().findViewById(R.id.rateCount);
        ratingBar = getView().findViewById(R.id.ratingBar);
        review =  getView().findViewById(R.id.review);
        submit = getView().findViewById(R.id.submitBtn);
        showRating = getView().findViewById(R.id.showRating);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();

                if (rateValue<=1 && rateValue >0)
                    rateCount.setText("Bad" +rateValue + "/5");
                else if (rateValue<=2 && rateValue>1)
                    rateCount.setText("OK" +rateValue + "/5");
                else if (rateValue<=3 && rateValue>2)
                    rateCount.setText("GOOD" +rateValue + "/5");
                else if (rateValue<=4 && rateValue>3)
                    rateCount.setText("VERY GOOD" +rateValue + "/5");
                else if (rateValue<=5 && rateValue>4)
                    rateCount.setText("BEST" +rateValue + "/5");
            }
        });

        submit=(Button)rootView.findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = rateCount.getText().toString();
                showRating.setText("Your Rating: \n " +temp + "\n" +review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ComuViewModel.class);
        // TODO: Use the ViewModel


    }

}