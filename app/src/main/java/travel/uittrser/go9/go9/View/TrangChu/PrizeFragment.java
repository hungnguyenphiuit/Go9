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

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
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
        Start();
      return m_View;
    }


    int maxTien;
    int sl = 0;
    int diaDiemBanDauID;
    String[] diaDiem = new String[9];
    int[] tienDiaDiem = new int[9];
    Stack<Integer> abc = new Stack<Integer>();
    boolean[] diaDiemMarking = new boolean[9];
    boolean[] abcd = new boolean[500];
    void Start() {
        for (int i = 0; i < 9; i++)
            diaDiemMarking[i] = false;
        diaDiem[0] = "Suoi tien";
        tienDiaDiem[0] = 120000;
        diaDiem[1] = "KhuDL_BCR";
        tienDiaDiem[1] = 100000;
        diaDiem[2] = "Suoi_mo";
        tienDiaDiem[2] = 70000;
        diaDiem[3] = "Bao_tang_ao_dai";
        tienDiaDiem[3] = 30000;
        diaDiem[4] = "Chua_buu_long";
        tienDiaDiem[4] = 0;
        diaDiem[5] = "Long_Island";
        tienDiaDiem[5] = 100000;
        diaDiem[6] = "Dam_sen_Tam_da";
        tienDiaDiem[6] = 30000;
        diaDiem[7] = "Chua_Phuoc_Tuong";
        tienDiaDiem[7] = 0;
        diaDiem[8] = "Den_tuong_bien_noc";
        tienDiaDiem[8] = 0;
        int viTriBanDau = 0;
        abc.push(viTriBanDau);
        diaDiemMarking[viTriBanDau]= true;
        maxTien = 300000;
        Test(0, 0);
        Log.d("HELLO", sl + " ");
    }

    void Test(int tien, int x) {
        if (tien > maxTien || abcd[x]) {
            diaDiemMarking[abc.pop()] = false;
            return;
        }
        Log.d("Hello", "Tien = " + tien);
        abcd[x] = true;
        printDuongDi();
        for (int i = 0; i < 8; i++)
            if (diaDiemMarking[i] == false) {
                abc.push(i);
                diaDiemMarking[i] = true;
                Test(tien + tienDiaDiem[i], x += Math.pow(2, i));
            }
        diaDiemMarking[abc.pop()] = false;
    }

    void printDuongDi() {
        String a = new String();
        sl++;
        for (int i: abc)
            a += diaDiem[i] + ": " + tienDiaDiem[i] + ", ";
        Log.d("Hello", a);
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

