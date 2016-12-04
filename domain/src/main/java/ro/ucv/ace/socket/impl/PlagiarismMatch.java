package ro.ucv.ace.socket.impl;

/**
 * Created by Geo on 04.12.2016.
 */
public class PlagiarismMatch {

    private int firstStudentId;

    private int secondStudentId;

    private String url;

    private float similarityPercent;

    public int getFirstStudentId() {
        return firstStudentId;
    }

    public void setFirstStudentId(int firstStudentId) {
        this.firstStudentId = firstStudentId;
    }

    public int getSecondStudentId() {
        return secondStudentId;
    }

    public void setSecondStudentId(int secondStudentId) {
        this.secondStudentId = secondStudentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getSimilarityPercent() {
        return similarityPercent;
    }

    public void setSimilarityPercent(float similarityPercent) {
        this.similarityPercent = similarityPercent;
    }
}
