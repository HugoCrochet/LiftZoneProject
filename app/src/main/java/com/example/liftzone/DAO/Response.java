package com.example.liftzone.DAO;

public class Response {

    private String w_name;
    private int w_id, w_time;

    public Response (String name, int id){
        setW_name(name);
        setW_id(id);
        //setW_time(time);
    }

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public int getW_time() {
        return w_time;
    }

    public void setW_time(int w_time) {
        this.w_time = w_time;
    }



}