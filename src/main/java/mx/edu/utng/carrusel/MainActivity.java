package mx.edu.utng.carrusel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.carrusel.R;

public class MainActivity extends AppCompatActivity {
    String[] ropa={"ropa 1","ropa 2","ropa 3","ropa 4"};
    int[] imagenes = {R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4};

    ManejadorGaleria manejadorGaleria;
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lista
        ListView lista = (ListView) findViewById(R.id.listView1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,ropa);
        lista.setAdapter(adapter);

        //Galeria de imagenes
        manejadorGaleria= new ManejadorGaleria(getSupportFragmentManager());

        mViewpager=(ViewPager)findViewById(R.id.pager);
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[0]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[1]));
        manejadorGaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[2]));
        mViewpager.setAdapter(manejadorGaleria);
    }
    public class ManejadorGaleria extends FragmentPagerAdapter {
        List<Fragment> fragmentos;

        public ManejadorGaleria(FragmentManager fm) {
            super(fm);
            fragmentos = new ArrayList();
        }

        public void agregarFragmentos(Fragment xfragmento) {
            fragmentos.add(xfragmento);
        }


        @Override
        public Fragment getItem(int position) {

            return fragmentos.get(position);
        }

        @Override
        public int getCount() {

            return fragmentos.size();
        }
    }
    public static class FragmentosImagenes extends Fragment{
        private static final String ARG_IMAGE="imagen";
        private int imagen;

        public  static  FragmentosImagenes newInstance(int imagen){
            FragmentosImagenes fragment = new FragmentosImagenes();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE, imagen);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;

        }
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            if(getArguments()!=null){
                imagen=getArguments().getInt(ARG_IMAGE);
            }
        }
        public FragmentosImagenes(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.fragment, container, false);
            ImageView imageView =(ImageView) rootView.findViewById(R.id.imageView1);
            imageView.setImageResource(imagen);
            return rootView;
        }
    }
}
