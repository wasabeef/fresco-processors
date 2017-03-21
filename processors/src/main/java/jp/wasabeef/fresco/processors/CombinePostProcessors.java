package jp.wasabeef.fresco.processors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.facebook.imagepipeline.request.BasePostprocessor;

import java.util.ArrayList;
import java.util.List;


public class CombinePostProcessors extends BasePostprocessor {

    private List<BasePostprocessor> mProcessors;

    private CombinePostProcessors(List<BasePostprocessor> processors) {
        super();
        mProcessors = processors;
    }

    @Override
    public void process(Bitmap dest, Bitmap src) {
        Canvas canvas = new Canvas(dest);
        Paint paint = new Paint();
        canvas.drawBitmap(src, 0, 0, paint);

        for(BasePostprocessor processor : mProcessors) {
            processor.process(dest, dest);
        }
    }

    public static class Builder {

        private List<BasePostprocessor> processors;

        public Builder() {
            processors = new ArrayList<BasePostprocessor>();
        }
        public Builder add(BasePostprocessor processor) {
            processors.add(processor);
            return this;
        }

        public CombinePostProcessors build() {
            return new CombinePostProcessors(processors);
        }
    }
}
