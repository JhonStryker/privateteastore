package com.priavteTeaStore.domain;

import javax.persistence.Embeddable;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Embeddable
public class TeaStorePosition {
    Double nx = 0.0;
    Double ny = 0.0;
    Double ex = 0.0;
    Double ey = 0.0;
    Double height = 0.0;

    public TeaStorePosition() {
    }

    public TeaStorePosition(Double nx, Double ny, Double ex, Double ey, Double height) {
        this.nx = nx;
        this.ny = ny;
        this.ex = ex;
        this.ey = ey;
        this.height = height;
    }


}
