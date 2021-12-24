package com.bw.library_common.router.cargo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
@Entity
public class CarGo {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String title;

    private String pic;

    //是否收货
    private boolean isReceiving;

    //是否评价
    private boolean isEvaluate;


    @Ignore
    public CarGo() {
    }




    public CarGo(Long id, String title, String pic, boolean isReceiving, boolean isEvaluate) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.isReceiving = isReceiving;
        this.isEvaluate = isEvaluate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isReceiving() {
        return isReceiving;
    }

    public void setReceiving(boolean receiving) {
        isReceiving = receiving;
    }

    public boolean isEvaluate() {
        return isEvaluate;
    }

    public void setEvaluate(boolean evaluate) {
        isEvaluate = evaluate;
    }
}
