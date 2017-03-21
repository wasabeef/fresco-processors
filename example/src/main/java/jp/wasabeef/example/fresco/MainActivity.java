package jp.wasabeef.example.fresco;

/**
 * Copyright (C) 2017 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.example.fresco.MainAdapter.Type;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    List<Type> dataSet = new ArrayList<>();
    dataSet.add(Type.BlurAndGrayscale);
    dataSet.add(Type.Blur);
    dataSet.add(Type.Grayscale);
    dataSet.add(Type.ColorFilter);
    dataSet.add(Type.Mask);
    dataSet.add(Type.NinePatchMask);
    dataSet.add(Type.Pixel);
    dataSet.add(Type.Vignette);
    dataSet.add(Type.Kuawahara);
    dataSet.add(Type.Brightness);
    dataSet.add(Type.Swirl);
    dataSet.add(Type.Sketch);
    dataSet.add(Type.Invert);
    dataSet.add(Type.Contrast);
    dataSet.add(Type.Sepia);
    dataSet.add(Type.Toon);

    recyclerView.setAdapter(new MainAdapter(this, dataSet));
  }
}
