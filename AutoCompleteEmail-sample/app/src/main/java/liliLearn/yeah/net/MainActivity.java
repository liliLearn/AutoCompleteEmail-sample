package liliLearn.yeah.net;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import liliLearn.yeah.net.utils.EmailAutoTokenizer;
import liliLearn.yeah.net.widgets.CleanableMultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    private String[] email_sufixs = new String[]{"@qq.com", "@163.com", "@126.com", "@gmail.com", "@sina.com", "@hotmail.com",
            "@yahoo.cn", "@sohu.com", "@foxmail.com", "@139.com", "@yeah.net", "@vip.qq.com", "@vip.sina.com"};

    private AutoCompleteTextView mAutoCompleteTextView;
    private MultiAutoCompleteTextView mMultiAutoCompleteTextView;
    private CleanableMultiAutoCompleteTextView mCleanableMultiAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mMultiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        mCleanableMultiAutoCompleteTextView = (CleanableMultiAutoCompleteTextView) findViewById(R.id.cleanableMultiAutoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, email_sufixs);

        //这个是从String.xml下读取
//        ArrayAdapter<String> adapter = new ArrayAdapter(this,
//                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.email_sufixs));

        mAutoCompleteTextView.setAdapter(adapter);

        mMultiAutoCompleteTextView.setAdapter(adapter);
        mMultiAutoCompleteTextView.setTokenizer(new EmailAutoTokenizer());

        mCleanableMultiAutoCompleteTextView.setAdapter(adapter);
        mCleanableMultiAutoCompleteTextView.setTokenizer(new EmailAutoTokenizer());
    }
}
