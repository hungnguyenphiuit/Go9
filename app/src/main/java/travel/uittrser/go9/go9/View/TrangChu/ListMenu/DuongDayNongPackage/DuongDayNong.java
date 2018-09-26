package travel.uittrser.go9.go9.View.TrangChu.ListMenu.DuongDayNongPackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import travel.uittrser.go9.go9.R;

public class DuongDayNong extends AppCompatActivity {

    ListView lvDuongDayNong;
    ArrayList<DuongDayNongListView> arrayItemDuongDayNong;
    DuongDayNongListViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout_duongdaynong);

        AnhXa();

        adapter = new DuongDayNongListViewAdapter(this,R.layout.menu_layout_duongdaynong_layout,arrayItemDuongDayNong);
        lvDuongDayNong.setAdapter(adapter);

        lvDuongDayNong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneToTel = null;
                Intent phoneNumToCall = new Intent(Intent.ACTION_DIAL);
                switch (position){
                    case 0:
                        phoneToTel = "tel:113";
                        break;
                    case 1:
                        phoneToTel = "tel:114";
                        break;
                    case 2:
                        phoneToTel = "tel:115";
                        break;
                }
                phoneNumToCall.setData(Uri.parse(phoneToTel));
                startActivity(phoneNumToCall);
            }
        });
    }

    private void AnhXa() {

        lvDuongDayNong = (ListView) findViewById(R.id.listViewDuongDayNongEmergency);
        arrayItemDuongDayNong = new ArrayList<>();
        arrayItemDuongDayNong.add(new DuongDayNongListView("Công an","113",R.drawable.menuicon_hotline_police_icon));
        arrayItemDuongDayNong.add(new DuongDayNongListView("Cứu hỏa","114",R.drawable.menuicon_hotline_fire));
        arrayItemDuongDayNong.add(new DuongDayNongListView("Cứu thương","115",R.drawable.menuicon_hotline_hospitalbuildings));

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
