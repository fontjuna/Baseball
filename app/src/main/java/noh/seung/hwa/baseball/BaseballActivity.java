package noh.seung.hwa.baseball;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import noh.seung.hwa.baseball.classes.Element;
import noh.seung.hwa.baseball.classes.ElementAdapter;
import noh.seung.hwa.baseball.classes.Guess;

public class BaseballActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private AdView mAdView;

    private TextView mLeftMia;
    private TextView mCenterMia;
    private TextView mRightMia;
    private TextView mLeftYours;
    private TextView mCenterYours;
    private TextView mRightYours;
    private ListView mListView;
    private ArrayList<Element> mElementList;
    private Guess mGuess;
    ElementAdapter mAdapter;

    AlertDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseball);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mLeftYours = (TextView) findViewById(R.id.lnumber_yours);
        mCenterYours = (TextView) findViewById(R.id.cnumber_yours);
        mRightYours = (TextView) findViewById(R.id.rnumber_yours);

        mLeftMia = (TextView) findViewById(R.id.lnumber_mia);
        mCenterMia = (TextView) findViewById(R.id.cnumber_mia);
        mRightMia = (TextView) findViewById(R.id.rnumber_mia);

        mListView = (ListView) findViewById(R.id.list_view);

        findViewById(R.id.send_button).setOnClickListener(this);
        findViewById(R.id.start_button).setOnClickListener(this);
        findViewById(R.id.button_0).setOnClickListener(this);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);

        mElementList = new ArrayList<>();
        mAdapter = new ElementAdapter(BaseballActivity.this, mElementList);

        mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("리셋");
        mBuilder.setMessage("게임을 처음 부터 시작 합니다.");
        mBuilder.setNegativeButton("취소",null);
        mBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gameStart();
            }
        });
        mBuilder.create();

        gameStart();
//        mBuilder.show();
    }

    private void listSet(ArrayList<Element> list) {
        mElementList = list;
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                mBuilder.show();
//                gameStart();
                break;
            case R.id.send_button:
                valueJudge();
                break;
            default:
                setDigits(view);
                break;
        }
    }

    private void setDigits(View view) {
        String[] digit = {"", "", ""};
        digit[0] = mLeftYours.getText().toString();
        digit[1] = mCenterYours.getText().toString();
        digit[2] = mRightYours.getText().toString();
        String keyStroke = ((Button) findViewById(view.getId())).getText().toString();
        if ((digit[0] + digit[1] + digit[2]).isEmpty()) {
            mLeftYours.setText(keyStroke);
        } else if ((digit[1] + digit[2]).isEmpty()) {
            mCenterYours.setText(keyStroke);
        } else if (digit[2].isEmpty()) {
            mRightYours.setText(keyStroke);
        } else {
            mLeftYours.setText(keyStroke);
            mCenterYours.setText("");
            mRightYours.setText("");
        }
    }

    private void valueJudge() {
        int guese[] = {0, 0, 0};

        if (getYourValues(guese)) {
            yourInitialize();

            String left = (guese[0] + " " + guese[1] + " " + guese[2]);
            String right = mGuess.getJudge(guese);

            mElementList.add(1, new Element(left, right));
            mAdapter.notifyDataSetChanged();

            if (right.equals("Success!")) {
                miaDisplay();
                Toast.makeText(this, "축하합니다. 맞았습니다!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void miaDisplay() {
        mLeftMia.setText(String.valueOf(mGuess.getCount(Guess.LEFT)));
        mCenterMia.setText(String.valueOf(mGuess.getCount(Guess.CENTER)));
        mRightMia.setText(String.valueOf(mGuess.getCount(Guess.RIGHT)));
    }

    private boolean getYourValues(int[] guese) {
        try {
            guese[0] = Integer.parseInt(mLeftYours.getText().toString());
            guese[1] = Integer.parseInt(mCenterYours.getText().toString());
            guese[2] = Integer.parseInt(mRightYours.getText().toString());
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "숫자를 확인 하세요!", Toast.LENGTH_SHORT).show();
            guese[0] = 0;
            guese[1] = 0;
            guese[2] = 0;
            return false;
        }
    }

    private void gameStart() {
        mGuess = new Guess();
        yourInitialize();
        miaInitialize();
        elementInitialize();
        listSet(mElementList);
    }

    private void elementInitialize() {
        mElementList.clear();
        mElementList.add(new Element("당신의 추측", "결과 판정"));
    }

    private void miaInitialize() {
        mLeftMia.setText("?");
        mCenterMia.setText("?");
        mRightMia.setText("?");
    }

    private void yourInitialize() {
        mLeftYours.setText("");
        mRightYours.setText("");
        mCenterYours.setText("");
    }
}
