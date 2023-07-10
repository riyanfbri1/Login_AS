package com.riyan.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListModelCuaca {
    @SerializedName("main")
    private MainModelCuaca mainModel;
    @SerializedName("weather")
    private List<WeatherModelCuaca> weatherModelList;
    private String dt_txt;
    public MainModelCuaca getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModelCuaca mainModel) {
        this.mainModel = mainModel;
    }

    public List<WeatherModelCuaca> getWeatherModelList() {
        return weatherModelList;
    }

    public void setWeatherModelList(List<WeatherModelCuaca> weatherModelList) {
        this.weatherModelList = weatherModelList;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public ListModelCuaca() {

    }
}