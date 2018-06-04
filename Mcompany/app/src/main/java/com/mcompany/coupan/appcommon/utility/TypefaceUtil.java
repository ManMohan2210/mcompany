package com.mcompany.coupan.appcommon.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;

import com.mcompany.coupan.R;


public class TypefaceUtil {
    private static SparseArray<Typeface> fontArray = new SparseArray<>();

    /**
     * This method return typeface which are already defined in system (8 types of fonts).
     *
     * @param context
     * @param fontId  String id which identify font type(ex. R.string.ubuntu), ids are defined in string.xml file.
     * @return
     */
    public static Typeface getFont(Context context, int fontId) {
        Typeface tf = fontArray.get(fontId);
        if (tf == null) {
            tf = Typeface.createFromAsset(context.getAssets(),
                    "font/" + context.getResources().getString(fontId) + ".ttf");
            fontArray.put(fontId, tf);
        }
        return tf;
    }


    /**
     * Get Font name from font attribute value.
     *
     * @param cf font attribute name
     * @return
     */
    public static int getFontName(int cf) {
        int fontName = 0;
        switch (cf) {
            case 1:
                fontName = R.string.regular;
                break;
            case 2:
                fontName = R.string.bold;
                break;
            case 3:
                fontName = R.string.bold_italic;
                break;
            case 4:
                fontName = R.string.italic;
                break;
            case 5:
                fontName = R.string.light;
                break;
            case 6:
                fontName = R.string.light_italic;
                break;

            case 7:
                fontName = R.string.medium;
                break;

            case 8:
                fontName = R.string.medium_italic;
                break;
            default:
                fontName = R.string.regular;
                break;
        }
        return fontName;
    }

}
