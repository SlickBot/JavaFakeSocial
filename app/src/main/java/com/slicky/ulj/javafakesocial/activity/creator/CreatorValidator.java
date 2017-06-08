package com.slicky.ulj.javafakesocial.activity.creator;

import android.support.design.widget.TextInputLayout;
import com.slicky.ulj.javafakesocial.R;

/**
 * Created by SlickyPC on 8.6.2017
 */
class CreatorValidator {

    private TextInputLayout textLayout;

    CreatorValidator(CreatorActivity activity) {
        textLayout = (TextInputLayout) activity.findViewById(R.id.creator_text_layout);
    }

    boolean validate() {
        textLayout.setError(null);

        String text = textLayout.getEditText().getText().toString();
        if (text.length() < 5) {
            textLayout.setError("Text is too short! (minimum is 5)");
            textLayout.requestFocus();
            return false;
        }
        if (text.length() > 256) {
            textLayout.setError("Text is too long! (minimum is 256)");
            textLayout.requestFocus();
            return false;
        }

        return true;
    }
}
