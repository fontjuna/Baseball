package noh.seung.hwa.baseball.classes;

/**
 * Created by fontjuna on 2017-09-05.
 */

public class Element {
    String mLeft;
    String mRight;

    public Element(String left, String right) {
        mLeft = left;
        mRight = right;
    }

    public String getLeft() {
        return mLeft;
    }

    public void setLeft(String left) {
        mLeft = left;
    }

    public String getRight() {
        return mRight;
    }

    public void setRight(String right) {
        mRight = right;
    }
}
