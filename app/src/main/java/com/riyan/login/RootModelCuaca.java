package com.riyan.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootModelCuaca {
    @SerializedName("list")
    private List<ListModelCuaca> listModelList;

    public List<ListModelCuaca> getListModelList() {
        return listModelList;
    }

    public void setListModelList(List<ListModelCuaca> listModelList) {
        this.listModelList = listModelList;
    }

    public RootModelCuaca() {
    }
}
