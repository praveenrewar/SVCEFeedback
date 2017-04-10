package com.myapp.re_war.svcefeedback;


//import android.graphics.Bitmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by re_war on 30/10/16.
 */
public class tab1 extends Fragment {
    private String username;
    private String password;
    public static final String KEY_USERNAME="username";
    String url = "http://192.168.43.237/getfac2.php";
    String url2 = "http://192.168.43.237/submitfeed.php";
    //Overriden method onCreateView
    String[] SubjectId = new String[8];
    String[] Subject = new String[8];
    String[] FacultyName = new String[8];
    String[] TableName = new String[8];
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20;
    EditText e21,e22,e23,e24,e25,e26,e27,e28,e29,e30,e31,e32,e33,e34,e35,e36,e37,e38,e39,e40;
    float[] avg= new float[8];
    float total=0;
    Button subbtn;
    boolean valid;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.tab1, container, false);
        username = Profile.getUSN();
        password = Profile.getPass();
        //Change R.layout.tab1 in you classes
        subbtn=(Button)rootView.findViewById(R.id.button);
        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url2,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.trim().equals("success")) {
                                        Toast.makeText(tab1.this.getActivity(), "Feedback submitted succesfully", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(tab1.this.getActivity(), response, Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(tab1.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            //for authorization
                            return params;
                        }

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            //Creating parameters
                            Map<String, String> params = new Hashtable<>();
                            //put params
                            params.put("username", username);
                            params.put("password", password);
                            for (int i = 0; i < 8; i++) {
                                params.put("feed" + i, Float.toString(avg[i]));

                            }
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tab1.this.getActivity(),error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //for authorization
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters
                Map<String,String> params = new Hashtable<>();
                //put params
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        tv1= (TextView) rootView.findViewById(R.id.my_text1);
        tv2= (TextView) rootView.findViewById(R.id.my_text2);
        tv3= (TextView) rootView.findViewById(R.id.my_text3);
        tv4= (TextView) rootView.findViewById(R.id.my_text4);
        tv5= (TextView) rootView.findViewById(R.id.my_text5);
        tv6= (TextView) rootView.findViewById(R.id.my_text6);
        tv7= (TextView) rootView.findViewById(R.id.my_text7);
        tv8= (TextView) rootView.findViewById(R.id.my_text8);
        tv9= (TextView) rootView.findViewById(R.id.my_text9);
        tv10= (TextView) rootView.findViewById(R.id.my_text10);
        tv11= (TextView) rootView.findViewById(R.id.my_text11);
        tv12= (TextView) rootView.findViewById(R.id.my_text12);
        tv13= (TextView) rootView.findViewById(R.id.my_text13);
        tv14= (TextView) rootView.findViewById(R.id.my_text14);
        tv15= (TextView) rootView.findViewById(R.id.my_text15);
        tv16= (TextView) rootView.findViewById(R.id.my_text16);

         e1=(EditText) rootView.findViewById(R.id.E1);
         e2=(EditText) rootView.findViewById(R.id.E2);
         e3=(EditText) rootView.findViewById(R.id.E3);
         e4=(EditText) rootView.findViewById(R.id.E4);
         e5=(EditText) rootView.findViewById(R.id.E5);
         e6=(EditText) rootView.findViewById(R.id.E6);
         e7=(EditText) rootView.findViewById(R.id.E7);
         e8=(EditText) rootView.findViewById(R.id.E8);
        e10=(EditText) rootView.findViewById(R.id.E10);
        e9=(EditText) rootView.findViewById(R.id.E9);
         e11=(EditText) rootView.findViewById(R.id.E11);
         e12=(EditText) rootView.findViewById(R.id.E12);
         e13=(EditText) rootView.findViewById(R.id.E13);
         e14=(EditText) rootView.findViewById(R.id.E14);
        e15=(EditText) rootView.findViewById(R.id.E15);
        e16=(EditText) rootView.findViewById(R.id.E16);
        e17=(EditText) rootView.findViewById(R.id.E17);
        e18=(EditText) rootView.findViewById(R.id.E18);
        e19=(EditText) rootView.findViewById(R.id.E19);
        e20=(EditText) rootView.findViewById(R.id.E20);
        e21=(EditText) rootView.findViewById(R.id.E21);
        e22=(EditText) rootView.findViewById(R.id.E22);
        e23=(EditText) rootView.findViewById(R.id.E23);
        e24=(EditText) rootView.findViewById(R.id.E24);
        e25=(EditText) rootView.findViewById(R.id.E25);
        e26=(EditText) rootView.findViewById(R.id.E26);
        e27=(EditText) rootView.findViewById(R.id.E27);
        e28=(EditText) rootView.findViewById(R.id.E28);
        e29=(EditText) rootView.findViewById(R.id.E29);
        e30=(EditText) rootView.findViewById(R.id.E30);
        e31=(EditText) rootView.findViewById(R.id.E31);
        e32=(EditText) rootView.findViewById(R.id.E32);
        e33=(EditText) rootView.findViewById(R.id.E33);
        e34=(EditText) rootView.findViewById(R.id.E34);
        e35=(EditText) rootView.findViewById(R.id.E35);
        e36=(EditText) rootView.findViewById(R.id.E36);
        e37=(EditText) rootView.findViewById(R.id.E37);
        e38=(EditText) rootView.findViewById(R.id.E38);
        e39=(EditText) rootView.findViewById(R.id.E39);
        e40=(EditText) rootView.findViewById(R.id.E40);

        
        return rootView;
    }

    private void showJSON(String response){

        try {
            JSONObject jsonObject =new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            for(int x=0;x<result.length();x++) {
                JSONObject FeedData = result.getJSONObject(x);
                SubjectId[x]=FeedData.getString(Config.KEY_SUBID);
                Subject[x]=FeedData.getString(Config.KEY_SUB);
                FacultyName[x]=FeedData.getString(Config.KEY_FACNAME);
                TableName[x]=FeedData.getString(Config.KEY_FAC);


            }
            tv1.setText(Subject[0]);
            tv2.setText(FacultyName[0]);
            tv3.setText(Subject[1]);
            tv4.setText(FacultyName[1]);
            tv5.setText(Subject[2]);
            tv6.setText(FacultyName[2]);
            tv7.setText(Subject[3]);
            tv8.setText(FacultyName[3]);
            tv9.setText(Subject[4]);
            tv10.setText(FacultyName[0]);
            tv11.setText(Subject[5]);
            tv12.setText(FacultyName[5]);
            tv13.setText(Subject[6]);
            tv14.setText(FacultyName[6]);
            tv15.setText(Subject[7]);
            tv16.setText(FacultyName[7]);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Buffer Error", "Error converting result " + e.toString());
            Log.e("Buffer Error",response);
        }
    }
    public boolean validate(){
        valid=true;
        String s1=e1.getText().toString().trim();
        Log.e("First field : ",s1);
        total=edfunc(e1,s1,total);
        String s2=e2.getText().toString().trim();
        total=edfunc(e2,s2,total);
        String s3=e3.getText().toString().trim();
        total=edfunc(e3,s3,total);
        String s4=e4.getText().toString().trim();
        total=edfunc(e4,s4,total);

        String s5=e5.getText().toString().trim();
        total=edfunc(e5,s5,total);
        avg[0]=total/5;

        total=0;

        String s6=e6.getText().toString().trim();
        total=edfunc(e6,s6,total);
        String s7=e7.getText().toString().trim();
        total=edfunc(e7,s7,total);
        String s8=e8.getText().toString().trim();
        total=edfunc(e8,s8,total);
        String s9=e9.getText().toString().trim();
        total=edfunc(e9,s9,total);
        String s10=e10.getText().toString().trim();
        total=edfunc(e10,s10,total);
        avg[1]=total/5;

        total=0;

        String s11=e11.getText().toString().trim();
        total=edfunc(e11,s11,total);
        String s12=e12.getText().toString().trim();
        total=edfunc(e12,s12,total);
        String s13=e13.getText().toString().trim();
        total=edfunc(e13,s13,total);
        String s14=e14.getText().toString().trim();
        total=edfunc(e14,s14,total);
        String s15=e15.getText().toString().trim();
        total=edfunc(e15,s15,total);
        avg[2]=total/5;

        total=0;

        String s16=e16.getText().toString().trim();
        total=edfunc(e16,s16,total);
        String s17=e17.getText().toString().trim();
        total=edfunc(e17,s17,total);
        String s18=e18.getText().toString().trim();
        total=edfunc(e18,s18,total);
        String s19=e19.getText().toString().trim();
        total=edfunc(e19,s19,total);
        String s20=e20.getText().toString().trim();
        total=edfunc(e20,s20,total);

        avg[3]=total/5;

        total=0;

        String s21=e21.getText().toString().trim();
        total=edfunc(e21,s21,total);
        String s22=e22.getText().toString().trim();
        total=edfunc(e22,s22,total);
        String s23=e23.getText().toString().trim();
        total=edfunc(e23,s23,total);
        String s24=e24.getText().toString().trim();
        total=edfunc(e24,s24,total);
        String s25=e25.getText().toString().trim();
        total=edfunc(e25,s25,total);
        avg[4]=total/5;

        total=0;

        String s26=e26.getText().toString().trim();
        total=edfunc(e26,s26,total);
        String s27=e27.getText().toString().trim();
        total=edfunc(e27,s27,total);
        String s28=e28.getText().toString().trim();
        total=edfunc(e28,s28,total);
        String s29=e29.getText().toString().trim();
        total=edfunc(e29,s29,total);
        String s30=e30.getText().toString().trim();
        total=edfunc(e30,s30,total);
        avg[5]=total/5;

        total=0;

        String s31=e31.getText().toString().trim();
        total=edfunc(e31,s31,total);
        String s32=e32.getText().toString().trim();
        total=edfunc(e32,s32,total);
        String s33=e33.getText().toString().trim();
        total=edfunc(e33,s33,total);
        String s34=e34.getText().toString().trim();
        total=edfunc(e34,s34,total);
        String s35=e35.getText().toString().trim();
        total=edfunc(e35,s35,total);
        avg[6]=total/5;

        total=0;

        String s36=e36.getText().toString().trim();
        total=edfunc(e36,s36,total);
        String s37=e37.getText().toString().trim();
        total=edfunc(e37,s37,total);
        String s38=e38.getText().toString().trim();
        total=edfunc(e38,s38,total);
        String s39=e39.getText().toString().trim();
        total=edfunc(e39,s39,total);
        String s40=e40.getText().toString().trim();
        total=edfunc(e40,s40,total);
        avg[7]=total/5;
        Log.e("pw : ",Float.toString(avg[7]));
        return valid;
    }
    public float edfunc(EditText editText,String text,float total){
        float feed;

        if(text!=null && !text.isEmpty()){
            feed=Float.valueOf(text);//Log.e("total : ",Float.toString(feed));
            if(checkfunc(feed)){
                editText.setError("Value should be between 1 and 5");
                valid=false;
            }
            else{
                total=total+feed;

            }
        }
        else {
            editText.setError("Please fill all the fields");
            valid=false;
        }

        return total;
    }
    public boolean checkfunc(float feed){
        if(feed>5.0)
            return true;
        else
            return false;
    }
}
