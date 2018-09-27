package travel.uittrser.go9.go9.View.TrangChu;


import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

import travel.uittrser.go9.go9.R;

public class PrizeFragment extends Fragment {

    View m_View;
    Spinner dropdown;
    TextView restTime;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        m_View = inflater.inflate(R.layout.prize_fragment_layout,container,false);
        InitializeId();
        initializeDropDown(dropdown);
//        initializeCountdownTimer(restTime);
        int h = 50;
        int m = 0;
        int s = 0;
        int abc = 180000;
        CountDownTimer countDownTimer = new CountDownTimer(180000000, 1000) {
            @Override
            public void onTick(long l) {
                l = l/1000;
                restTime.setText("Còn " + l/3600 + ": " + l%3600/60 + ": " + l%3600%60 + " giờ");
            }

            @Override
            public void onFinish() {
                restTime.setText("Đã hết hạn");
            }
        };
        countDownTimer.start();
        /*TaoHanhTrinh thuattoan = new TaoHanhTrinh();
        thuattoan.getTxtFileTien();
        thuattoan.Start();*/
        Test_ThuatToan thuatToan = new Test_ThuatToan();
        thuatToan.Start();

        return m_View;
    }

public class TaoHanhTrinh {

    int maxTien;
    int soDinh;
    int sl = 0;
    int diaDiemBanDauID;
    String[] diaDiem;
    int[] tienDiaDiem;
    Stack<Integer> abc = new Stack<Integer>();
    Stack<Integer> abcde = new Stack<>();
    boolean[] diaDiemMarking;
    boolean[] abcd;
    int dcm = 0;
    String clmm = new String();
    int clmm2 = 0;

    public TaoHanhTrinh() {
        soDinh = 10;
        diaDiem = new String[soDinh];
        tienDiaDiem = new int[soDinh];
        diaDiemMarking = new boolean[soDinh];
        abcd = new boolean[(int) Math.pow(2, soDinh + 1)];

    }

    void Start() {
        for (int i = 0; i < 9; i++)
            diaDiemMarking[i] = false;
        int viTriBanDau = soDinh - 1;
        abc.push(viTriBanDau);
        diaDiemMarking[viTriBanDau] = true;
        maxTien = 300000;
        Test(0, 0);
        Log.d("hello", clmm2 + "");
        Log.d("hello", clmm);
    }

    void Test(int tien, int x) {
        if (tien > maxTien || abcd[x]) {
            diaDiemMarking[abc.pop()] = false;
            return;
        }
        abcd[x] = true;
        printDuongDi(tien);
        for (int i = 0; i < 8; i++)
            if (diaDiemMarking[i] == false) {
                abc.push(i);
                diaDiemMarking[i] = true;
                Test(tien + tienDiaDiem[i], x += Math.pow(2, i));
            }
        diaDiemMarking[abc.pop()] = false;
    }

    void printDuongDi(int tienn) {
        String a = new String();
        if (abc.size() <= dcm)
            return;
        clmm = "";
        clmm2 = tienn;
        for (int i : abc) {
            if (i != 9)
                clmm += diaDiem[i] + ": " + tienDiaDiem[i] + ", ";
        }
    }
    public void getTxtFileTien() {
        String text = "";
        try {
            InputStream is = getContext().getAssets().open("giave.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            int size = is.available();
            byte[] buffer = new byte[size];
            String line = "";
            int i=0;
            line = reader.readLine();
            soDinh = Integer.parseInt(line);
            String temp[] = new String[100];
            while ((line = reader.readLine()) != null) {
                temp = line.split(" ");
                diaDiem[i] = temp[0];
                //Log.d("Hello2", diaDiem[i]);
                tienDiaDiem[i] = Integer.parseInt(temp[1]);
                //Log.d("Hello2", String.valueOf(tienDiaDiem[i]));
                i++;
            }
            //is.read(buffer);
            is.close();
            //text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("Hello2", text);
    }
    public void getTxtFileKhoangCach() {
        /*String text = "";
        try {
            InputStream is = getContext().getAssets().open("giave.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            int size = is.available();
            byte[] buffer = new byte[size];
            String line = "";
            int i=0;
            line = reader.readLine();
            soDinh = Integer.parseInt(line);
            String temp[] = new String[100];
            while ((line = reader.readLine()) != null) {
                temp = line.split(" ");
                diaDiem[i] = temp[0];
                //Log.d("Hello2", diaDiem[i]);
                tienDiaDiem[i] = Integer.parseInt(temp[1]);
                //Log.d("Hello2", String.valueOf(tienDiaDiem[i]));
                i++;
            }
            //is.read(buffer);
            is.close();
            //text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("Hello2", text);*/
    }
}
    public class Test_ThuatToan {
        int soDinh = 0;
        boolean[] diaDiemMarking;
        float[][] trongSo;
        Stack<Integer> duongDi = new Stack<Integer>();
        int[] diaDiemExactly;
        float doDaiExactly = Float.MAX_VALUE;
        int dinhDau = 0;
        public void Start() {
            Input("input1.txt");
            diaDiemMarking[dinhDau] = true;
            duongDi.push(dinhDau);
            Test(dinhDau, 0, 1);
            XuatRaManHinh();
        }
        void Input(String tenfile) {
            String text = "";
            try {
                InputStream is = getContext().getAssets().open("input1.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                int size = is.available();
                byte[] buffer = new byte[size];
                String line = "";
                int i = 0;
                int j = 0;
                int k = 0;
                line = reader.readLine();
                soDinh = Integer.parseInt(line);
                line = reader.readLine();
                dinhDau = Integer.parseInt(line);
                String temp[] = new String[100];

                while ((line = reader.readLine()) != null) {
                    temp = line.split(" ");
                    trongSo[i][j] = Float.parseFloat(temp[k]);
                    j++;
                    if (j >= soDinh) {
                        i++;
                        j = 0;
                        k = 0;
                    }
                }
                //is.read(buffer);
                is.close();
                //text = new String(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        void Test(int viTri, float doDai, int sl) {
            if (sl == soDinh)
                if (doDai < doDaiExactly)
                    LuuDuongDi(doDai);

            for (int i = 0; i < soDinh; i++)
                if (!diaDiemMarking[i]) {
                    diaDiemMarking[i] = true;
                    duongDi.push(i);
                    Test(i, doDai + trongSo[viTri][i], sl + 1);
                    duongDi.pop();
                    diaDiemMarking[i] = false;
                }
        }

        void LuuDuongDi(float doDai) {
            int j = 0;
            doDaiExactly = doDai;
            for (int i: duongDi) {
                diaDiemExactly[j++] = i;
            }
        }
        void XuatRaManHinh() {
            Log.d("Hello3",doDaiExactly + "");
            for (int i: diaDiemExactly)
                Log.d("Hello3",i + " ");
        }

    }


    void InitializeId() {
        dropdown = (Spinner) m_View.findViewById(R.id.sortDropDown);
        restTime = (TextView) m_View.findViewById((R.id.restTime));
    }

    void initializeDropDown(Spinner dropdown) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sort));
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
    }

    void initializeCountdownTimer(final TextView textView) {
        int h = 50;
        int m = 0;
        int s = 0;
        int abc = 180000;
        CountDownTimer countDownTimer = new CountDownTimer(180000000, 1000) {
            @Override
            public void onTick(long l) {
                textView.setText("Còn " + l/3600 + ": " + l/60 + ": " + l + " giờ");
            }

            @Override
            public void onFinish() {
                textView.setText("Đã hết hạn");
            }
        };
    }
}

